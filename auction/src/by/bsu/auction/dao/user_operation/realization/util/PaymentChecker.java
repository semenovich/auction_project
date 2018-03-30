package by.bsu.auction.dao.user_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.tc.auction.entity.Auction;

public class PaymentChecker {

	private static final String CHECK_IS_PARTICIPATION_EXIST_SQL_STATEMENT = "SELECT * FROM auction.user_participation_in_bidding WHERE su_login=? AND a_id=?";
	
	public boolean isParictipationExist(Connection connection, Auction auction, String userLogin) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IS_PARTICIPATION_EXIST_SQL_STATEMENT)) {
			preparedStatement.setString(1, userLogin);
			preparedStatement.setInt(2, auction.getId());
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		}
	}
}
