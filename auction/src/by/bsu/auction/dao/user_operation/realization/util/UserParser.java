package by.bsu.auction.dao.user_operation.realization.util;

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
import by.tc.auction.entity.User;
import by.tc.auction.entity.UserRole;

public class UserParser {

	private static final String USER_LOGIN = "userLogin";
	private static final String USER_PASSWORD = "userPassword";
	private static final String USER_ROLE = "userRole";
	private static final String USER_SURNAME = "userSurname";
	private static final String USER_NAME = "userName";
	private static final String USER_COUNTRY = "userCountry";
	private static final String USER_EMAIL = "userEmail";
	private static final String USER_PHONE = "userPhone";
	private static final String USER_PASSPORT_ID = "userPassportId";
	private static final String USER_PASSPORT_ISSUED_BY = "userPassportIssuedBy";	
	private static final String USER_IS_BLOCKED = "isBlocked";
	private static final String USER_PICTURE = "userPicture";
	
	private static final String LOT_ID = "lotId";
	private static final String LOT_NAME = "lotName";
	private static final String LOT_DESCRIPTION = "lotDescription";
	private static final String LOT_QUANTITY = "lotQuantity";
	private static final String LOT_STATUS = "lotStatus";
	private static final String LOT_TYPE = "lotType";
	private static final String LOT_DATE_ADDED = "lotDateAdded";
	private static final String LOT_OWNER = "lotOwner";
	private static final String LOT_PICTURE = "lotPicture";
	
	private static final String AUCTION_ID = "auctionId";
	private static final String AUCTION_TYPE = "auctionType";
	private static final String AUCTION_START_TIME = "auctionStartTime";
	private static final String AUCTION_END_TIME = "auctionEndTime";
	private static final String AUCTION_MINIMUM_BET = "auctionMinimumPrice";
	private static final String AUCTION_CURRENT_BET = "auctionCurrentPrice";
	private static final String AUCTION_LAST_BET_USER = "auctionLastBetLogin";
	private static final String AUCTION_STATUS = "auctionStatus";
	private static final String AUCTION_LAST_BET_TIME = "auctionLastBetTime";

	private static final UserParser instance = new UserParser();
	
	private UserParser() {}
	
	public static UserParser getInstance() {
		return instance;
	}
	
	public User parseUser(ResultSet result) throws SQLException {
		User user = null;
		if (result.next()) {
			user = new User();
			user.setLogin(result.getString(USER_LOGIN));
			user.setSurname(result.getString(USER_SURNAME));
			user.setName(result.getString(USER_NAME));
			user.setPassword(result.getString(USER_PASSWORD));
			user.setPassportId(result.getString(USER_PASSPORT_ID));
			user.setPassportIssuedBy(result.getString(USER_PASSPORT_ISSUED_BY));
			user.setPhone(result.getString(USER_PHONE));
			user.setEmail(result.getString(USER_EMAIL));
			user.setCountry(result.getString(USER_COUNTRY));
			user.setBlocked(result.getBoolean(USER_IS_BLOCKED));
			user.setRole(UserRole.valueOf(result.getString(USER_ROLE)));
			user.setPicture(result.getString(USER_PICTURE));
		}
		return user;
	}
	
	public ArrayList<User> parseUsers(ResultSet result) throws SQLException {
		ArrayList<User> users = new ArrayList<>();
		User user = null;
		while (result.next()) {
			user = new User();
			user.setLogin(result.getString(USER_LOGIN));
			user.setSurname(result.getString(USER_SURNAME));
			user.setName(result.getString(USER_NAME));
			user.setPassword(result.getString(USER_PASSWORD));
			user.setPassportId(result.getString(USER_PASSPORT_ID));
			user.setPassportIssuedBy(result.getString(USER_PASSPORT_ISSUED_BY));
			user.setPhone(result.getString(USER_PHONE));
			user.setEmail(result.getString(USER_EMAIL));
			user.setCountry(result.getString(USER_COUNTRY));
			user.setBlocked(result.getBoolean(USER_IS_BLOCKED));
			user.setRole(UserRole.valueOf(result.getString(USER_ROLE)));
			user.setPicture(result.getString(USER_PICTURE));
			users.add(user);
		}
		return users;
	}
	
	public ArrayList<Auction> parseUserAuctions(ResultSet result) throws SQLException {
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
			auction.setLot(parseAuctionLot(result));
			auctions.add(auction);
		}
		return auctions;
	}
	
	public ArrayList<Lot> parseUserLots(ResultSet result) throws SQLException{
		Lot lot = null;
		ArrayList<Lot> lots = new ArrayList<>();
		while (result.next()) {
			lot = new Lot();
			lot.setId(result.getInt(LOT_ID));
			lot.setName(result.getString(LOT_NAME));
			lot.setDescription(result.getString(LOT_DESCRIPTION));
			lot.setQuantity(result.getInt(LOT_QUANTITY));
			lot.setOwner(result.getString(LOT_OWNER));
			lot.setStatus(LotStatus.valueOf(result.getString(LOT_STATUS)));
			lot.setType(LotType.valueOf(result.getString(LOT_TYPE)));
		    lot.setAdded(result.getDate(LOT_DATE_ADDED));
		    lot.setPicture(result.getString(LOT_PICTURE));
			lots.add(lot);
		}
		return lots;
	}
	
	private Lot parseAuctionLot(ResultSet result) throws SQLException {
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
