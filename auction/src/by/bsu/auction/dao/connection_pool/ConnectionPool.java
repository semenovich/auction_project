package by.bsu.auction.dao.connection_pool;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import by.bsu.auction.dao.exception.DBConnectionException;

public class ConnectionPool {
	
	private static ConnectionPool instance = new ConnectionPool();
	   
	private ConnectionPool() {}
 
    public static ConnectionPool getInstance(){
        return instance;
    }
   
    public Connection getConnection() throws DBConnectionException{
        Context ctx;
        Connection conection = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/auctionPull");
            conection = ds.getConnection();
        } catch (NamingException | SQLException e) {
            throw new DBConnectionException(e.getMessage(), e.getCause());
        }
        return conection;
    }
}
