package by.tc.auction.dao.auction_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotStatus;

/**
 * A class is used to create and execute a query to a database to process auctions in a database. 
 * @author semenovich
 *
 */
public class AuctionProcessor {
	
	private static final String CREATE_AUCTION_SQL_STATEMENT = "INSERT INTO auction.auctions (l_id, a_start_time, a_end_time, a_minimum_price, auctions_type_at_id) VALUES (?, ?, ?, ?, (SELECT at_id FROM auction.auctions_type WHERE at_type_name=?))";
	private static final String SET_LOT_STATUS_SQL_STATEMENT = "UPDATE auction.lots SET l_status=? WHERE l_id=?";
	private static final String CREATE_LOT_SQL_STATEMENT = "INSERT INTO auction.lots (l_name, l_description, l_quantity, l_picture, su_owner_login, l_date_added, l_status, l_type, l_locale) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final Logger logger = Logger.getLogger(AuctionProcessor.class);
	
	/**
	 * Creates and executes query to a database to create an auction with an existing lot in a database.
	 * @param connection - a connection to a database.
	 * @param auction - an auction which will be created in a database. Only the start time, the minimum bet, the type (and the end time for Online type) fields must be filled in.
	 * @param lotId - an ID of an existing lot.
	 * @return {@code true}.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public boolean createAuctionWithExistingLot(Connection connection, Auction auction, Integer lotId) throws SQLException  {
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
			return true;
		}  catch (SQLException e){
			logger.error("Error in AuctionProcessor", e);
			connection.rollback();
			throw e;
		}
	}
	
	/**
	 * Creates and executes query to a database to create an auction with a lot in a database.
	 * @param connection - a connection to a database.
	 * @param auction - an auction which will be created in a database. Only the start time, the minimum bet, the type (and the end time for Online type) fields must be filled in.
	 * @param lot - a lot which will be created and used in auction in a database. All fields except the status must be filled in.
	 * @return {@code true}.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
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
			return true;
		} catch (SQLException e){
			logger.error("Error in AuctionProcessor", e);
			connection.rollback();
			throw e;
		}
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
