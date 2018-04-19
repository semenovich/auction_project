package by.tc.auction.dao.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionStatus;
import by.tc.auction.entity.AuctionType;
import by.tc.auction.entity.Bet;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotStatus;
import by.tc.auction.entity.LotType;
import by.tc.auction.entity.User;
import by.tc.auction.entity.UserRole;

public class EntityCreator {

	private static final String USER_LOGIN = "userLogin";
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
	private static final String LOT_LOCALE = "lotLocale";
	
	private static final String AUCTION_ID = "auctionId";
	private static final String AUCTION_TYPE = "auctionType";
	private static final String AUCTION_START_TIME = "auctionStartTime";
	private static final String AUCTION_END_TIME = "auctionEndTime";
	private static final String AUCTION_MINIMUM_BET = "auctionMinimumPrice";
	private static final String AUCTION_CURRENT_BET = "auctionCurrentPrice";
	private static final String AUCTION_LAST_BET_USER = "auctionLastBetLogin";
	private static final String AUCTION_STATUS = "auctionStatus";
	private static final String AUCTION_LAST_BET_TIME = "auctionLastBetTime";
	
	private static final Logger logger = Logger.getLogger(EntityCreator.class);
	
	private static final EntityCreator instance = new EntityCreator();
	
	private EntityCreator() {};
	
	public static EntityCreator getInstance() {
		return instance;
	}
	
	public Lot createLot(ResultSet result) throws SQLException {
		try{
			Lot lot = null;
			if (result.next()) {
				lot = new Lot();
				lot.setId(result.getInt(LOT_ID));
				lot.setName(result.getString(LOT_NAME));
				lot.setDescription(result.getString(LOT_DESCRIPTION));
				lot.setQuantity(result.getInt(LOT_QUANTITY));
				lot.setOwner(result.getString(LOT_OWNER));
				lot.setStatus(LotStatus.valueOf(result.getString(LOT_STATUS)));
				lot.setType(LotType.valueOf(result.getString(LOT_TYPE)));
				lot.setAdded(result.getTimestamp(LOT_DATE_ADDED));
				lot.setPicture(result.getString(LOT_PICTURE));
				lot.setLocale(Locale.valueOf(result.getString(LOT_LOCALE)));
			}
			return lot;
		} catch (SQLException e) {
			logger.error("Error in Parser", e);
			throw e;
		}
	}
	
	private Lot createAuctionLot(ResultSet result) throws SQLException {
		try {
			Lot lot = new Lot();
			lot.setId(result.getInt(LOT_ID));
			lot.setName(result.getString(LOT_NAME));
			lot.setDescription(result.getString(LOT_DESCRIPTION));
			lot.setQuantity(result.getInt(LOT_QUANTITY));
			lot.setOwner(result.getString(LOT_OWNER));
			lot.setStatus(LotStatus.valueOf(result.getString(LOT_STATUS)));
			lot.setType(LotType.valueOf(result.getString(LOT_TYPE)));
			lot.setAdded(result.getTimestamp(LOT_DATE_ADDED));
			lot.setPicture(result.getString(LOT_PICTURE));
			lot.setLocale(Locale.valueOf(result.getString(LOT_LOCALE)));
			return lot;
		} catch (SQLException e) {
			logger.error("Error in Parser", e);
			throw e;
		}
	}
	
	public ArrayList<Lot> createLots(ResultSet result) throws SQLException{
		try {
			ArrayList<Lot> lots = new ArrayList<>();
			Lot lot = null;
			while(result.next()) {
				lot = new Lot();
				lot.setId(result.getInt(LOT_ID));
				lot.setName(result.getString(LOT_NAME));
				lot.setDescription(result.getString(LOT_DESCRIPTION));
				lot.setQuantity(result.getInt(LOT_QUANTITY));
				lot.setOwner(result.getString(LOT_OWNER));
				lot.setStatus(LotStatus.valueOf(result.getString(LOT_STATUS)));
				lot.setType(LotType.valueOf(result.getString(LOT_TYPE)));
			    lot.setAdded(result.getTimestamp(LOT_DATE_ADDED));
			    lot.setPicture(result.getString(LOT_PICTURE));
			    lot.setLocale(Locale.valueOf(result.getString(LOT_LOCALE)));
			    lots.add(lot);
			}
			return lots;
		} catch (SQLException e) {
			logger.error("Error in Parser", e);
			throw e;
		}
	}
	
	public ArrayList<Auction> createAuctions(ResultSet result) throws SQLException {
		try {
			ArrayList<Auction> auctions = new ArrayList<>();
			Auction auction = null;
			while (result.next()) {
				Bet minBet = new Bet();
				Bet lastBet = new Bet();
				auction = new Auction();
				auction.setId(result.getInt(AUCTION_ID));
				auction.setStartTime(result.getTimestamp(AUCTION_START_TIME));
				auction.setEndTime(result.getTimestamp(AUCTION_END_TIME));
				minBet.setValue(result.getDouble(AUCTION_MINIMUM_BET));
				auction.setMinBet(minBet);;
				lastBet.setValue(result.getDouble(AUCTION_CURRENT_BET));
				auction.setLastBet(lastBet);;
				auction.setLastBetUser(result.getString(AUCTION_LAST_BET_USER));
				auction.setStatus(AuctionStatus.valueOf(result.getString(AUCTION_STATUS)));
				auction.setType(AuctionType.valueOf(result.getString(AUCTION_TYPE)));
				auction.setLastBetTime(result.getTimestamp(AUCTION_LAST_BET_TIME));
				auction.setLot(createAuctionLot(result));
				auctions.add(auction);
			}
			return auctions;
		} catch (SQLException e) {
			logger.error("Error in Parser", e);
			throw e;
		}
	}
	
	public Auction createAuction(ResultSet result) throws SQLException {
		try {
			Auction auction = null;
			if (result.next()) {
				Bet minBet = new Bet();
				Bet lastBet = new Bet();
				auction = new Auction();
				auction.setId(result.getInt(AUCTION_ID));
				auction.setStartTime(result.getTimestamp(AUCTION_START_TIME));
				auction.setEndTime(result.getTimestamp(AUCTION_END_TIME));
				minBet.setValue(result.getDouble(AUCTION_MINIMUM_BET));
				auction.setMinBet(minBet);;
				lastBet.setValue(result.getDouble(AUCTION_CURRENT_BET));
				auction.setLastBet(lastBet);;
				auction.setLastBetUser(result.getString(AUCTION_LAST_BET_USER));
				auction.setStatus(AuctionStatus.valueOf(result.getString(AUCTION_STATUS)));
				auction.setType(AuctionType.valueOf(result.getString(AUCTION_TYPE)));
				auction.setLastBetTime(result.getTimestamp(AUCTION_LAST_BET_TIME));
				auction.setLot(createAuctionLot(result));
			}
			return auction;
		} catch (SQLException e) {
			logger.error("Error in Parser", e);
			throw e;
		}
	}
	
	public User createUser(ResultSet result) throws SQLException {
		try {
			User user = null;
			if (result.next()) {
				user = new User();
				user.setLogin(result.getString(USER_LOGIN));
				user.setSurname(result.getString(USER_SURNAME));
				user.setName(result.getString(USER_NAME));
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
		} catch (SQLException e) {
			logger.error("Error in Parser", e);
			throw e;
		}
	}
	
	public ArrayList<User> createUsers(ResultSet result) throws SQLException {
		try {
			ArrayList<User> users = new ArrayList<>();
			User user = null;
			while (result.next()) {
				user = new User();
				user.setLogin(result.getString(USER_LOGIN));
				user.setSurname(result.getString(USER_SURNAME));
				user.setName(result.getString(USER_NAME));
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
		} catch (SQLException e) {
			logger.error("Error in Parser", e);
			throw e;
		}
	}
}
