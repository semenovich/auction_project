package by.tc.auction.dao.connection_pool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import by.tc.auction.dao.exception.ConnectionPoolException;

public class ConnectionPool {

    private static final Logger logger = Logger.getLogger(ConnectionPool.class);
    
	private static ConnectionPool instance = new ConnectionPool();

    private static final int DEFAULT_POOL_SIZE = 5;

    private static Lock lock;
    private static AtomicBoolean isInitialized;

    private BlockingQueue<Connection> freeConnectionQueue;
    private BlockingQueue<Connection> busyConnectionQueue;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    static {
        lock = new ReentrantLock();
        isInitialized = new AtomicBoolean();
    }

    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DBParameterList.DATABASE_DRIVER);
        this.url = dbResourceManager.getValue(DBParameterList.DATABASE_URL);
        this.user = dbResourceManager.getValue(DBParameterList.DATABASE_USER);
        this.password = dbResourceManager.getValue(DBParameterList.DATABASE_PASSWORD);

        try {
            this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameterList.DATABASE_POOL_SIZE));
            initPoolData();
        } catch (NumberFormatException e) {
            poolSize = DEFAULT_POOL_SIZE;
        } catch (ConnectionPoolException e) {
            logger.fatal("ConnectionPool exception into ConnectionPool", e);
        }
    }

    public static ConnectionPool getInstance() {
        if(!isInitialized.get()){
            lock.lock();
            try{
                if(!isInitialized.get()){
                    instance = new ConnectionPool();
                    isInitialized.set(true);
                }
            }finally {
                lock.unlock();
            }
        }

        return instance;
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = freeConnectionQueue.take();
            busyConnectionQueue.add(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(
                    "Error connecting to the data source.", e);
        }
        return connection;
    }

    private void initPoolData() throws ConnectionPoolException {
        try {
            Class.forName(driverName);
            busyConnectionQueue = new ArrayBlockingQueue<>(poolSize);
            freeConnectionQueue = new ArrayBlockingQueue<>(poolSize);
            for (int counter = 0; counter < poolSize; counter++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                PooledConnection pooledConnection = new PooledConnection(connection);
                freeConnectionQueue.add(pooledConnection);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("SQLException in ConnectionPool", e);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Can't find database driver class", e);
        }
    }

    private class PooledConnection implements Connection {
        private Connection connection;

        private PooledConnection(Connection c) throws SQLException {
            this.connection = c;
            this.connection.setAutoCommit(true);
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public void close() throws SQLException {
            if (connection.isClosed()) {
                throw new SQLException("Attempting to close closed connection.");
            }

            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }

            if (!busyConnectionQueue.remove(this)) {
                throw new SQLException(
                        "Error deleting connection from the given away connections pool.");
            }

            if (!freeConnectionQueue.offer(this)) {
                throw new SQLException("Error allocating connection in the pool.");
            }
            connection.setAutoCommit(true);
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements)
                throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }
        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }
        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }
        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }
        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }
        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }
        @Override
        public Statement createStatement(int resultSetType,
                                         int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType,
                    resultSetConcurrency);
        }
        @Override
        public Statement createStatement(int resultSetType,
                                         int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return connection.createStatement(resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }
        @Override
        public Struct createStruct(String typeName, Object[] attributes)
                throws SQLException {
            return connection.createStruct(typeName, attributes);
        }
        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }
        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }
        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }
        @Override
        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }
        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }
        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }
        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }
        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }
        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }
        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }
        @Override
        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }
        @Override
        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }
        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }
        @Override
        public CallableStatement prepareCall(String sql, int resultSetType,
                                             int resultSetConcurrency) throws SQLException {
            return connection.prepareCall(sql, resultSetType,
                    resultSetConcurrency);
        }
        @Override
        public CallableStatement prepareCall(String sql, int resultSetType,
                                             int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
            return connection.prepareCall(sql, resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }
        @Override
        public PreparedStatement prepareStatement(String sql)
                throws SQLException {
            return connection.prepareStatement(sql);
        }
        @Override
        public PreparedStatement prepareStatement(String sql,
                                                  int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }
        @Override
        public PreparedStatement prepareStatement(String sql,
                                                  int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }
        @Override
        public PreparedStatement prepareStatement(String sql,
                                                  String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }
        @Override
        public PreparedStatement prepareStatement(String sql,
                                                  int resultSetType, int resultSetConcurrency)
                throws SQLException {
            return connection.prepareStatement(sql, resultSetType,
                    resultSetConcurrency);
        }
        @Override
        public PreparedStatement prepareStatement(String sql,
                                                  int resultSetType, int resultSetConcurrency,
                                                  int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType,
                    resultSetConcurrency, resultSetHoldability);
        }
        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }
        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }
        @Override
        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }
        @Override
        public void setClientInfo(String name, String value)
                throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }
        @Override
        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        @Override
        public void abort(Executor arg0) throws SQLException {
            connection.abort(arg0);

        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void releaseSavepoint(Savepoint arg0) throws SQLException {
            connection.releaseSavepoint(arg0);
        }

        @Override
        public void rollback(Savepoint arg0) throws SQLException {
            connection.rollback(arg0);
        }

        @Override
        public void setClientInfo(Properties arg0)
                throws SQLClientInfoException {
            connection.setClientInfo(arg0);
        }

        @Override
        public void setNetworkTimeout(Executor arg0, int arg1)
                throws SQLException {
            connection.setNetworkTimeout(arg0, arg1);
        }

        @Override
        public void setSchema(String arg0) throws SQLException {
            connection.setSchema(arg0);
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
            connection.setTypeMap(arg0);
        }

    }
}
