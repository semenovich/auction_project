package by.tc.auction.dao.user_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.tc.auction.entity.Auction;

public class PaymentChecker {

	private static final String CHECK_IS_PARTICIPATION_EXIST_SQL_STATEMENT = "SELECT * FROM auction.user_participation_in_bidding WHERE su_login=? AND a_id=?";
	private static final String GET_AUCTION_CURRENT_BET_SQL_STATEMENT = "SELECT a_current_price AS auctionCurrentBet FROM auction.auctions WHERE a_id=?";
	
	private static final String AUCTION_CURRENT_BET = "auctionCurrentBet";
	
	private static final Logger logger = Logger.getLogger(PaymentChecker.class);

	public boolean isParictipationExist(Connection connection, Auction auction, String userLogin) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IS_PARTICIPATION_EXIST_SQL_STATEMENT)) {
			preparedStatement.setString(1, userLogin);
			preparedStatement.setInt(2, auction.getId());
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		} catch (SQLException e){
			logger.error("Error in PaymentChecker", e);
			throw e;
		}
	}
	
	public Double getAuctionCurrentBet(Connection connection, Integer auctionId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_AUCTION_CURRENT_BET_SQL_STATEMENT)) {
			preparedStatement.setInt(1, auctionId);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				return result.getDouble(AUCTION_CURRENT_BET);
			}
			return 0D;
		} catch (SQLException e){
			logger.error("Error in PaymentChecker", e);
			throw e;
		}
	}
}
