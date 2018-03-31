package by.bsu.auction.dao.lot_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;

public class LotsSearcher {

	private static final String GET_LOTS_BY_SEARCHING_SQL_STATEMENT = "SELECT l_id AS lotId, l_name AS lotName, l_description AS lotDescription, l_quantity AS lotQuantity, l_picture AS lotPicture, l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, su_owner_login AS lotOwner FROM auction.lots WHERE UPPER(l_name) LIKE UPPER(?) ORDER BY l_date_added DESC";
	private static final String GET_LOTS_BY_TYPE_SQL_STATEMENT = "SELECT l_id AS lotId, l_name AS lotName, l_description AS lotDescription, l_quantity AS lotQuantity, l_picture AS lotPicture, l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, su_owner_login AS lotOwner FROM auction.lots WHERE l.l_type=? ORDER BY l_date_added DESC";
	
	private LotParser lotParser = LotParser.getInstance();
	
	public ArrayList<Lot> getLotsBySearching(Connection connection, String searchLine) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_LOTS_BY_SEARCHING_SQL_STATEMENT)) {
			preparedStatement.setString(1, "%" + searchLine + "%");
			ResultSet result = preparedStatement.executeQuery();
			return lotParser.parseLots(result);
		}
	}

	public ArrayList<Lot> getLotsByType(Connection connection, LotType lotType) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_LOTS_BY_TYPE_SQL_STATEMENT)) {
			preparedStatement.setString(1, lotType.toString());
			ResultSet result = preparedStatement.executeQuery();
			return lotParser.parseLots(result);
		}
	}
}
