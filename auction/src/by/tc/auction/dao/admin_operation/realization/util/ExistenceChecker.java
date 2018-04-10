package by.tc.auction.dao.admin_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ExistenceChecker {
	
	private static final String CHECK_LOT_EXISTENCE_SQL_STATEMENT = "SELECT * FROM auction.lots WHERE l_id=?";
	private static final String CHECK_USER_LOGIN_SQL_STATEMENT = "SELECT su_login AS userLogin FROM auction.site_users WHERE su_login=?";
	
	private static final Logger logger = Logger.getLogger(ExistenceChecker.class);
	
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
