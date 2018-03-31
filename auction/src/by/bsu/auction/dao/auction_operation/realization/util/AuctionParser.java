package by.bsu.auction.dao.auction_operation.realization.util;

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

public class AuctionParser {

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
	
	private static final AuctionParser instance = new AuctionParser();
	
	private AuctionParser() {}
	
	public static AuctionParser getInstance() {
		return instance;
	}
	
	public ArrayList<Auction> parseAuctions(ResultSet result) throws SQLException {
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
	
	public Auction parseAuction(ResultSet result) throws SQLException {
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

}
