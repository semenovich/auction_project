package dao;

import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.lot_operation.LotOperationDAO;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotStatus;
import by.tc.auction.entity.LotType;

public class LotOperationTest {

	@Test
	public void testCreateLot() {
		LotOperationDAO lotOperationDAO = DAOFactory.getInstance().getLotOperationDAO();
		
		Lot lot = new Lot();
		
		lot.setName("CreatedLot");
		lot.setDescription("description");
		lot.setQuantity(1);
		lot.setLocale(Locale.en);
		lot.setOwner("Admin");
		lot.setStatus(LotStatus.CONFIRMING);
		lot.setType(LotType.CAR);
		lot.setAdded(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		try {
			Assert.assertEquals(true, lotOperationDAO.createLot(lot));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testGetLotInfo() {
		LotOperationDAO lotOperationDAO = DAOFactory.getInstance().getLotOperationDAO();
		
		Integer lotId = 8;
		
		try {
			Lot lot = lotOperationDAO.getLotInfo(lotId);
			Assert.assertEquals(true, lot.getName().equals("InfoLot") &&
					lot.getDescription().equals("InfoLot") && 
					lot.getOwner().equals("Admin") && 
					lot.getQuantity().equals(1));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteWaitingLot() {
		LotOperationDAO lotOperationDAO = DAOFactory.getInstance().getLotOperationDAO();
		
		Integer lotId = 9;
		
		try {
			lotOperationDAO.deleteWaitingLot(lotId);
			Assert.assertEquals(null, lotOperationDAO.getLotInfo(lotId));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEditWaitingLot() {
		LotOperationDAO lotOperationDAO = DAOFactory.getInstance().getLotOperationDAO();
		
		Integer lotId = 10;
		
		Lot editLot = new Lot();
		
		editLot.setId(lotId);
		editLot.setName("EditedLot");
		editLot.setDescription("EditedLot");
		editLot.setQuantity(2);
		
		try {
			lotOperationDAO.editWaitingLot(editLot);
			Lot editedLot = lotOperationDAO.getLotInfo(editLot.getId());
			Assert.assertEquals(true, editLot.getDescription().equals(editedLot.getDescription()) &&
					editLot.getName().equals(editedLot.getName()) &&
					editLot.getQuantity().equals(editedLot.getQuantity()));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLotsList() {
		LotOperationDAO lotOperationDAO = DAOFactory.getInstance().getLotOperationDAO();
		
		try {
			Assert.assertEquals(false, lotOperationDAO.getLotsList(Locale.en).isEmpty());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLotsBySearching() {
		LotOperationDAO lotOperationDAO = DAOFactory.getInstance().getLotOperationDAO();
		
		try {
			Assert.assertEquals(false, lotOperationDAO.getLotsBySearching("Search", Locale.en).isEmpty());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLotsByType() {
		LotOperationDAO lotOperationDAO = DAOFactory.getInstance().getLotOperationDAO();
		
		try {
			Assert.assertEquals(false, lotOperationDAO.getLotsByType(LotType.CAR, Locale.en).isEmpty());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetWaitingLots() {
		LotOperationDAO lotOperationDAO = DAOFactory.getInstance().getLotOperationDAO();
		
		try {
			Assert.assertEquals(false, lotOperationDAO.getWaitingLots(Locale.en).isEmpty());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
