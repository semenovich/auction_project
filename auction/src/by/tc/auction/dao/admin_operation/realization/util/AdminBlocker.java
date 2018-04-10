package by.tc.auction.dao.admin_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.tc.auction.entity.LotStatus;

public class AdminBlocker {
	
	private static final String BLOCK_LOT_SQL_STATEMENT = "UPDATE auction.lots SET l_status=? WHERE l_id=?";
	private static final String BLOCK_USER_SQL_STATEMENT = "UPDATE auction.site_users SET su_blocked=? WHERE su_login=?";
	
	private static final Logger logger = Logger.getLogger(AdminBlocker.class);
	
	public AdminBlocker() {}
	
	public boolean blockUser(Connection connection, String userLogin) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_USER_SQL_STATEMENT)) {
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, userLogin);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e){
			logger.error("Error in AdminBlocker", e);
			throw e;
		}
	}
	
	public boolean unblockUser(Connection connection, String userLogin) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_USER_SQL_STATEMENT)) {
			preparedStatement.setBoolean(1, false);
			preparedStatement.setString(2, userLogin);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e){
			logger.error("Error in AdminBlocker", e);
			throw e;
		}
	}
	
	public boolean blockLot(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_LOT_SQL_STATEMENT)) {
			preparedStatement.setString(1, LotStatus.BLOCKED.toString());
			preparedStatement.setInt(2, lotId);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e){
			logger.error("Error in AdminBlocker", e);
			throw e;
		}
	}
	
	public boolean unblockLot(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_LOT_SQL_STATEMENT)) {
			preparedStatement.setString(1, LotStatus.READY.toString());
			preparedStatement.setInt(2, lotId);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e){
			logger.error("Error in AdminBlocker", e);
			throw e;
		}
	}
}
