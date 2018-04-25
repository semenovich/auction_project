package by.tc.auction.dao.admin_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.tc.auction.entity.LotStatus;

/**
 * A class is used to create and execute lock (unlock) database queries.  
 * @author semenovich
 *
 */
public class AdminBlocker {
	
	private static final String BLOCK_LOT_SQL_STATEMENT = "UPDATE auction.lots SET l_status=? WHERE l_id=?";
	private static final String BLOCK_USER_SQL_STATEMENT = "UPDATE auction.site_users SET su_blocked=? WHERE su_login=?";
	
	private static final Logger logger = Logger.getLogger(AdminBlocker.class);
	
	/**
	 * Default constructor.
	 */
	public AdminBlocker() {}
	
	/**
	 * Creates and executes a user lock query to a database.
	 * @param connection - a connection to a database.
	 * @param userLogin - a login of a user which will be blocked.
	 * @return {@code true}.
	 * @throws SQLException - if a database access error or other errors occurred. 
	 */
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
	
	/**
	 * Creates and executes a user unlock query to a database.
	 * @param connection - a connection to a database.
	 * @param userLogin - a login of a user which will be unblocked.
	 * @return {@code true}.
	 * @throws SQLException - if a database access error or other errors occurred. 
	 */
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
	
	/**
	 * Creates and executes a lot lock query to a database.
	 * @param connection - a connection to a database.
	 * @param lotId - an ID of a lot which will be blocked.
	 * @return {@code true}.
	 * @throws SQLException - if a database access error or other errors occurred. 
	 */
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
	
	/**
	 * Creates and executes a lot unlock query to a database.
	 * @param connection - a connection to a database.
	 * @param lotId - an ID of a lot which will be unblocked.
	 * @return {@code true}.
	 * @throws SQLException - if a database access error or other errors occurred. 
	 */
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
