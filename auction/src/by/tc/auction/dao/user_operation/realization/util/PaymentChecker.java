package by.tc.auction.dao.user_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Bet;

/**
 * A class is used to create and execute a participation existence and an auction current bet checking query to a database.
 * @author semenovich
 *
 */
public class PaymentChecker {

	private static final String CHECK_IS_PARTICIPATION_EXIST_SQL_STATEMENT = "SELECT * FROM auction.user_participation_in_bidding WHERE su_login=? AND a_id=?";
	private static final String GET_AUCTION_CURRENT_BET_SQL_STATEMENT = "SELECT MAX(upib_bet) AS auctionCurrentBet FROM auction.user_participation_in_bidding upib_max WHERE upib_max.a_id = ?";
	
	private static final String AUCTION_CURRENT_BET = "auctionCurrentBet";
	
	private static final Logger logger = Logger.getLogger(PaymentChecker.class);

	/**
	 * Creates and executes a participation existence checking query to a database.
	 * @param connection - a connection to a database.
	 * @param auction - an auction. Only ID field must be filled in. 
	 * @param userLogin - a user login.
	 * @return {@code true} - if a participation exists. {@code false} - if a participation doesn't exist.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
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
	
	/**
	 * Creates and executes a query to a database to get an auction current bet.
	 * @param connection - a connection to a database.
	 * @param auctionId - an auction ID.
	 * @return Auction current bet.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public Bet getAuctionCurrentBet(Connection connection, Integer auctionId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_AUCTION_CURRENT_BET_SQL_STATEMENT)) {
			preparedStatement.setInt(1, auctionId);
			ResultSet result = preparedStatement.executeQuery();
			Bet bet = new Bet();
			bet.setValue(0D);
			if (result.next()) {
				bet.setValue(result.getDouble(AUCTION_CURRENT_BET));
			}
			return bet;
		} catch (SQLException e){
			logger.error("Error in PaymentChecker", e);
			throw e;
		}
	}
}
