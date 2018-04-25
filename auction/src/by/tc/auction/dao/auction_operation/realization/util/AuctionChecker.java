package by.tc.auction.dao.auction_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * A class is used to create and execute an auction existence, a lot existence and a using of a lot in other auctions checking query to a database.
 * @author semenovich
 *
 */
public class AuctionChecker {

	private static final String CHECK_IS_LOT_USED_SQL_STATEMENT = "SELECT * FROM auction.auctions WHERE l_id=?";
	private static final String CHECK_LOT_EXISTENCE_SQL_STATEMENT = "SELECT * FROM auction.lots WHERE l_id=?";
	private static final String CHECK_AUCTION_EXISTENCE_SQL_STATEMENT = "SELECT * FROM auction.auctions WHERE a_id=?";
	
	private static final Logger logger = Logger.getLogger(AuctionChecker.class);
	
	/**
	 * Creates and executes an auction existence checking query to a database.
	 * @param connection - connection to a database
	 * @param auctionId - an auction ID.
	 * @return {@code true} - if an auction exists. {@code false} - if an auction doesn't exist.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public boolean isAuctionExist(Connection connection, Integer auctionId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_AUCTION_EXISTENCE_SQL_STATEMENT)) {
			preparedStatement.setInt(1, auctionId);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		} catch (SQLException e){
			logger.error("Error in AuctionChecker", e);
			throw e;
		}
	}
	
	/**
	 * Creates and executes a lot existence checking query to a database.
	 * @param connection - a connection to a database.
	 * @param lotId - a lot ID.
	 * @return {@code true} - if a lot exists. {@code false} - if a lot doesn't exist.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public boolean isLotExist(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOT_EXISTENCE_SQL_STATEMENT)) {
			preparedStatement.setInt(1, lotId);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		} catch (SQLException e){
			logger.error("Error in AuctionChecker", e);
			throw e;
		}
	}
	
	/**
	 * Creates and executes a lot using checking query to a database.
	 * @param connection - a connection to a database. 
	 * @param lotId - an lot ID.
	 * @return {@code true} - if a lot is used. {@code false} - if a lot isn't used.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public boolean isLotUsed(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IS_LOT_USED_SQL_STATEMENT)) {
			preparedStatement.setInt(1, lotId);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		} catch (SQLException e){
			logger.error("Error in AuctionChecker", e);
			throw e;
		}
	}
}
