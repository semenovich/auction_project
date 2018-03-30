package by.bsu.auction.dao.user_operation.realization.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Bet;

public class PaymentProcessor {

	private static final String PLACE_BET_SQL_STATEMENT = "UPDATE auction.auctions SET a_current_price=?, su_login_last_bet=?, a_last_bet_time=? WHERE a_id=?";
	private static final String CREATE_PARTICIPATION_SQL_STATEMENT = "INSERT INTO auction.user_participation_in_bidding (su_login, a_id, upib_status) VALUES (?, ?, 'ACTIVE')";
	private static final String SET_AUCTION_STATUS_COMPLETED_SQL_STATEMENT = "UPDATE auction.auctions SET a_status='COMPLETED' WHERE a_id=?";
	private static final String SET_LOT_STATUS_SOLED_SQL_STATEMENT = "UPDATE auction.lots SET l_status='SOLED' WHERE l_id=?";
	private static final String CHECK_IS_AUCTION_EXIST_SQL_STATEMENT = "SELECT * FROM auction.auctions WHERE a_id=?";
	private static final String CHECK_IS_AUCTION_ACTIVE_SQL_STATEMENT = "SELECT * FROM auction.auctions WHERE a_id=? AND a_status='ACTIVE'";
	
	public boolean placeBet(Connection connection, Auction auction, String userLogin, Bet bet, Date betTime) throws SQLException {
		if (isAuctionExist(connection, auction.getId()) && isAuctionActive(connection, auction.getId())) {
			try(PreparedStatement preparedStatement = connection.prepareStatement(PLACE_BET_SQL_STATEMENT)) {
				preparedStatement.setDouble(1, bet.getValue());
				preparedStatement.setString(2, userLogin);
				preparedStatement.setDate(3, betTime);
				preparedStatement.setInt(4, auction.getId());
				preparedStatement.executeUpdate();
				return true;
			}
		}
		return false;
	}
	
	public boolean createParticipation(Connection connection, Auction auction, String userLogin) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PARTICIPATION_SQL_STATEMENT)) {
			preparedStatement.setString(1, userLogin);
			preparedStatement.setInt(2, auction.getId());
			preparedStatement.executeUpdate();
			return true;
		}
	}
	
	public boolean payForLot(Connection connection, Integer auctionId, Integer lotId) throws SQLException {
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
		connection.setAutoCommit(true);
		return true;
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
