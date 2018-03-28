package by.bsu.auction.dao.server_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.bsu.auction.entity.Auction;
import by.bsu.auction.entity.AuctionStatus;
import by.bsu.auction.entity.AuctionType;
import by.bsu.auction.entity.Bet;
import by.bsu.auction.entity.Lot;
import by.bsu.auction.entity.LotStatus;
import by.bsu.auction.entity.LotType;

public class ServerProcessor {
	
	private static final String GET_ACTIVE_AUCTIONS_LIST_SQL_STATEMENT = "SELECT l.l_id AS lotId, l.l_name AS lotName, l.l_description AS lotDescription, l.l_quantity AS lotQuantity, l.l_picture AS lotPicture, l.l_date_added AS lotDateAdded, l.l_type AS lotType, l.l_status AS lotStatus, l.su_owner_login AS lotOwner, a.a_id AS auctionId, a.a_last_bet_time AS auctionLastBetTime, a.a_start_time AS auctionStartTime, a.a_end_time AS auctionEndTime, a.a_status AS auctionStatus, l.l_type AS lotType, l.l_status AS lotStatus, a.a_minimum_price AS auctionMinimumPrice, a.a_current_price AS auctionCurrentPrice, au_t.at_type_name AS auctionType, a.su_login_last_bet AS auctionLastBetLogin FROM auctions AS a INNER JOIN lots AS l ON l.l_id = a.l_id INNER JOIN auctions_type AS au_t ON au_t.at_id = a.auctions_type_at_id WHERE a.a_status='ACTIVE' ORDER BY a.a_start_time DESC";
	private static final String SET_AUCTION_PENDING_PAYMENT_STATUS_SQL_STATEMENT = "UPDATE `auction`.`auctions` SET `a_status`='PENDING_PAYMENT' WHERE `a_id`=?";
	private static final String SET_LOSER_PARTICIPATIONS_SQL_STATEMENT = "UPDATE `auction`.`user_participation_in_bidding` SET `upib_status`='LOST' WHERE NOT `su_login`=? and`a_id`=?";
	private static final String SET_WIN_PARTICIPATIONS_SQL_STATEMENT = "UPDATE `auction`.`user_participation_in_bidding` SET `upib_status`='WON' WHERE `su_login`=? and`a_id`=?";
	
	private static final String AUCTION_ID = "auctionId";
	private static final String AUCTION_TYPE = "auctionType";
	private static final String AUCTION_START_TIME = "auctionStartTime";
	private static final String AUCTION_END_TIME = "auctionEndTime";
	private static final String AUCTION_MINIMUM_BET = "auctionMinimumPrice";
	private static final String AUCTION_CURRENT_BET = "auctionCurrentPrice";
	private static final String AUCTION_LAST_BET_USER = "auctionLastBetLogin";
	private static final String AUCTION_STATUS = "auctionStatus";
	private static final String AUCTION_LAST_BET_TIME = "auctionLastBetTime";
	
	private static final String LOT_ID = "lotId";
	private static final String LOT_NAME = "lotName";
	private static final String LOT_DESCRIPTION = "lotDescription";
	private static final String LOT_QUANTITY = "lotQuantity";
	private static final String LOT_STATUS = "lotStatus";
	private static final String LOT_TYPE = "lotType";
	private static final String LOT_DATE_ADDED = "lotDateAdded";
	private static final String LOT_OWNER = "lotOwner";
	private static final String LOT_PICTURE = "lotPicture";
	
	public ArrayList<Auction> getActiveAuctions(Connection connection) throws SQLException{
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ACTIVE_AUCTIONS_LIST_SQL_STATEMENT)){
			ResultSet result = preparedStatement.executeQuery();
			return parseAuctions(result);
		}
	}

	public boolean completeAuctions(Connection connection, ArrayList<Auction> auctions) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(SET_AUCTION_PENDING_PAYMENT_STATUS_SQL_STATEMENT)){
			for(Auction auction: auctions) {
				preparedStatement.setInt(1, auction.getId());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			return true;
		}
	}
	
	public boolean setLoserParticipations(Connection connection, ArrayList<Auction> auctions) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(SET_LOSER_PARTICIPATIONS_SQL_STATEMENT)){
			for(Auction auction: auctions) {
				preparedStatement.setString(1, auction.getLastBetUser());
				preparedStatement.setInt(2, auction.getId());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			return true;
		}
	}
	
	public boolean setWinParticipations(Connection connection, ArrayList<Auction> auctions) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(SET_WIN_PARTICIPATIONS_SQL_STATEMENT)){
			for(Auction auction: auctions) {
				preparedStatement.setString(1, auction.getLastBetUser());
				preparedStatement.setInt(2, auction.getId());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			return true;
		}
	}
	
	private ArrayList<Auction> parseAuctions(ResultSet result) throws SQLException {
		ArrayList<Auction> auctions = new ArrayList<>();
		Auction auction = null;
		while (result.next()) {
			Bet minBet = new Bet();
			Bet currentBet = new Bet();
			auction = new Auction();
			auction.setId(result.getInt(AUCTION_ID));
			auction.setStartTime(result.getDate(AUCTION_START_TIME));
			auction.setEndTime(result.getDate(AUCTION_END_TIME));
			minBet.setValue(result.getDouble(AUCTION_MINIMUM_BET));
			auction.setMinBet(minBet);;
			currentBet.setValue(result.getDouble(AUCTION_CURRENT_BET));
			auction.setCurrentBet(currentBet);;
			auction.setLastBetUser(result.getString(AUCTION_LAST_BET_USER));
			auction.setStatus(AuctionStatus.valueOf(result.getString(AUCTION_STATUS)));
			auction.setType(AuctionType.valueOf(result.getString(AUCTION_TYPE)));
			auction.setLastBetTime(result.getDate(AUCTION_LAST_BET_TIME));
			auction.setLot(parseLot(result));
			auctions.add(auction);
		}
		return auctions;
	}
	
	private Lot parseLot(ResultSet result) throws SQLException {
		Lot lot = new Lot();
		lot.setId(result.getInt(LOT_ID));
		lot.setName(result.getString(LOT_NAME));
		lot.setDescription(result.getString(LOT_DESCRIPTION));
		lot.setQuantity(result.getInt(LOT_QUANTITY));
		lot.setOwner(result.getString(LOT_OWNER));
		lot.setStatus(LotStatus.valueOf(result.getString(LOT_STATUS)));
		lot.setType(LotType.valueOf(result.getString(LOT_TYPE)));
		lot.setAdded(result.getDate(LOT_DATE_ADDED));
		lot.setPicture(result.getString(LOT_PICTURE));
		return lot;
	}

}
