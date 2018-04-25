package by.tc.auction.dao.authentication.realization.util;

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

	private static final String CHECK_USER_LOGIN_AND_PASSWORD_SQL_STATEMENT = "SELECT u.su_login AS userLogin, u.su_surname AS userSurname, u.su_Name AS userName, u.su_password AS userPassword, u.su_email AS userEmail, u.su_phone AS userPhone, u.su_passport_id AS userPassportId, u.su_passport_issued_by AS userPassportIssuedBy, c.uc_name AS userCountry, u.su_blocked AS isBlocked, r.sur_role_name AS userRole, u.su_picture AS userPicture FROM auction.site_users u INNER JOIN auction.site_users_role r ON u.sur_id = r.sur_id INNER JOIN auction.users_countries c ON u.uc_id = c.uc_id WHERE u.su_login=? AND u.su_password=MD5(?)";
	private static final String CHECK_USER_LOGIN_SQL_STATEMENT = "SELECT su_login AS userLogin FROM auction.site_users WHERE su_login=?";
	
	private static final Logger logger = Logger.getLogger(UserChecker.class);
	
	/**
	 * Creates and executes a user existence checking query to a database.
	 * @param connection - a connection to a database.
	 * @param login - a user login.
	 * @return {@code true} - if a user exist in a database. {@code false} - if a user doesn't exist. 
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public boolean isUserExist(Connection connection, String login) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_LOGIN_SQL_STATEMENT)){
			preparedStatement.setString(1, login);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		} catch (SQLException e){
			logger.error("Error in UserChecker", e);
			throw e;
		}
	}
	
	/**
	 * 
	 * Creates and executes a user existence checking query to a database.
	 * @param connection - a connection to a database.
	 * @param login - a user login.
	 * @param password
	 * @return {@code true} - if a user exist in a database. {@code false} - if a user doesn't exist. 
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public boolean isUserExist(Connection connection, String login, String password) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_LOGIN_AND_PASSWORD_SQL_STATEMENT)){
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		} catch (SQLException e){
			logger.error("Error in UserChecker", e);
			throw e;
		}
	}
}
