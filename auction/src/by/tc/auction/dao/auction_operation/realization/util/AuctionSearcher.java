package by.tc.auction.dao.auction_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.tc.auction.dao.util.EntityCreator;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.LotType;

/**
 * A class is used to create and execute a query to a database to search auctions in a database.
 * @author semenovich
 *
 */
public class AuctionSearcher {

	private static final String GET_AUCTIONS_LIST_BY_SEARCHING_SQL_STATEMENT = "SELECT l.l_id AS lotId, l.l_locale AS lotLocale, l.l_name AS lotName, l.l_description AS lotDescription, l.l_quantity AS lotQuantity, l.l_picture AS lotPicture, l.l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, l.su_owner_login AS lotOwner, a.a_id AS auctionId, a.a_start_time AS auctionStartTime, a.a_end_time AS auctionEndTime, a.a_status AS auctionStatus, a.a_minimum_price AS auctionMinimumPrice, au_t.at_type_name AS auctionType, upib.su_login AS auctionLastBetLogin, upib.upib_bet AS auctionCurrentPrice, upib.upib_last_bet_time AS auctionLastBetTime FROM auction.auctions AS a INNER JOIN auction.lots AS l ON l.l_id = a.l_id INNER JOIN auction.auctions_type AS au_t ON au_t.at_id = a.auctions_type_at_id LEFT JOIN auction.user_participation_in_bidding AS upib ON upib.a_id = a.a_id AND upib.upib_bet = (SELECT MAX(upib_bet) FROM auction.user_participation_in_bidding upib_max WHERE upib_max.a_id = a.a_id) WHERE UPPER(l.l_name) LIKE UPPER(?) AND l.l_locale=? ORDER BY a.a_start_time DESC";
	private static final String GET_AUCTIONS_LIST_BY_LOT_TYPE_SQL_STATEMENT = "SELECT l.l_id AS lotId, l.l_locale AS lotLocale, l.l_name AS lotName, l.l_description AS lotDescription, l.l_quantity AS lotQuantity, l.l_picture AS lotPicture, l.l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, l.su_owner_login AS lotOwner, a.a_id AS auctionId, a.a_start_time AS auctionStartTime, a.a_end_time AS auctionEndTime, a.a_status AS auctionStatus, a.a_minimum_price AS auctionMinimumPrice, au_t.at_type_name AS auctionType, upib.su_login AS auctionLastBetLogin, upib.upib_bet AS auctionCurrentPrice, upib.upib_last_bet_time AS auctionLastBetTime FROM auction.auctions AS a INNER JOIN auction.lots AS l ON l.l_id = a.l_id INNER JOIN auction.auctions_type AS au_t ON au_t.at_id = a.auctions_type_at_id LEFT JOIN auction.user_participation_in_bidding AS upib ON upib.a_id = a.a_id AND upib.upib_bet = (SELECT MAX(upib_bet) FROM auction.user_participation_in_bidding upib_max WHERE upib_max.a_id = a.a_id) WHERE l.l_type=? AND l.l_locale=? ORDER BY a.a_start_time DESC";
	
	private static final Logger logger = Logger.getLogger(AuctionSearcher.class);
	
	private final EntityCreator creator = EntityCreator.getInstance();
	
	/**
	 * Creates and executes query to a database to get a list of auctions by matching a name of a lot. 
	 * @param connection - a connection to a database.
	 * @param searchLine - a search line which will be matched with a lot name.
	 * @param locale - a locale of auctions.
	 * @return An auction list of auctions if such auctions exist in a database. Empty list if such auctions don't exist in a database.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public ArrayList <Auction> getAuctionsBySearching(Connection connection, String searchLine, Locale locale) throws SQLException{
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_AUCTIONS_LIST_BY_SEARCHING_SQL_STATEMENT)){
			preparedStatement.setString(1, "%" + searchLine + "%");
			preparedStatement.setString(2, locale.toString());
			ResultSet result = preparedStatement.executeQuery();
			return creator.createAuctions(result);
		} catch (SQLException e){
			logger.error("Error in AuctionSearcher", e);
			throw e;
		}
	}
	
	/**
	 * Creates and executes query to a database to get a list of auctions by searching by the type of a lot.
	 * @param connection - a connection to a database.
	 * @param lotType - a lot type.
	 * @param locale - an auction locale.
	 * @return An auction list of auctions if such auctions exist in a database. Empty list if such auctions don't exist in a database.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public ArrayList <Auction> getAuctionsByLotType(Connection connection, LotType lotType, Locale locale) throws SQLException{
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_AUCTIONS_LIST_BY_LOT_TYPE_SQL_STATEMENT)){
			preparedStatement.setString(1, lotType.toString());
			preparedStatement.setString(2, locale.toString());
			ResultSet result = preparedStatement.executeQuery();
			return creator.createAuctions(result);
		} catch (SQLException e){
			logger.error("Error in AuctionSearcher", e);
			throw e;
		}
	}
}
