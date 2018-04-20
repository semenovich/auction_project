package dao;

import org.junit.Assert;
import org.junit.Test;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.admin_operation.AdminOperationDAO;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.lot_operation.LotOperationDAO;
import by.tc.auction.dao.user_operation.ProfileDAO;
import by.tc.auction.entity.LotStatus;

public class AdminOperationTest {
	
	@Test
	public void testBlockUser() {
		AdminOperationDAO adminOperationDAO = DAOFactory.getInstance().getAdminOperationDAO();
		ProfileDAO profileDAO = DAOFactory.getInstance().getProfileDAO();
		
		String userLogin = "BlockUser";
		try {
			adminOperationDAO.blockUser(userLogin);
			Assert.assertEquals(true, profileDAO.getUserInfo(userLogin).isBlocked());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUnblockUser() {
		AdminOperationDAO adminOperationDAO = DAOFactory.getInstance().getAdminOperationDAO();
		ProfileDAO profileDAO = DAOFactory.getInstance().getProfileDAO();
		
		String userLogin = "UnblockUser";
		try {
			adminOperationDAO.blockUser(userLogin);
			Assert.assertEquals(false, profileDAO.getUserInfo(userLogin).isBlocked());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBlockLot() {
		AdminOperationDAO adminOperationDAO = DAOFactory.getInstance().getAdminOperationDAO();
		LotOperationDAO lotOperationDAO = DAOFactory.getInstance().getLotOperationDAO();
		
		Integer lotId = 1;
		try {
			adminOperationDAO.blockLot(lotId);
			Assert.assertEquals(LotStatus.BLOCKED, lotOperationDAO.getLotInfo(lotId).getStatus());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUnblockLot() {
		AdminOperationDAO adminOperationDAO = DAOFactory.getInstance().getAdminOperationDAO();
		LotOperationDAO lotOperationDAO = DAOFactory.getInstance().getLotOperationDAO();
		
		Integer lotId = 2;
		try {
			adminOperationDAO.unblockLot(lotId);
			Assert.assertEquals(LotStatus.READY, lotOperationDAO.getLotInfo(lotId).getStatus());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}
