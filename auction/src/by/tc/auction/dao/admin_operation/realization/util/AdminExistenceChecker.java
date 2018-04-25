package by.tc.auction.dao.admin_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * A class is used to create and execute a lot and a user existence checking query to a database.
 * @author semenovich
 *
 */
public class AdminExistenceChecker {
	
	private static final String CHECK_LOT_EXISTENCE_SQL_STATEMENT = "SELECT * FROM auction.lots WHERE l_id=?";
	private static final String CHECK_USER_LOGIN_SQL_STATEMENT = "SELECT su_login AS userLogin FROM auction.site_users WHERE su_login=?";
	
	private static final Logger logger = Logger.getLogger(AdminExistenceChecker.class);
	
	/**
	 * Default constructor.
	 */
	public AdminExistenceChecker() {}
	
	/**
	 * Creates and executes a user existence checking query to a database.
	 * @param connection - a connection to a database.
	 * @param userLogin - a login of a user which an existence will be checked. 
	 * @return {@code true} - if a user exists. {@code false} - if a user doesn't exist.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public boolean isUserExist(Connection connection, String userLogin) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_LOGIN_SQL_STATEMENT)){
			preparedStatement.setString(1, userLogin);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		} catch (SQLException e){
			logger.error("Error in ExistenceChecker", e);
			throw e;
		}
	}
	
	/**
	 * Creates and executes a lot existence checking query to a database.
	 * @param connection - a connection to a database.
	 * @param lotId - an ID of a lot which an existence will be checked. 
	 * @return {@code true} - if a lot exists. {@code false} - if a lot doesn't exist.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public boolean isLotExist(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOT_EXISTENCE_SQL_STATEMENT)) {
			preparedStatement.setInt(1, lotId);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		} catch (SQLException e){
			logger.error("Error in ExistenceChecker", e);
			throw e;
		}
	}
}
