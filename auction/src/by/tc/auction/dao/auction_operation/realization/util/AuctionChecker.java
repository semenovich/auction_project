package by.tc.auction.dao.auction_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class AuctionChecker {

	private static final String CHECK_IS_LOT_USED_SQL_STATEMENT = "SELECT * FROM auction.auctions WHERE l_id=?";
	private static final String CHECK_LOT_EXISTENCE_SQL_STATEMENT = "SELECT * FROM auction.lots WHERE l_id=?";
	private static final String CHECK_AUCTION_EXISTENCE_SQL_STATEMENT = "SELECT * FROM auction.auctions WHERE a_id=?";
	
	private static final Logger logger = Logger.getLogger(AuctionChecker.class);
	
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
