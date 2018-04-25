package by.tc.auction.dao.lot_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.tc.auction.entity.Lot;

/**
 * A class is used to create and execute a query to a database to process lots in a database. 
 * @author semenovich
 *
 */
public class LotProcessor {

	private static final String CREATE_LOT_SQL_STATEMENT = "INSERT INTO auction.lots (l_name, l_description, l_quantity, su_owner_login, l_date_added, l_status, l_type, l_locale) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_LOT_SQL_STATEMENT = "DELETE FROM auction.lots WHERE l_id=?";
	private static final String EDIT_LOT_INFO_SQL_STATEMENT = "UPDATE auction.lots SET l_name=?, l_description=?, l_quantity=? WHERE l_id=?"; 
	private static final String UPLOAD_LOT_IMAGE_SQL_STATEMENT = "UPDATE auction.lots SET l_picture=? WHERE l_id=?";	
	
	private static final Logger logger = Logger.getLogger(LotProcessor.class);

	/**
	 * Creates and executes query to a database to create a lot.
	 * @param connection - a connection to a database.
	 * @param lot - a lot which will be created in a database. All fields must be filled in.
	 * @return {@code true}.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public boolean createLot(Connection connection, Lot lot) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_LOT_SQL_STATEMENT)) {
			fillCreateLotPreparedStatement(preparedStatement, lot);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e){
			logger.error("Error in LotProcessor", e);
			throw e;
		}
	}
	
	/**
	 * Creates and executes query to a database to remove a lot.
	 * @param connection - a connection to a database.
	 * @param lotId - a lot ID.
	 * @return {@code true}.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public boolean deleteLot(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LOT_SQL_STATEMENT)) {
			preparedStatement.setInt(1, lotId);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e){
			logger.error("Error in LotProcessor", e);
			throw e;
		}
	}
	
	/**
	 * Creates and executes query to a database to update a lot info.
	 * @param connection - a connection to a database.
	 * @param lot - an update lot info. Only ID, name, description, quantity fields must be filled in.
	 * @return {@code true}.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public boolean editLot(Connection connection, Lot lot) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(EDIT_LOT_INFO_SQL_STATEMENT)) {
			preparedStatement.setString(1, lot.getName());
			preparedStatement.setString(2, lot.getDescription());
			preparedStatement.setInt(3, lot.getQuantity());
			preparedStatement.setInt(4, lot.getId());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e){
			logger.error("Error in LotProcessor", e);
			throw e;
		}
	}
	
	/**
	 * Creates and executes query to a database to upload a lot image.
	 * @param connection - a connection to a database.
	 * @param lotId - a lot ID.
	 * @param imagePath - an image path.
	 * @return {@code true}.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public boolean uploadLotImage(Connection connection, Integer lotId, String imagePath) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(UPLOAD_LOT_IMAGE_SQL_STATEMENT)) {
			preparedStatement.setString(1, imagePath);
			preparedStatement.setInt(2, lotId);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e){
			logger.error("Error in LotProcessor", e);
			throw e;
		}
	}
	
	private void fillCreateLotPreparedStatement(PreparedStatement preparedStatement, Lot lot) throws SQLException {
		preparedStatement.setString(1, lot.getName());
		preparedStatement.setString(2, lot.getDescription());
		preparedStatement.setInt(3, lot.getQuantity());
		preparedStatement.setString(4, lot.getOwner());
		preparedStatement.setTimestamp(5, lot.getAdded());
		preparedStatement.setString(6, lot.getStatus().toString());
		preparedStatement.setString(7, lot.getType().toString());
		preparedStatement.setString(8, lot.getLocale().toString());
	}
}
