package by.tc.auction.dao.lot_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.tc.auction.dao.util.EntityCreator;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;

/**
 * A class is used to create and execute a query to a database to get info about lots in a database.
 * @author semenovich
 *
 */
public class LotInfoGetter {

	private static final String GET_LOT_INFO_SQL_STATEMENT = "SELECT l_id AS lotId, l_locale AS lotLocale, l_name AS lotName, l_description AS lotDescription, l_quantity AS lotQuantity, l_picture AS lotPicture, l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, su_owner_login AS lotOwner FROM auction.lots WHERE l_id=?";
	private static final String GET_LOTS_LIST_SQL_STATEMENT = "SELECT l_id AS lotId, l_locale AS lotLocale, l_name AS lotName, l_description AS lotDescription, l_quantity AS lotQuantity, l_picture AS lotPicture, l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, su_owner_login AS lotOwner FROM auction.lots WHERE l_locale=? ORDER BY l_date_added DESC";
	
	private static final Logger logger = Logger.getLogger(LotInfoGetter.class);

	private final EntityCreator creator = EntityCreator.getInstance();
	
	/**
	 * Creates and executes query to a database to get info about a lot.
	 * @param connection - a connection to a database.
	 * @param lotId - a lot ID.
	 * @return A lot if the lot exists. {@code null} if a lot doesn't exist.
	 * @throws SQLException
	 */
	public Lot getLotInfo(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_LOT_INFO_SQL_STATEMENT)) {
			preparedStatement.setInt(1, lotId);
			ResultSet result = preparedStatement.executeQuery();
			return creator.createLot(result);
		} catch (SQLException e){
			logger.error("Error in LotInfoGetter", e);
			throw e;
		}
	}
	
	/**
	 * Creates and executes query to a database to get a list of lots.
	 * @param connection - a connection to a database.
	 * @param locale - a locale of lots.
	 * @return Auction list of lots if lots exist in a database. Empty list if lots doesn't exist in a database.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public ArrayList<Lot> getLotsList(Connection connection, Locale locale) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_LOTS_LIST_SQL_STATEMENT)) {
			preparedStatement.setString(1, locale.toString());
			ResultSet result = preparedStatement.executeQuery();
			return creator.createLots(result);
		} catch (SQLException e){
			logger.error("Error in LotInfoGetter", e);
			throw e;
		}
	}
}
