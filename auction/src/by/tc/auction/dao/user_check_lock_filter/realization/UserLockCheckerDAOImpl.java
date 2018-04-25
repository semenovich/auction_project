package by.tc.auction.dao.user_check_lock_filter.realization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.tc.auction.dao.connection_pool.ConnectionPool;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.user_check_lock_filter.UserLockCheckerDAO;
import by.tc.auction.dao.exception.ConnectionPoolException;

/**
 * A class is used to provide methods for working with a user lock checking in a database.
 * @author semenovich
 *
 */
public class UserLockCheckerDAOImpl implements UserLockCheckerDAO {

	private final String CHECK_IS_USER_BLOCKED_SQL_STATEMENT = "SELECT su_blocked AS isBlocked FROM auction.site_users WHERE su_login=?";
	private static final Logger logger = Logger.getLogger(UserLockCheckerDAOImpl.class);
	
	private static final String USER_IS_BLOCKED = "isBlocked";
	
	/**
	 * Default constructor.
	 */
	public UserLockCheckerDAOImpl() {}
	
	/**
	 * Checks if the user is blocked out in a database.
	 * @param login - a login of a user.
	 * @return {@code true} - if a user is blocked out in a database. {@code false} - if a user isn't blocked out in a database.
	 * @throws DAOException - if a database access error or other errors occurred.
	 */
	@Override
	public boolean isBlocked(String login) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IS_USER_BLOCKED_SQL_STATEMENT)) {
				preparedStatement.setString(1, login);
				ResultSet result = preparedStatement.executeQuery();
				if (result.next()) {
					return result.getBoolean(USER_IS_BLOCKED);
				}
				return false;
			} catch (SQLException e){
				throw e;
			}
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in UserBlockCheckerDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
}
