package by.tc.auction.dao.user_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.tc.auction.dao.util.Parser;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.User;

public class UserInfoGetter {

	private static final String GET_USERS_BY_SEARCHING_SQL_STATEMENT = "SELECT u.su_login AS userLogin, u.su_surname AS userSurname, u.su_Name AS userName, u.su_password AS userPassword, u.su_email AS userEmail, u.su_phone AS userPhone, u.su_passport_id AS userPassportId, u.su_passport_issued_by AS userPassportIssuedBy, c.uc_name AS userCountry, u.su_blocked AS isBlocked, r.sur_role_name AS userRole, u.su_picture AS userPicture   FROM auction.site_users u  INNER JOIN auction.site_users_role r ON u.sur_id = r.sur_id  INNER JOIN auction.users_countries c ON u.uc_id = c.uc_id WHERE UPPER(u.su_login) LIKE UPPER(?) ORDER BY u.su_login ASC";
	private static final String GET_USERS_SQL_STATEMENT = "SELECT u.su_login AS userLogin, u.su_surname AS userSurname, u.su_Name AS userName, u.su_password AS userPassword, u.su_email AS userEmail, u.su_phone AS userPhone, u.su_passport_id AS userPassportId, u.su_passport_issued_by AS userPassportIssuedBy, c.uc_name AS userCountry, u.su_blocked AS isBlocked, r.sur_role_name AS userRole, u.su_picture AS userPicture   FROM auction.site_users u  INNER JOIN auction.site_users_role r ON u.sur_id = r.sur_id  INNER JOIN auction.users_countries c ON u.uc_id = c.uc_id ORDER BY u.su_login ASC";
	private static final String GET_USER_SQL_STATEMENT = "SELECT u.su_login AS userLogin, u.su_surname AS userSurname, u.su_Name AS userName, u.su_password AS userPassword, u.su_email AS userEmail, u.su_phone AS userPhone, u.su_passport_id AS userPassportId, u.su_passport_issued_by AS userPassportIssuedBy, c.uc_name AS userCountry, u.su_blocked AS isBlocked, r.sur_role_name AS userRole, u.su_picture AS userPicture   FROM auction.site_users u  INNER JOIN auction.site_users_role r ON u.sur_id = r.sur_id  INNER JOIN auction.users_countries c ON u.uc_id = c.uc_id  WHERE u.su_login=?";
	private static final String GET_USER_LOTS_SQL_STATEMENT = "SELECT l_id AS lotId, l_name AS lotName, l_description AS lotDescription, l_quantity AS lotQuantity, l_picture AS lotPicture, l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, su_owner_login AS lotOwner, l_locale as lotLocale FROM auction.lots WHERE su_owner_login=? ORDER BY l_date_added DESC";
	private static final String GET_USER_AUCTION_PARTICIPATION_SQL_STATEMENT = "SELECT l.l_id AS lotId, l.l_locale AS lotLocale, l.l_name AS lotName, l.l_description AS lotDescription, l.l_quantity AS lotQuantity, l.l_picture AS lotPicture, l.l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, l.su_owner_login AS lotOwner, a.a_id AS auctionId, a.a_start_time AS auctionStartTime, a.a_end_time AS auctionEndTime, a.a_status AS auctionStatus, a.a_minimum_price AS auctionMinimumPrice, au_t.at_type_name AS auctionType, upib.su_login AS auctionLastBetLogin, upib.upib_bet AS auctionCurrentPrice, upib.upib_last_bet_time AS auctionLastBetTime FROM auction.auctions AS a INNER JOIN auction.lots AS l ON l.l_id = a.l_id INNER JOIN auction.auctions_type AS au_t ON au_t.at_id = a.auctions_type_at_id LEFT JOIN auction.user_participation_in_bidding AS upib ON upib.a_id = a.a_id AND upib.upib_bet = (SELECT MAX(upib_bet) FROM auction.user_participation_in_bidding upib_max WHERE upib_max.a_id = a.a_id) WHERE upib.su_login=? ORDER BY l_date_added DESC";
	private static final String GET_USER_WIN_LOTS_SQL_STATEMENT = "SELECT l.l_id AS lotId, l.l_name AS lotName, l.l_description AS lotDescription, l.l_quantity AS lotQuantity, l.l_picture AS lotPicture, l.l_date_added AS lotDateAdded, l.l_type AS lotType, l.l_status AS lotStatus, l.su_owner_login AS lotOwner, l.l_locale as lotLocale FROM auction.site_users AS su INNER JOIN auction.user_participation_in_bidding AS upib ON upib.su_login = su.su_login INNER JOIN auction.auctions AS a ON upib.a_id = a.a_id INNER JOIN auction.lots AS l ON l.l_id = a.l_id WHERE su.su_login=? AND upib.upib_status='WON' and a.a_status='COMPLETED' ORDER BY l_date_added DESC";
	
	public final Parser parser = Parser.getInstance();
	
	private static final Logger logger = Logger.getLogger(UserInfoGetter.class);
	
	public ArrayList<User> getUsers(Connection connection) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS_SQL_STATEMENT)){
			ResultSet result = preparedStatement.executeQuery();
			return parser.parseUsers(result);
		} catch (SQLException e){
			logger.error("Error in UserInfoGetter", e);
			throw e;
		}
	}
	
	public ArrayList<User> getUsersBySearching(Connection connection, String searchLine) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS_BY_SEARCHING_SQL_STATEMENT)){
			preparedStatement.setString(1, "%" + searchLine + "%");
			ResultSet result = preparedStatement.executeQuery();
			return parser.parseUsers(result);
		} catch (SQLException e){
			logger.error("Error in UserInfoGetter", e);
			throw e;
		}
	}
		
	public User getPersonalUserInfo(Connection connection, String login) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_SQL_STATEMENT)){
			preparedStatement.setString(1, login);
			ResultSet result = preparedStatement.executeQuery();
			return parser.parseUser(result);
		} catch (SQLException e){
			logger.error("Error in UserInfoGetter", e);
			throw e;
		}
	}
	
	public ArrayList<Lot> getUserLots(Connection connection, String login) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_LOTS_SQL_STATEMENT)){
			preparedStatement.setString(1, login);
			ResultSet result = preparedStatement.executeQuery();
			return parser.parseLots(result);
		} catch (SQLException e){
			logger.error("Error in UserInfoGetter", e);
			throw e;
		}
	}
	
	public ArrayList<Lot> getUserWinLots(Connection connection, String login) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_WIN_LOTS_SQL_STATEMENT)){
			preparedStatement.setString(1, login);
			ResultSet result = preparedStatement.executeQuery();
			return parser.parseLots(result);
		} catch (SQLException e){
			logger.error("Error in UserInfoGetter", e);
			throw e;
		}
	}
	
	public ArrayList<Auction> getUserParticipations(Connection connection, String login) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_AUCTION_PARTICIPATION_SQL_STATEMENT)){
			preparedStatement.setString(1, login);
			ResultSet result = preparedStatement.executeQuery();
			return parser.parseAuctions(result);
		} catch (SQLException e){
			logger.error("Error in UserInfoGetter", e);
			throw e;
		}
	}
}
