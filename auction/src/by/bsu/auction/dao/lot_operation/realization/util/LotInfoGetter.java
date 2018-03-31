package by.bsu.auction.dao.lot_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.tc.auction.entity.Lot;
public class LotInfoGetter {

	private static final String GET_LOT_INFO_SQL_STATEMENT = "SELECT l_id AS lotId, l_name AS lotName, l_description AS lotDescription, l_quantity AS lotQuantity, l_picture AS lotPicture, l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, su_owner_login AS lotOwner FROM auction.lots WHERE l_id=?";
	private static final String GET_LOTS_LIST_SQL_STATEMENT = "SELECT l_id AS lotId, l_name AS lotName, l_description AS lotDescription, l_quantity AS lotQuantity, l_picture AS lotPicture, l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, su_owner_login AS lotOwner FROM auction.lots ORDER BY l_date_added DESC";
	
	LotParser lotParser = LotParser.getInstance();
	
	public Lot getLotInfo(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_LOT_INFO_SQL_STATEMENT)) {
			preparedStatement.setInt(1, lotId);
			ResultSet result = preparedStatement.executeQuery();
			return lotParser.parseLot(result);
		}
	}
	
	public ArrayList<Lot> getLotsList(Connection connection) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_LOTS_LIST_SQL_STATEMENT)) {
			ResultSet result = preparedStatement.executeQuery();
			return lotParser.parseLots(result);
		}
	}
}
