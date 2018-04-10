package by.tc.auction.dao.server_operation.realization;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.tc.auction.dao.connection_pool.ConnectionPool;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.exception.ConnectionPoolException;
import by.tc.auction.dao.server_operation.ServerOperationDAO;
import by.tc.auction.dao.server_operation.realization.util.ServerProcessor;
import by.tc.auction.entity.Auction;

public class ServerOperationDAOImpl implements ServerOperationDAO {
 
	private final ServerProcessor serverProcessor = new ServerProcessor();
	private static final Logger logger = Logger.getLogger(ServerOperationDAOImpl.class);

	@Override
	public ArrayList<Auction> getActiveAuctions() throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return serverProcessor.getActiveAuctions(connection);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in ServerOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean completeAuctions(ArrayList<Auction> auctions) throws DAOException {
		if (auctions.isEmpty()) {
			return true;
		}
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			connection.setAutoCommit(false);
			try {
				serverProcessor.completeAuctions(connection, auctions);
				serverProcessor.setLoserParticipations(connection, auctions);
				serverProcessor.setWinParticipations(connection, auctions);
				connection.commit();
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				connection.rollback();
				logger.error("Error in ServerOperationDAOImpl", e);
				throw new DAOException(e.getMessage(), e.getCause());
			}
			return true;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in ServerOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

}
