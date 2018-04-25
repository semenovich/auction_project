package by.tc.auction.dao.lot_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * A class is used to create and execute a lot existence, a lot waiting status checking query to a database.
 * @author semenovich
 *
 */
public class LotChecker {

	private static final String CHECK_LOT_EXISTENCE_SQL_STATEMENT = "SELECT * FROM auction.lots WHERE l_id=?";
	private static final String CHECK_IS_LOT_CONFIRMING_SQL_STATEMENT = "SELECT l_id AS lotId FROM auction.lots WHERE l_id=? AND l_status='CONFIRMING'";
	private static final String CHECK_IS_LOT_READY_SQL_STATEMENT = "SELECT l_id AS lotId FROM auction.lots WHERE l_id=? AND l_status='READY'";
	
	private static final Logger logger = Logger.getLogger(LotChecker.class);

	/**
	 * Creates and executes a lot existence checking query to a database.
	 * @param connection - a connection to a database.
	 * @param lotId - a lot ID.
	 * @return {@code true} - if a lot exists. {@code false} - if a lot doesn't exist.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public boolean isLotExist(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOT_EXISTENCE_SQL_STATEMENT)) {
			preparedStatement.setInt(1, lotId);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		} catch (SQLException e){
			logger.error("Error in LotChecker", e);
			throw e;
		}
	}
	
	/**
	 * Creates and executes a lot waiting status checking query to a database.
	 * <br> A waiting lot - is which has status 'CONFRMING' or 'READY' in a database.
	 * @param connection - a connection to a database.
	 * @param lotId - a lot ID.
	 * @return {@code true} - if a lot status is waiting. {@code false} - if a lot status isn't waiting. 
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public boolean isLotWaiting(Connection connection, Integer lotId) throws SQLException {
		try {
			try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IS_LOT_CONFIRMING_SQL_STATEMENT)) {
				preparedStatement.setInt(1, lotId);
				ResultSet result = preparedStatement.executeQuery();
				if (result.next()) {
					return true;
				}
			}
			try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IS_LOT_READY_SQL_STATEMENT)) {
				preparedStatement.setInt(1, lotId);
				ResultSet result = preparedStatement.executeQuery();
				return result.next();
			}
		} catch (SQLException e){
			logger.error("Error in LotChecker", e);
			throw e;
		}
	}
}
