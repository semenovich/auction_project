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

/**
 * A class is used to provide methods for a server working in a database.
 * @author semenovich
 *
 */
public class ServerOperationDAOImpl implements ServerOperationDAO {
 
	private final ServerProcessor serverProcessor = new ServerProcessor();
	private static final Logger logger = Logger.getLogger(ServerOperationDAOImpl.class);

	/**
	 * Default constructor.
	 */
	public ServerOperationDAOImpl() {}
	
	/**
	 * Returns a list of active auctions in a database. 
	 * @return A list of active auctions in a database. Empty list if active auctions doesn't exist in a database.
	 * @throws DAOException - if a database access error or other errors occurred.
	 */
	@Override
	public ArrayList<Auction> getActiveAuctions() throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return serverProcessor.getActiveAuctions(connection);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in ServerOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Completes auctions in a database.
	 * @param auctions - a list of auctions which will be completed in a database. Only the ID, the last bet user and the end time fields must be filled in.
	 * @return {@code true}.
	 * @throws DAOException - if a database access error or other errors occurred with database or not all fields are filled in.
	 */
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
				return true;
			} catch (SQLException e) {
				connection.rollback();
				logger.error("Error in ServerOperationDAOImpl", e);
				throw new DAOException(e.getMessage(), e.getCause());
			}
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in ServerOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
}
