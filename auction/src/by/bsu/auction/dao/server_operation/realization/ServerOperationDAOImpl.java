package by.bsu.auction.dao.server_operation.realization;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.bsu.auction.dao.connection_pool.ConnectionPool;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.exception.DBConnectionException;
import by.bsu.auction.dao.server_operation.ServerOperationDAO;
import by.bsu.auction.dao.server_operation.realization.util.ServerProcessor;
import by.tc.auction.entity.Auction;

public class ServerOperationDAOImpl implements ServerOperationDAO {

	private ServerProcessor serverProcessor = new ServerProcessor();
	private static final Logger logger = Logger.getLogger(ServerOperationDAOImpl.class);

	@Override
	public ArrayList<Auction> getActiveAuctions() throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return serverProcessor.getActiveAuctions(connection);
		} catch (SQLException | DBConnectionException e) {
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
			serverProcessor.completeAuctions(connection, auctions);
			serverProcessor.setLoserParticipations(connection, auctions);
			serverProcessor.setWinParticipations(connection, auctions);
			connection.commit();
			connection.setAutoCommit(true);
			return true;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in ServerOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

}
