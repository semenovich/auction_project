package by.bsu.auction.dao.lot_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotStatus;
import by.tc.auction.entity.LotType;

public class LotProcessor {

	private static final String CREATE_LOT_SQL_STATEMENT = "INSERT INTO auction.lots (l_name, l_description, l_quantity, l_picture, su_owner_login, l_date_added, l_status, l_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String CHECK_LOT_EXISTENCE_SQL_STATEMENT = "SELECT * FROM auction.lots WHERE l_id=?";
	private static final String GET_LOT_INFO_SQL_STATEMENT = "SELECT l_id AS lotId, l_name AS lotName, l_description AS lotDescription, l_quantity AS lotQuantity, l_picture AS lotPicture, l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, su_owner_login AS lotOwner FROM auction.lots WHERE l_id=?";
	private static final String DELETE_LOT_SQL_STATEMENT = "DELETE FROM auction.lots WHERE l_id=?";
	private static final String CHECK_IS_LOT_CONFIRMING_SQL_STATEMENT = "SELECT l_id AS lotId FROM auction.lots WHERE l_id=? AND l_status='CONFIRMING'";
	private static final String EDIT_LOT_INFO_SQL_STATEMENT = "UPDATE auction.lots SET l_name=?, l_description=?, l_quantity=?, l_picture=? WHERE l_id=?"; 
	private static final String GET_LOTS_LIST_SQL_STATEMENT = "SELECT l_id AS lotId, l_name AS lotName, l_description AS lotDescription, l_quantity AS lotQuantity, l_picture AS lotPicture, l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, su_owner_login AS lotOwner FROM auction.lots ORDER BY l_date_added DESC";
	private static final String GET_LOTS_BY_SEARCHING_SQL_STATEMENT = "SELECT l_id AS lotId, l_name AS lotName, l_description AS lotDescription, l_quantity AS lotQuantity, l_picture AS lotPicture, l_date_added AS lotDateAdded, l_type AS lotType, l_status AS lotStatus, su_owner_login AS lotOwner FROM auction.lots WHERE UPPER(l_name) LIKE UPPER(?) ORDER BY l_date_added DESC";
	
	private static final String LOT_ID = "lotId";
	private static final String LOT_NAME = "lotName";
	private static final String LOT_DESCRIPTION = "lotDescription";
	private static final String LOT_QUANTITY = "lotQuantity";
	private static final String LOT_STATUS = "lotStatus";
	private static final String LOT_TYPE = "lotType";
	private static final String LOT_DATE_ADDED = "lotDateAdded";
	private static final String LOT_OWNER = "lotOwner";
	private static final String LOT_PICTURE = "lotPicture";
	
	public boolean isLotExist(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOT_EXISTENCE_SQL_STATEMENT)) {
			preparedStatement.setInt(1, lotId);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		}
	}
	
	public boolean createLot(Connection connection, Lot lot) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_LOT_SQL_STATEMENT)) {
			fillCreateLotPreparedStatement(preparedStatement, lot);
			preparedStatement.executeUpdate();
			return true;
		}
	}
	
	public Lot getLotInfo(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_LOT_INFO_SQL_STATEMENT)) {
			preparedStatement.setInt(1, lotId);
			ResultSet result = preparedStatement.executeQuery();
			return parseLot(result);
		}
	}
	
	public boolean deleteLot(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LOT_SQL_STATEMENT)) {
			preparedStatement.setInt(1, lotId);
			preparedStatement.executeUpdate();
			return true;
		}
	}
	
	public boolean editLot(Connection connection, Lot lot) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(EDIT_LOT_INFO_SQL_STATEMENT)) {
			preparedStatement.setString(1, lot.getName());
			preparedStatement.setString(2, lot.getDescription());
			preparedStatement.setInt(3, lot.getQuantity());
			preparedStatement.setString(4, lot.getPicture());
			preparedStatement.setInt(5, lot.getId());
			preparedStatement.executeUpdate();
			return true;
		}
	}
	
	public boolean checkIsLotConfirming(Connection connection, Integer lotId) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IS_LOT_CONFIRMING_SQL_STATEMENT)) {
			preparedStatement.setInt(1, lotId);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		}
	}
	
	public ArrayList<Lot> getLotsList(Connection connection) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_LOTS_LIST_SQL_STATEMENT)) {
			ResultSet result = preparedStatement.executeQuery();
			return parseLots(result);
		}
	}
	
	public ArrayList<Lot> getLotsBySearching(Connection connection, String searchLine) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_LOTS_BY_SEARCHING_SQL_STATEMENT)) {
			preparedStatement.setString(1, "%" + searchLine + "%");
			ResultSet result = preparedStatement.executeQuery();
			return parseLots(result);
		}
	}
	
	private ArrayList<Lot> parseLots(ResultSet result) throws SQLException{
		ArrayList<Lot> lots = new ArrayList<>();
		Lot lot = null;
		while(result.next()) {
			lot = new Lot();
			lot.setId(result.getInt(LOT_ID));
			lot.setName(result.getString(LOT_NAME));
			lot.setDescription(result.getString(LOT_DESCRIPTION));
			lot.setQuantity(result.getInt(LOT_QUANTITY));
			lot.setOwner(result.getString(LOT_OWNER));
			lot.setStatus(LotStatus.valueOf(result.getString(LOT_STATUS)));
			lot.setType(LotType.valueOf(result.getString(LOT_TYPE)));
		    lot.setAdded(result.getDate(LOT_DATE_ADDED));
		    lot.setPicture(result.getString(LOT_PICTURE));
		    lots.add(lot);
		}
		return lots;
	}
	
	private Lot parseLot(ResultSet result) throws SQLException {
		Lot lot = null;
		if (result.next()) {
			lot = new Lot();
			lot.setId(result.getInt(LOT_ID));
			lot.setName(result.getString(LOT_NAME));
			lot.setDescription(result.getString(LOT_DESCRIPTION));
			lot.setQuantity(result.getInt(LOT_QUANTITY));
			lot.setOwner(result.getString(LOT_OWNER));
			lot.setStatus(LotStatus.valueOf(result.getString(LOT_STATUS)));
			lot.setType(LotType.valueOf(result.getString(LOT_TYPE)));
		    lot.setAdded(result.getDate(LOT_DATE_ADDED));
		    lot.setPicture(result.getString(LOT_PICTURE));
		}
		return lot;
	}
	
	
	private void fillCreateLotPreparedStatement(PreparedStatement preparedStatement, Lot lot) throws SQLException {
		preparedStatement.setString(1, lot.getName());
		preparedStatement.setString(2, lot.getDescription());
		preparedStatement.setInt(3, lot.getQuantity());
		preparedStatement.setString(4, lot.getPicture());
		preparedStatement.setString(5, lot.getOwner());
		preparedStatement.setDate(6, lot.getAdded());
		preparedStatement.setString(7, lot.getStatus().toString());
		preparedStatement.setString(8, lot.getType().toString());
	}
}
