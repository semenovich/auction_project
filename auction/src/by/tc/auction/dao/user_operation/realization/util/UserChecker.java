package by.tc.auction.dao.user_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * A class is used to create and execute a user existence checking query to a database.
 * @author semenovich
 *
 */
public class UserChecker {

	private static final String CHECK_USER_LOGIN_SQL_STATEMENT = "SELECT su_login AS userLogin FROM auction.site_users WHERE su_login=?";
	
	private static final Logger logger = Logger.getLogger(UserChecker.class);

	/**
	 * Creates and executes a user existence checking query to a database.
	 * @param connection - a connection to a database.
	 * @param userLogin - a user login.
	 * @return {@code true} - if a user exists in a database. {@code false} - if a user doesn't exist in a database.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public boolean isUserExist(Connection connection, String userLogin) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_LOGIN_SQL_STATEMENT)){
			preparedStatement.setString(1, userLogin);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		} catch (SQLException e){
			logger.error("Error in UserChecker", e);
			throw e;
		}
	}
}
