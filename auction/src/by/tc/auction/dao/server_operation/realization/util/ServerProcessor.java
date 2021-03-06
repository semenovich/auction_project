package by.tc.auction.dao.server_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.log4j.Logger;

import by.tc.auction.dao.util.EntityCreator;
import by.tc.auction.entity.Auction;

/**
 * A class is used to create and execute a server process query to a database.
 * @author semenovich
 *
 */
public class ServerProcessor {
	
	private static final String GET_ACTIVE_AUCTIONS_LIST_SQL_STATEMENT = "SELECT l.l_id AS lotId, l.l_locale AS lotLocale, l.l_name AS lotName, l.l_description AS lotDescription, l.l_quantity AS lotQuantity, l.l_picture AS lotPicture, l.l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, l.su_owner_login AS lotOwner, a.a_id AS auctionId, a.a_start_time AS auctionStartTime, a.a_end_time AS auctionEndTime, a.a_status AS auctionStatus, a.a_minimum_price AS auctionMinimumPrice, au_t.at_type_name AS auctionType, upib.su_login AS auctionLastBetLogin, upib.upib_bet AS auctionCurrentPrice, upib.upib_last_bet_time AS auctionLastBetTime FROM auction.auctions AS a INNER JOIN auction.lots AS l ON l.l_id = a.l_id INNER JOIN auction.auctions_type AS au_t ON au_t.at_id = a.auctions_type_at_id LEFT JOIN auction.user_participation_in_bidding AS upib ON upib.a_id = a.a_id AND upib.upib_bet = (SELECT MAX(upib_bet) FROM auction.user_participation_in_bidding upib_max WHERE upib_max.a_id = a.a_id) WHERE a.a_status='ACTIVE' ORDER BY a.a_start_time DESC";
	private static final String SET_AUCTION_PENDING_PAYMENT_STATUS_SQL_STATEMENT = "UPDATE auction.auctions SET a_status='PENDING_PAYMENT', a_end_time=? WHERE a_id=?";
	private static final String SET_LOSER_PARTICIPATIONS_SQL_STATEMENT = "UPDATE auction.user_participation_in_bidding SET upib_status='LOST' WHERE NOT su_login=? and a_id=?";
	private static final String SET_WIN_PARTICIPATIONS_SQL_STATEMENT = "UPDATE auction.user_participation_in_bidding SET upib_status='WON' WHERE su_login=? and a_id=?";
	
	private static final Logger logger = Logger.getLogger(ServerProcessor.class);
	
	private static final EntityCreator creator = EntityCreator.getInstance();
	
	/**
	 * Creates and executes query to a database to get active auctions.
	 * @param connection - a connection to a database.
	 * @return A list of active auctions in a database. Empty list if active auctions doesn't exist in a database.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public ArrayList<Auction> getActiveAuctions(Connection connection) throws SQLException{
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ACTIVE_AUCTIONS_LIST_SQL_STATEMENT)){
			ResultSet result = preparedStatement.executeQuery();
			return creator.createAuctions(result);
		} catch (SQLException e){
			logger.error("Error in ServerProcessor", e);
			throw e;
		}
	} 	

	/**
	 * Creates and executes query to a database to complete auctions.
	 * After that method setLoserParticipations() and setWinParticipations() must be used.
	 * @param connection - a connection to a database.
	 * @param auctions - a list of auctions which will be completed in a database. Only the ID and the end time fields must be filled in.
	 * @return {@code true}.
	 * @throws SQLException - if a database access error or other errors occurred with database or not all fields are filled in.
	 */
	public boolean completeAuctions(Connection connection, ArrayList<Auction> auctions) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(SET_AUCTION_PENDING_PAYMENT_STATUS_SQL_STATEMENT)){
			Timestamp endTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
			for(Auction auction: auctions) {
				preparedStatement.setTimestamp(1, endTime);
				preparedStatement.setInt(2, auction.getId());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			return true;
		} catch (SQLException e){
			logger.error("Error in ServerProcessor", e);
			throw e;
		}
	}
	
	/**
	 * Creates and executes query to a database to set users loser participations.
	 * Must be used after completeAuctions() method.
	 * @param connection - a connection to a database.
	 * @param auctions - a list of auctions which participants will be set. Only the ID and the last bet user fields must be filled in.
	 * @return {@code true}.
	 * @throws SQLException - if a database access error or other errors occurred with database or not all fields are filled in.
	 */
	public boolean setLoserParticipations(Connection connection, ArrayList<Auction> auctions) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(SET_LOSER_PARTICIPATIONS_SQL_STATEMENT)){
			for(Auction auction: auctions) {
				preparedStatement.setString(1, auction.getLastBetUser());
				preparedStatement.setInt(2, auction.getId());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			return true;
		} catch (SQLException e){
			logger.error("Error in ServerProcessor", e);
			throw e;
		}
	}
	
	/**
	 * Creates and executes query to a database to set users win participations.
	 * Must be used after completeAuctions() method.
	 * @param connection - a connection to a database.
	 * @param auctions - a list of auctions which participants will be set. Only the ID and the last bet user fields must be filled in.
	 * @return {@code true}.
	 * @throws SQLException - if a database access error or other errors occurred with database or not all fields are filled in.
	 */
	public boolean setWinParticipations(Connection connection, ArrayList<Auction> auctions) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(SET_WIN_PARTICIPATIONS_SQL_STATEMENT)){
			for(Auction auction: auctions) {
				preparedStatement.setString(1, auction.getLastBetUser());
				preparedStatement.setInt(2, auction.getId());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			return true;
		} catch (SQLException e){
			logger.error("Error in ServerProcessor", e);
			throw e;
		}
	}
}
