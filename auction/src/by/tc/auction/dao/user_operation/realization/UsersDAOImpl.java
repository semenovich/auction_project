package by.tc.auction.dao.user_operation.realization;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.tc.auction.dao.connection_pool.ConnectionPool;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.exception.ConnectionPoolException;
import by.tc.auction.dao.user_operation.UsersDAO;
import by.tc.auction.dao.user_operation.realization.util.UserInfoGetter;
import by.tc.auction.entity.User;

/**
 * A class is used to provide methods for operations on users in a database.
 * @author semenovich
 *
 */
public class UsersDAOImpl implements UsersDAO {

	private final UserInfoGetter userInfoGetter = new UserInfoGetter();
	
	private static final Logger logger = Logger.getLogger(UsersDAOImpl.class);

	/**
	 * Default constructor.
	 */
	public UsersDAOImpl() {}

	/**
	 * Returns a list of all users from a database.
	 * @return A list of all users in a database. Empty list if users don't exist in a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public ArrayList<User> getUsers() throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return userInfoGetter.getUsers(connection);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in UsersDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a list of users by matching a login of users from a database.
	 * @param searchLine - a search line which will be matched with a user name.
	 * @return A list of such users in a database. Empty list if such users don't exist in a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public ArrayList<User> getUsersBySearching(String searchLine) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return userInfoGetter.getUsersBySearching(connection, searchLine);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in UsersDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

}
