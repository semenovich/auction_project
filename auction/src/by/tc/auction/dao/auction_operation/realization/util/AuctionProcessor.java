package by.tc.auction.dao.auction_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotStatus;

public class AuctionProcessor {
	
	private static final String CREATE_AUCTION_SQL_STATEMENT = "INSERT INTO auction.auctions (l_id, a_start_time, a_end_time, a_minimum_price, auctions_type_at_id) VALUES (?, ?, ?, ?, (SELECT at_id FROM auction.auctions_type WHERE at_type_name=?))";
	private static final String SET_LOT_STATUS_SQL_STATEMENT = "UPDATE auction.lots SET l_status=? WHERE l_id=?";
	private static final String CREATE_LOT_SQL_STATEMENT = "INSERT INTO auction.lots (l_name, l_description, l_quantity, l_picture, su_owner_login, l_date_added, l_status, l_type, l_locale) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final Logger logger = Logger.getLogger(AuctionProcessor.class);
	
	public boolean createAuctionFromExistingLot(Connection connection, Auction auction, Integer lotId) throws SQLException  {
		try {	
			connection.setAutoCommit(false);
			try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_AUCTION_SQL_STATEMENT)) {
				fillCreateAuctionPreparedStatement(preparedStatement, auction, lotId);
				preparedStatement.executeUpdate();
			}
			try(PreparedStatement preparedStatement = connection.prepareStatement(SET_LOT_STATUS_SQL_STATEMENT)){
				preparedStatement.setString(1, LotStatus.ACTIVE.toString());
				preparedStatement.setInt(2, lotId);
				preparedStatement.executeUpdate();
			}
			connection.commit();
			connection.setAutoCommit(true);
		}  catch (SQLException e){
			logger.error("Error in AuctionProcessor", e);
			connection.rollback();
			throw e;
		}
		return true;
	}
	
	public boolean createAuctionWithLot(Connection connection, Auction auction, Lot lot) throws SQLException {
		try{ 
			connection.setAutoCommit(false);;
			Integer lotId = null;
			try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_LOT_SQL_STATEMENT, PreparedStatement.RETURN_GENERATED_KEYS)) {
				fillCreateLotPreparedStatement(preparedStatement, lot);
				preparedStatement.executeUpdate();
				ResultSet result = preparedStatement.getGeneratedKeys();
				if (result.next()) {
					lotId = result.getInt(1);
				}
			}
			try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_AUCTION_SQL_STATEMENT)) {
				fillCreateAuctionPreparedStatement(preparedStatement, auction, lotId);
				preparedStatement.executeUpdate();
			}
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e){
			logger.error("Error in AuctionProcessor", e);
			connection.rollback();
			throw e;
		}
		return true;
	}
		
	private void fillCreateAuctionPreparedStatement(PreparedStatement preparedStatement, Auction auction, Integer lotId) throws SQLException {
		preparedStatement.setInt(1, lotId);;
		preparedStatement.setTimestamp(2, auction.getStartTime());
		preparedStatement.setTimestamp(3, auction.getEndTime());
		preparedStatement.setDouble(4, auction.getMinBet().getValue());
		preparedStatement.setString(5, auction.getType().toString());
		
	}
	
	private void fillCreateLotPreparedStatement(PreparedStatement preparedStatement, Lot lot) throws SQLException {
		preparedStatement.setString(1, lot.getName());
		preparedStatement.setString(2, lot.getDescription());
		preparedStatement.setInt(3, lot.getQuantity());
		preparedStatement.setString(4, lot.getPicture());
		preparedStatement.setString(5, lot.getOwner());
		preparedStatement.setTimestamp(6, lot.getAdded());
		preparedStatement.setString(7, LotStatus.ACTIVE.toString());
		preparedStatement.setString(8, lot.getType().toString());
		preparedStatement.setString(9, lot.getLocale().toString());
	}
}
