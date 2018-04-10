package by.tc.auction.dao.auction_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.tc.auction.dao.util.Parser;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Locale;

public class AuctionInfoGetter {

	private static final String GET_AUCTIONS_LIST_SQL_STATEMENT = "SELECT l.l_id AS lotId, l.l_locale AS lotLocale, l.l_name AS lotName, l.l_description AS lotDescription, l.l_quantity AS lotQuantity, l.l_picture AS lotPicture, l.l_date_added AS lotDateAdded, l.l_type AS lotType, l.l_status AS lotStatus, l.su_owner_login AS lotOwner, a.a_id AS auctionId, a.a_last_bet_time AS auctionLastBetTime, a.a_start_time AS auctionStartTime, a.a_end_time AS auctionEndTime, a.a_status AS auctionStatus, l.l_type AS lotType, l.l_status AS lotStatus, a.a_minimum_price AS auctionMinimumPrice, a.a_current_price AS auctionCurrentPrice, au_t.at_type_name AS auctionType, a.su_login_last_bet AS auctionLastBetLogin FROM auction.auctions AS a INNER JOIN auction.lots AS l ON l.l_id = a.l_id INNER JOIN auction.auctions_type AS au_t ON au_t.at_id = a.auctions_type_at_id WHERE l.l_locale=? ORDER BY a.a_start_time DESC";
	private static final String GET_AUCTION_INFO_SQL_STATEMENT = "SELECT l.l_id AS lotId, l.l_locale AS lotLocale, l.l_name AS lotName, l.l_description AS lotDescription, l.l_quantity AS lotQuantity, l.l_picture AS lotPicture, l.l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, l.su_owner_login AS lotOwner, a.a_id AS auctionId, a.a_last_bet_time AS auctionLastBetTime, a.a_start_time AS auctionStartTime, a.a_end_time AS auctionEndTime, a.a_status AS auctionStatus, l.l_type AS lotType, l.l_status AS lotStatus, a.a_minimum_price AS auctionMinimumPrice, a.a_current_price AS auctionCurrentPrice, au_t.at_type_name AS auctionType, a.su_login_last_bet AS auctionLastBetLogin FROM auction.auctions AS a INNER JOIN auction.lots AS l ON l.l_id = a.l_id INNER JOIN auction.auctions_type AS au_t ON au_t.at_id = a.auctions_type_at_id WHERE a.a_id=?";
	
	private static final Logger logger = Logger.getLogger(AuctionInfoGetter.class);
	
	private final Parser parser = Parser.getInstance();
	
	public Auction getAuctionInfo(Connection connection, Integer auctionId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_AUCTION_INFO_SQL_STATEMENT)){
			preparedStatement.setInt(1, auctionId);
			ResultSet result = preparedStatement.executeQuery();
			return parser.parseAuction(result);
		} catch (SQLException e){
			logger.error("Error in AuctionInfoGetter", e);
			throw e;
		}
	}
	
	public ArrayList<Auction> getAuctions(Connection connection, Locale locale) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_AUCTIONS_LIST_SQL_STATEMENT)){
			preparedStatement.setString(1, locale.toString());
			ResultSet result = preparedStatement.executeQuery();
			return parser.parseAuctions(result);
		} catch (SQLException e){
			logger.error("Error in AuctionInfoGetter", e);
			throw e;
		}
	}
}
