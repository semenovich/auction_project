package by.bsu.auction.dao.lot_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LotChecker {

	private static final String CHECK_LOT_EXISTENCE_SQL_STATEMENT = "SELECT * FROM auction.lots WHERE l_id=?";
	private static final String CHECK_IS_LOT_CONFIRMING_SQL_STATEMENT = "SELECT l_id AS lotId FROM auction.lots WHERE l_id=? AND l_status='CONFIRMING'";
	
	public boolean isLotExist(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOT_EXISTENCE_SQL_STATEMENT)) {
			preparedStatement.setInt(1, lotId);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		}
	}
	
	public boolean checkIsLotConfirming(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IS_LOT_CONFIRMING_SQL_STATEMENT)) {
			preparedStatement.setInt(1, lotId);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		}
	}
}
