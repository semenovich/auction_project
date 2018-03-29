package by.bsu.auction.dao.user_check_block_filter.realization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.bsu.auction.dao.connection_pool.ConnectionPool;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.exception.DBConnectionException;
import by.bsu.auction.dao.user_check_block_filter.UserBlockCheckerDAO;

public class UserBlockCheckerDAOImpl implements UserBlockCheckerDAO {

	private final String CHECK_IS_USER_BLOCKED_SQL_STATEMENT = "SELECT su_blocked AS isBlocked FROM auction.site_users WHERE su_login=?";
	private static final Logger logger = Logger.getLogger(UserBlockCheckerDAOImpl.class);
	
	private static final String USER_IS_BLOCKED = "isBlocked";
	
	@Override
	public boolean isBlocked(String login) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IS_USER_BLOCKED_SQL_STATEMENT);
			preparedStatement.setString(1, login);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				return result.getBoolean(USER_IS_BLOCKED);
			}
			return false;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in UserBlockCheckerDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
}
