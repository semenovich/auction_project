package by.bsu.auction.dao.lot_operation.realization.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotStatus;
import by.tc.auction.entity.LotType;

public class LotParser {

	private static final String LOT_ID = "lotId";
	private static final String LOT_NAME = "lotName";
	private static final String LOT_DESCRIPTION = "lotDescription";
	private static final String LOT_QUANTITY = "lotQuantity";
	private static final String LOT_STATUS = "lotStatus";
	private static final String LOT_TYPE = "lotType";
	private static final String LOT_DATE_ADDED = "lotDateAdded";
	private static final String LOT_OWNER = "lotOwner";
	private static final String LOT_PICTURE = "lotPicture";

	private static LotParser instance = new LotParser();
	
	private LotParser() {}
	
	public static LotParser getInstance() {
		return instance;
	}
	
	public ArrayList<Lot> parseLots(ResultSet result) throws SQLException{
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
	
	public Lot parseLot(ResultSet result) throws SQLException {
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
}
