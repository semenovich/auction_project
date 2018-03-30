package by.bsu.auction.dao.admin_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExistenceChecker {
	
	private static final String CHECK_LOT_EXISTENCE_SQL_STATEMENT = "SELECT * FROM auction.lots WHERE l_id=?";
	private static final String CHECK_USER_LOGIN_SQL_STATEMENT = "SELECT su_login AS userLogin FROM auction.site_users WHERE su_login=?";
	
	public boolean isUserExist(Connection connection, String userLogin) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_LOGIN_SQL_STATEMENT)){
			preparedStatement.setString(1, userLogin);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		}
	}
	
	public boolean isLotExist(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOT_EXISTENCE_SQL_STATEMENT)) {
			preparedStatement.setInt(1, lotId);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		}
	}
}
