package by.bsu.auction.dao.admin_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.bsu.auction.entity.LotStatus;

public class AdminProcessor {
	
	private static final String CHECK_LOT_EXISTENCE_SQL_STATEMENT = "SELECT * FROM `auction`.`lots` WHERE `l_id`=?";
	private static final String CHECK_USER_LOGIN_SQL_STATEMENT = "SELECT su_login AS userLogin FROM site_users WHERE su_login=?";
	private static final String BLOCK_LOT_SQL_STATEMENT = "UPDATE `auction`.`lots` SET `l_status`=? WHERE `l_id`=?";
	private static final String BLOCK_USER_SQL_STATEMENT = "UPDATE `auction`.`site_users` SET `su_blocked`=? WHERE `su_login`=?";
	
	
	public AdminProcessor() {}
	
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
	
	public boolean blockUser(Connection connection, String userLogin) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_USER_SQL_STATEMENT)) {
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, userLogin);
			preparedStatement.executeUpdate();
			return true;
		}
	}
	
	public boolean unblockUser(Connection connection, String userLogin) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_USER_SQL_STATEMENT)) {
			preparedStatement.setBoolean(1, false);
			preparedStatement.setString(2, userLogin);
			preparedStatement.executeUpdate();
			return true;
		}
	}
	
	public boolean blockLot(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_LOT_SQL_STATEMENT)) {
			preparedStatement.setString(1, LotStatus.BLOCKED.toString());
			preparedStatement.setInt(2, lotId);
			preparedStatement.executeUpdate();
			return true;
		}
	}
	
	public boolean unblockLot(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_LOT_SQL_STATEMENT)) {
			preparedStatement.setString(1, LotStatus.READY.toString());
			preparedStatement.setInt(2, lotId);
			preparedStatement.executeUpdate();
			return true;
		}
	}
}
