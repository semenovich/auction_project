package by.bsu.auction.dao.auction_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionStatus;
import by.tc.auction.entity.AuctionType;
import by.tc.auction.entity.Bet;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotStatus;
import by.tc.auction.entity.LotType;

public class AuctionProcessor {
	
	private static final String CREATE_AUCTION_SQL_STATEMENT = "INSERT INTO auction.auctions (l_id, a_start_time, a_end_time, a_minimum_price, auctions_type_at_id) VALUES (?, ?, ?, ?, (SELECT at_id FROM auction.auctions_type WHERE at_type_name=?))";
	private static final String SET_LOT_STATUS_SQL_STATEMENT = "UPDATE auction.lots SET l_status=? WHERE l_id=?";
	private static final String CREATE_LOT_SQL_STATEMENT = "INSERT INTO auction.lots (l_name, l_description, l_quantity, l_picture, su_owner_login, l_date_added, l_status, l_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_AUCTIONS_LIST_SQL_STATEMENT = "SELECT l.l_id AS lotId, l.l_name AS lotName, l.l_description AS lotDescription, l.l_quantity AS lotQuantity, l.l_picture AS lotPicture, l.l_date_added AS lotDateAdded, l.l_type AS lotType, l.l_status AS lotStatus, l.su_owner_login AS lotOwner, a.a_id AS auctionId, a.a_last_bet_time AS auctionLastBetTime, a.a_start_time AS auctionStartTime, a.a_end_time AS auctionEndTime, a.a_status AS auctionStatus, l.l_type AS lotType, l.l_status AS lotStatus, a.a_minimum_price AS auctionMinimumPrice, a.a_current_price AS auctionCurrentPrice, au_t.at_type_name AS auctionType, a.su_login_last_bet AS auctionLastBetLogin FROM auction.auctions AS a INNER JOIN auction.lots AS l ON l.l_id = a.l_id INNER JOIN auction.auctions_type AS au_t ON au_t.at_id = a.auctions_type_at_id ORDER BY a.a_start_time DESC";
	private static final String GET_AUCTIONS_LIST_BY_SEARCHING_SQL_STATEMENT = "SELECT l.l_id AS lotId, l.l_name AS lotName, l.l_description AS lotDescription, l.l_quantity AS lotQuantity, l.l_picture AS lotPicture, l.l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, l.su_owner_login AS lotOwner, a.a_id AS auctionId, a.a_last_bet_time AS auctionLastBetTime, a.a_start_time AS auctionStartTime, a.a_end_time AS auctionEndTime, a.a_status AS auctionStatus, l.l_type AS lotType, l.l_status AS lotStatus, a.a_minimum_price AS auctionMinimumPrice, a.a_current_price AS auctionCurrentPrice, au_t.at_type_name AS auctionType, a.su_login_last_bet AS auctionLastBetLogin FROM auction.auctions AS a INNER JOIN auction.lots AS l ON l.l_id = a.l_id INNER JOIN auction.auctions_type AS au_t ON au_t.at_id = a.auctions_type_at_id WHERE UPPER(l.l_name) LIKE UPPER(?) ORDER BY a.a_start_time DESC";
	private static final String GET_AUCTION_INFO_SQL_STATEMENT = "SELECT l.l_id AS lotId, l.l_name AS lotName, l.l_description AS lotDescription, l.l_quantity AS lotQuantity, l.l_picture AS lotPicture, l.l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, l.su_owner_login AS lotOwner, a.a_id AS auctionId, a.a_last_bet_time AS auctionLastBetTime, a.a_start_time AS auctionStartTime, a.a_end_time AS auctionEndTime, a.a_status AS auctionStatus, l.l_type AS lotType, l.l_status AS lotStatus, a.a_minimum_price AS auctionMinimumPrice, a.a_current_price AS auctionCurrentPrice, au_t.at_type_name AS auctionType, a.su_login_last_bet AS auctionLastBetLogin FROM auction.auctions AS a INNER JOIN auction.lots AS l ON l.l_id = a.l_id INNER JOIN auction.auctions_type AS au_t ON au_t.at_id = a.auctions_type_at_id WHERE a.a_id=?";
	
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
	
	public boolean createAuctionFromExistingLot(Connection connection, Auction auction, Integer lotId) throws SQLException {
		connection.setAutoCommit(false);;
		try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_AUCTION_SQL_STATEMENT)) {
			fillCreateAuctionPreparedStatement(preparedStatement, auction, lotId);
			preparedStatement.executeUpdate();
		}
		try(PreparedStatement preparedStatement = connection.prepareStatement(SET_LOT_STATUS_SQL_STATEMENT)){
			preparedStatement.setString(1, LotStatus.ACTIVE.toString());
			preparedStatement.setInt(2, lotId);
			preparedStatement.executeUpdate();
		}
		connection.commit();
		connection.setAutoCommit(true);
		return true;
	}
	
	public boolean createAuctionWithLot(Connection connection, Auction auction, Lot lot) throws SQLException {
		connection.setAutoCommit(false);;
		Integer lotId = null;
		try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_LOT_SQL_STATEMENT, PreparedStatement.RETURN_GENERATED_KEYS)) {
			fillCreateLotPreparedStatement(preparedStatement, lot);
			preparedStatement.executeUpdate();
			ResultSet result = preparedStatement.getGeneratedKeys();
			if (result.next()) {
				lotId = result.getInt(1);
			}
		}
		try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_AUCTION_SQL_STATEMENT)) {
			fillCreateAuctionPreparedStatement(preparedStatement, auction, lotId);
			preparedStatement.executeUpdate();
		}
		connection.commit();
		connection.setAutoCommit(true);
		return true;
	}
	
	public Auction getAuctionInfo(Connection connection, Integer auctionId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_AUCTION_INFO_SQL_STATEMENT)){
			preparedStatement.setInt(1, auctionId);
			ResultSet result = preparedStatement.executeQuery();
			return parseAuction(result);
		}
	}
	
	public ArrayList<Auction> getAuctions(Connection connection) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_AUCTIONS_LIST_SQL_STATEMENT)){
			ResultSet result = preparedStatement.executeQuery();
			return parseAuctions(result);
		}
	}
	
	public ArrayList <Auction> getAuctionsBySearching(Connection connection, String searchLine) throws SQLException{
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_AUCTIONS_LIST_BY_SEARCHING_SQL_STATEMENT)){
			preparedStatement.setString(1, "%" + searchLine + "%");
			ResultSet result = preparedStatement.executeQuery();
			return parseAuctions(result);
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
	
	private Auction parseAuction(ResultSet result) throws SQLException {
		Auction auction = null;
		if (result.next()) {
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
		}
		return auction;
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
	
	private void fillCreateAuctionPreparedStatement(PreparedStatement preparedStatement, Auction auction, Integer lotId) throws SQLException {
		preparedStatement.setInt(1, lotId);;
		preparedStatement.setDate(2, auction.getStartTime());
		preparedStatement.setDate(3, auction.getEndTime());
		preparedStatement.setDouble(4, auction.getMinBet().getValue());
		preparedStatement.setString(5, auction.getType().toString());
		
	}
	
	private void fillCreateLotPreparedStatement(PreparedStatement preparedStatement, Lot lot) throws SQLException {
		preparedStatement.setString(1, lot.getName());
		preparedStatement.setString(2, lot.getDescription());
		preparedStatement.setInt(3, lot.getQuantity());
		preparedStatement.setString(4, lot.getPicture());
		preparedStatement.setString(5, lot.getOwner());
		preparedStatement.setDate(6, lot.getAdded());
		preparedStatement.setString(7, LotStatus.ACTIVE.toString());
		preparedStatement.setString(8, lot.getType().toString());
	}
}
