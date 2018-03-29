package by.bsu.auction.dao.user_operation.realization.util;

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
import by.tc.auction.entity.User;
import by.tc.auction.entity.UserRole;

public final class UserProcessor {
	
	private static final String GET_USERS_BY_SEARCHING_SQL_STATEMENT = "SELECT u.su_login AS userLogin, u.su_surname AS userSurname, u.su_Name AS userName, u.su_password AS userPassword, u.su_email AS userEmail, u.su_phone AS userPhone, u.su_passport_id AS userPassportId, u.su_passport_issued_by AS userPassportIssuedBy, c.uc_name AS userCountry, u.su_blocked AS isBlocked, r.sur_role_name AS userRole, u.su_picture AS userPicture   FROM auction.site_users u  INNER JOIN auction.site_users_role r ON u.sur_id = r.sur_id  INNER JOIN auction.users_countries c ON u.uc_id = c.uc_id WHERE UPPER(u.su_login) LIKE UPPER(?) ORDER BY u.su_login ASC";
	private static final String GET_USERS_SQL_STATEMENT = "SELECT u.su_login AS userLogin, u.su_surname AS userSurname, u.su_Name AS userName, u.su_password AS userPassword, u.su_email AS userEmail, u.su_phone AS userPhone, u.su_passport_id AS userPassportId, u.su_passport_issued_by AS userPassportIssuedBy, c.uc_name AS userCountry, u.su_blocked AS isBlocked, r.sur_role_name AS userRole, u.su_picture AS userPicture   FROM auction.site_users u  INNER JOIN auction.site_users_role r ON u.sur_id = r.sur_id  INNER JOIN auction.users_countries c ON u.uc_id = c.uc_id ORDER BY u.su_login ASC";
	private static final String GET_USER_SQL_STATEMENT = "SELECT u.su_login AS userLogin, u.su_surname AS userSurname, u.su_Name AS userName, u.su_password AS userPassword, u.su_email AS userEmail, u.su_phone AS userPhone, u.su_passport_id AS userPassportId, u.su_passport_issued_by AS userPassportIssuedBy, c.uc_name AS userCountry, u.su_blocked AS isBlocked, r.sur_role_name AS userRole, u.su_picture AS userPicture   FROM auction.site_users u  INNER JOIN auction.site_users_role r ON u.sur_id = r.sur_id  INNER JOIN auction.users_countries c ON u.uc_id = c.uc_id  WHERE u.su_login=?";
	private static final String CHECK_USER_LOGIN_AND_PASSWORD_SQL_STATEMENT = "SELECT u.su_login AS userLogin, u.su_surname AS userSurname, u.su_Name AS userName, u.su_password AS userPassword, u.su_email AS userEmail, u.su_phone AS userPhone, u.su_passport_id AS userPassportId, u.su_passport_issued_by AS userPassportIssuedBy, c.uc_name AS userCountry, u.su_blocked AS isBlocked, r.sur_role_name AS userRole, u.su_picture AS userPicture FROM auction.site_users u INNER JOIN auction.site_users_role r ON u.sur_id = r.sur_id INNER JOIN auction.users_countries c ON u.uc_id = c.uc_id WHERE u.su_login=? AND u.su_password=MD5(?)";
	private static final String CHECK_USER_LOGIN_SQL_STATEMENT = "SELECT su_login AS userLogin FROM auction.site_users WHERE su_login=?";
	private static final String GET_USER_LOTS_SQL_STATEMENT = "SELECT l_id AS lotId, l_name AS lotName, l_description AS lotDescription, l_quantity AS lotQuantity, l_picture AS lotPicture, l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, su_owner_login AS lotOwner FROM auction.lots WHERE su_owner_login=? ORDER BY l_date_added DESC";
	private static final String GET_USER_AUCTION_PARTICIPATION_SQL_STATEMENT = "SELECT l.l_id AS lotId, l.l_name AS lotName, l.l_description AS lotDescription, l.l_quantity AS lotQuantity, l.l_picture AS lotPicture, l.l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, l.su_owner_login AS lotOwner, a.a_id AS auctionId, a.a_last_bet_time AS auctionLastBetTime, a.a_start_time AS auctionStartTime, a.a_end_time AS auctionEndTime, a.a_status AS auctionStatus, l.l_type AS lotType, l.l_status AS lotStatus, a.a_minimum_price AS auctionMinimumPrice, a.a_current_price AS auctionCurrentPrice, au_t.at_type_name AS auctionType, a.su_login_last_bet AS auctionLastBetLogin FROM auction.site_users AS su INNER JOIN auction.user_participation_in_bidding AS upib ON upib.su_login = su.su_login INNER JOIN auction.auctions AS a ON upib.a_id = a.a_id INNER JOIN auction.lots AS l ON l.l_id = a.l_id INNER JOIN auction.auctions_type AS au_t ON au_t.at_id = a.auctions_type_at_id WHERE su.su_login=? ORDER BY l_date_added DESC";
	private static final String EDIT_USER_INFO_SQL_STATEMENT = "UPDATE auction.site_users SET su_surname=?, su_name=?, su_password=?, su_email=?, su_phone=?, su_passport_id=?, su_passport_issued_by=?, su_picture=? WHERE su_login=?";
	private static final String GET_USER_WIN_LOTS_SQL_STATEMENT = "SELECT l.l_id AS lotId, l.l_name AS lotName, l.l_description AS lotDescription, l.l_quantity AS lotQuantity, l.l_picture AS lotPicture, l.l_date_added AS lotDateAdded, l.l_type AS lotType, l.l_status AS lotStatus, l.su_owner_login AS lotOwner FROM auction.site_users AS su INNER JOIN auction.user_participation_in_bidding AS upib ON upib.su_login = su.su_login INNER JOIN auction.auctions AS a ON upib.a_id = a.a_id INNER JOIN auction.lots AS l ON l.l_id = a.l_id WHERE su.su_login=? AND upib.upib_status='WON' AND a.a_status='COMPLETED' ORDER BY l_date_added DESC";
	
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

	public boolean isUserExist(Connection connection, String login) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_LOGIN_SQL_STATEMENT)){
			preparedStatement.setString(1, login);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		}
	}
	
	public boolean isUserExist(Connection connection, String login, String password) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_LOGIN_AND_PASSWORD_SQL_STATEMENT)){
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		}
	}
	
	public ArrayList<User> getUsers(Connection connection) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS_SQL_STATEMENT)){
			ResultSet result = preparedStatement.executeQuery();
			return parseUsers(result);
		}
	}
	
	public ArrayList<User> getUsersBySearching(Connection connection, String searchLine) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS_BY_SEARCHING_SQL_STATEMENT)){
			preparedStatement.setString(1, "%" + searchLine + "%");
			ResultSet result = preparedStatement.executeQuery();
			return parseUsers(result);
		}
	}
		
	public User getPersonalUserInfo(Connection connection, String login) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_SQL_STATEMENT)){
			preparedStatement.setString(1, login);
			ResultSet result = preparedStatement.executeQuery();
			return parseUser(result);
		}
	}
	
	public boolean edit(Connection connection, User user) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(EDIT_USER_INFO_SQL_STATEMENT)) {
			fillEditPreparedStatement(preparedStatement, user);
			preparedStatement.executeUpdate();
			return true;
		}
	}
	
	public ArrayList<Lot> getUserLots(Connection connection, String login) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_LOTS_SQL_STATEMENT)){
			preparedStatement.setString(1, login);
			ResultSet result = preparedStatement.executeQuery();
			return parseUserLots(result);
		}
	}
	
	public ArrayList<Lot> getUserWinLots(Connection connection, String login) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_WIN_LOTS_SQL_STATEMENT)){
			preparedStatement.setString(1, login);
			ResultSet result = preparedStatement.executeQuery();
			return parseUserLots(result);
		}
	}
	
	public ArrayList<Auction> getUserParticipations(Connection connection, String login) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_AUCTION_PARTICIPATION_SQL_STATEMENT)){
			preparedStatement.setString(1, login);
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
			auction.setLot(parseAuctionLot(result));
			auctions.add(auction);
		}
		return auctions;
	}
	
	private User parseUser(ResultSet result) throws SQLException {
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
	
	private ArrayList<User> parseUsers(ResultSet result) throws SQLException {
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
	
	private ArrayList<Lot> parseUserLots(ResultSet result) throws SQLException{
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

	private void fillEditPreparedStatement(PreparedStatement preparedStatement, User user) throws SQLException {
		preparedStatement.setString(1, user.getSurname());
		preparedStatement.setString(2, user.getName());
		preparedStatement.setString(3, user.getPassword());
		preparedStatement.setString(4, user.getEmail());
		preparedStatement.setString(5, user.getPhone());
		preparedStatement.setString(6, user.getPassportId());
		preparedStatement.setString(7, user.getPassportIssuedBy());
		preparedStatement.setString(8, user.getPicture());
		preparedStatement.setString(9, user.getLogin());
	}
}
