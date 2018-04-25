package by.tc.auction.dao.admin_operation.realization;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.tc.auction.dao.admin_operation.AdminOperationDAO;
import by.tc.auction.dao.admin_operation.realization.util.AdminBlocker;
import by.tc.auction.dao.admin_operation.realization.util.AdminExistenceChecker;
import by.tc.auction.dao.authentication.realization.AuthDAOImpl;
import by.tc.auction.dao.connection_pool.ConnectionPool;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.exception.ConnectionPoolException;

/**
 * A class is used to provide methods for an administrator working in a database.
 * @author semenovich
 *
 */
public class AdminOperationDAOImpl implements AdminOperationDAO {

	private final AdminBlocker adminBlocker = new AdminBlocker();
	private final AdminExistenceChecker checker = new AdminExistenceChecker();
	private static final Logger logger = Logger.getLogger(AuthDAOImpl.class);
	
	/**
	 * Default constructor.
	 */
	public AdminOperationDAOImpl() {}

	/**
	 * Blocks a user by login in a database. 
	 * @param userLogin - a login of a user which will be blocked.
	 * @return {@code true} - if a user has been blocked. {@code false} - if a user doesn't exist and hasn't been blocked.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public boolean blockUser(String userLogin) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (checker.isUserExist(connection, userLogin)) {
				adminBlocker.blockUser(connection, userLogin);
				return true;
			}
			return false;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in AdminOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Unblocks a user by login in a database. 
	 * @param userLogin - a login of a user which will be unblocked.
	 * @return {@code false} - if a user has been unblocked. {@code false} - if a user doesn't exist and hasn't been blocked.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public boolean unblockUser(String userLogin) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (checker.isUserExist(connection, userLogin)) {
				adminBlocker.unblockUser(connection, userLogin);
				return true;
			}
			return false;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in AdminOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Blocks a lot by ID in a database. 
	 * @param lotId - an ID of a lot which will be blocked.
	 * @return {@code true} - if a lot has been blocked. {@code false} - if a lot doesn't exist and hasn't been blocked.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public boolean blockLot(Integer lotId) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (checker.isLotExist(connection, lotId)) {
				adminBlocker.blockLot(connection, lotId);
				return true;
			}
			return false;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in AdminOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
	
	/**
	 * Unblocks a lot by ID in a database. 
	 * @param lotId - an ID of a lot which will be unblocked.
	 * @return {@code true} - if a lot has been unblocked. {@code false} - if a lot doesn't exist and hasn't been blocked.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public boolean unblockLot(Integer lotId) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (checker.isLotExist(connection, lotId)) {
				adminBlocker.unblockLot(connection, lotId);
				return true;
			}
			return false;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in AdminOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
}
