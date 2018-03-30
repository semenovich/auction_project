package by.bsu.auction.dao.lot_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.tc.auction.entity.Lot;

public class LotProcessor {

	private static final String CREATE_LOT_SQL_STATEMENT = "INSERT INTO auction.lots (l_name, l_description, l_quantity, l_picture, su_owner_login, l_date_added, l_status, l_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_LOT_SQL_STATEMENT = "DELETE FROM auction.lots WHERE l_id=?";
	private static final String EDIT_LOT_INFO_SQL_STATEMENT = "UPDATE auction.lots SET l_name=?, l_description=?, l_quantity=?, l_picture=? WHERE l_id=?"; 
		
	public boolean createLot(Connection connection, Lot lot) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CREATE_LOT_SQL_STATEMENT)) {
			fillCreateLotPreparedStatement(preparedStatement, lot);
			preparedStatement.executeUpdate();
			return true;
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
