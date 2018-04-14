package by.tc.auction.dao.user_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Bet;

public class PaymentProcessor {

	private static final String PLACE_BET_SQL_STATEMENT = "UPDATE auction.user_participation_in_bidding SET upib_bet=?, upib_last_bet_time=? WHERE su_login=? AND a_id=?";
	private static final String CREATE_PARTICIPATION_SQL_STATEMENT = "INSERT INTO auction.user_participation_in_bidding (su_login, a_id, upib_status) VALUES (?, ?, 'ACTIVE')";
	private static final String SET_AUCTION_STATUS_COMPLETED_SQL_STATEMENT = "UPDATE auction.auctions SET a_status='COMPLETED' WHERE a_id=?";
	private static final String SET_LOT_STATUS_SOLED_SQL_STATEMENT = "UPDATE auction.lots SET l_status='SOLED' WHERE l_id=?";
	private static final String CHECK_IS_AUCTION_EXIST_SQL_STATEMENT = "SELECT * FROM auction.auctions WHERE a_id=?";
	private static final String CHECK_IS_AUCTION_ACTIVE_SQL_STATEMENT = "SELECT * FROM auction.auctions WHERE a_id=? AND a_status='ACTIVE'";
	
	private static final Logger logger = Logger.getLogger(PaymentProcessor.class);

	public boolean placeBet(Connection connection, Auction auction, String userLogin, Bet bet, Timestamp betTime) throws SQLException {
		try{
			if (isAuctionExist(connection, auction.getId()) && isAuctionActive(connection, auction.getId())) {
			try(PreparedStatement preparedStatement = connection.prepareStatement(PLACE_BET_SQL_STATEMENT)) {
				preparedStatement.setDouble(1, bet.getValue());
				preparedStatement.setTimestamp(2, betTime);
				preparedStatement.setString(3, userLogin);
				preparedStatement.setInt(4, auction.getId());
				preparedStatement.executeUpdate();
				return true;
				}
			}
			return false;
		} catch (SQLException e){
			logger.error("Error in PaymentProcessor", e);
			throw e;
		}
	}
	
	public boolean createParticipation(Connection connection, Auction auction, String userLogin) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PARTICIPATION_SQL_STATEMENT)) {
			preparedStatement.setString(1, userLogin);
			preparedStatement.setInt(2, auction.getId());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e){
			logger.error("Error in PaymentProcessor", e);
			throw e;
		}
	}
	
	public boolean payForLot(Connection connection, Integer auctionId, Integer lotId) throws SQLException {
		try {
			connection.setAutoCommit(false);
			try(PreparedStatement preparedStatement = connection.prepareStatement(SET_AUCTION_STATUS_COMPLETED_SQL_STATEMENT)) {
				preparedStatement.setInt(1, auctionId);
				preparedStatement.executeUpdate();
			}
			try(PreparedStatement preparedStatement = connection.prepareStatement(SET_LOT_STATUS_SOLED_SQL_STATEMENT)) {
				preparedStatement.setInt(1, lotId);
				preparedStatement.executeUpdate();
			}
			connection.commit();
			return true;
		} catch (SQLException e){
			logger.error("Error in PaymentProcessor", e);
			connection.rollback();
			throw e;
		}
	}
	
	private boolean isAuctionExist(Connection connection, Integer auctionId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IS_AUCTION_EXIST_SQL_STATEMENT)) {
			preparedStatement.setInt(1, auctionId);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		}
	}
	
	private boolean isAuctionActive(Connection connection, Integer auctionId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IS_AUCTION_ACTIVE_SQL_STATEMENT)) {
			preparedStatement.setInt(1, auctionId);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		}
	}
}
