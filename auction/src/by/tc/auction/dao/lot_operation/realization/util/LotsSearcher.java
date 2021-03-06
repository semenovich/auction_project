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
import by.tc.auction.entity.LotType;

/**
 * A class is used to create and execute a query to a database to search lots in a database.
 * @author semenovich
 *
 */
public class LotsSearcher {

	private static final String GET_LOTS_BY_SEARCHING_SQL_STATEMENT = "SELECT l_id AS lotId, l_locale AS lotLocale, l_name AS lotName, l_description AS lotDescription, l_quantity AS lotQuantity, l_picture AS lotPicture, l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, su_owner_login AS lotOwner FROM auction.lots WHERE UPPER(l_name) LIKE UPPER(?) AND l_locale=? ORDER BY l_date_added DESC";
	private static final String GET_LOTS_BY_TYPE_SQL_STATEMENT = "SELECT l_id AS lotId, l_locale AS lotLocale, l_name AS lotName, l_description AS lotDescription, l_quantity AS lotQuantity, l_picture AS lotPicture, l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, su_owner_login AS lotOwner FROM auction.lots WHERE l_type=? AND l_locale=? ORDER BY l_date_added DESC";
	private static final String GET_WAITING_LOTS_SQL_STATEMENT = "SELECT l_id AS lotId, l_locale AS lotLocale, l_name AS lotName, l_description AS lotDescription, l_quantity AS lotQuantity, l_picture AS lotPicture, l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, su_owner_login AS lotOwner FROM auction.lots WHERE (l_status='CONFIRMING' OR l_status='READY') AND l_locale=? ORDER BY l_date_added DESC";
	
	private static final Logger logger = Logger.getLogger(LotsSearcher.class);

	private final EntityCreator creator = EntityCreator.getInstance();
	
	/**
	 * Creates and executes query to a database to get a list of lots by matching a name of lots. 
	 * @param connection - a connection to a database.
	 * @param searchLine - a search line which will be matched with lots name.
	 * @param locale - a locale of lots.
	 * @return A lot list of lots if such lots exist in a database. Empty list if such lots don't exist in a database.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public ArrayList<Lot> getLotsBySearching(Connection connection, String searchLine, Locale locale) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_LOTS_BY_SEARCHING_SQL_STATEMENT)) {
			preparedStatement.setString(1, "%" + searchLine + "%");
			preparedStatement.setString(2, locale.toString());
			ResultSet result = preparedStatement.executeQuery();
			return creator.createLots(result);
		} catch (SQLException e){
			logger.error("Error in LotsSearcher", e);
			throw e;
		}
	}

	/**
	 * Creates and executes query to a database to get a list of lots by searching by the type of lots.
	 * @param connection - a connection to a database.
	 * @param lotType - a lots type.
	 * @param locale - a locale of lots.
	 * @return A lot list of lots if such lots exist in a database. Empty list if such lots don't exist in a database.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public ArrayList<Lot> getLotsByType(Connection connection, LotType lotType, Locale locale) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_LOTS_BY_TYPE_SQL_STATEMENT)) {
			preparedStatement.setString(1, lotType.toString());
			preparedStatement.setString(2, locale.toString());
			ResultSet result = preparedStatement.executeQuery();
			return creator.createLots(result);
		} catch (SQLException e){
			logger.error("Error in LotsSearcher", e);
			throw e;
		}
	}
	
	/**
	 * Creates and executes query to a database to get a list of waiting lots.
	 * <br> A waiting lot - is which has status 'CONFRMING' or 'READY' in a database.
	 * @param connection - a connection to a database.
	 * @param locale - a locale of lots.
	 * @return A lot list of lots if such lots exist in a database. Empty list if such lots don't exist in a database.
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public ArrayList<Lot> getWaitingLots(Connection connection, Locale locale) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_WAITING_LOTS_SQL_STATEMENT)) {
			preparedStatement.setString(1, locale.toString());
			ResultSet result = preparedStatement.executeQuery();
			return creator.createLots(result);
		} catch (SQLException e){
			logger.error("Error in LotsSearcher", e);
			throw e;
		}
	}
}
