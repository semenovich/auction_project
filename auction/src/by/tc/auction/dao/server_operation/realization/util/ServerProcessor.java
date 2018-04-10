package by.tc.auction.dao.server_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.tc.auction.dao.util.Parser;
import by.tc.auction.entity.Auction;

public class ServerProcessor {
	
	private static final String GET_ACTIVE_AUCTIONS_LIST_SQL_STATEMENT = "SELECT l.l_id AS lotId, l.l_name AS lotName, l.l_description AS lotDescription, l.l_quantity AS lotQuantity, l.l_picture AS lotPicture, l.l_date_added AS lotDateAdded, l.l_type AS lotType, l.l_status AS lotStatus, l.su_owner_login AS lotOwner, a.a_id AS auctionId, a.a_last_bet_time AS auctionLastBetTime, a.a_start_time AS auctionStartTime, a.a_end_time AS auctionEndTime, a.a_status AS auctionStatus, l.l_type AS lotType, l.l_status AS lotStatus, a.a_minimum_price AS auctionMinimumPrice, a.a_current_price AS auctionCurrentPrice, au_t.at_type_name AS auctionType, a.su_login_last_bet AS auctionLastBetLogin, l.l_locale AS lotLocale FROM auction.auctions AS a INNER JOIN auction.lots AS l ON l.l_id = a.l_id INNER JOIN auction.auctions_type AS au_t ON au_t.at_id = a.auctions_type_at_id WHERE a.a_status='ACTIVE' ORDER BY a.a_start_time DESC";
	private static final String SET_AUCTION_PENDING_PAYMENT_STATUS_SQL_STATEMENT = "UPDATE auction.auctions SET a_status='PENDING_PAYMENT' WHERE a_id=?";
	private static final String SET_LOSER_PARTICIPATIONS_SQL_STATEMENT = "UPDATE auction.user_participation_in_bidding SET upib_status='LOST' WHERE NOT su_login=? and a_id=?";
	private static final String SET_WIN_PARTICIPATIONS_SQL_STATEMENT = "UPDATE auction.user_participation_in_bidding SET upib_status='WON' WHERE su_login=? and a_id=?";
	
	private static final Logger logger = Logger.getLogger(ServerProcessor.class);
	
	private static final Parser parser = Parser.getInstance();
	
	public ArrayList<Auction> getActiveAuctions(Connection connection) throws SQLException{
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ACTIVE_AUCTIONS_LIST_SQL_STATEMENT)){
			ResultSet result = preparedStatement.executeQuery();
			return parser.parseAuctions(result);
		} catch (SQLException e){
			logger.error("Error in ServerProcessor", e);
			throw e;
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
		} catch (SQLException e){
			logger.error("Error in ServerProcessor", e);
			throw e;
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
		} catch (SQLException e){
			logger.error("Error in ServerProcessor", e);
			throw e;
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
		} catch (SQLException e){
			logger.error("Error in ServerProcessor", e);
			throw e;
		}
	}
}
