package test.dao;

import org.junit.Assert;
import org.junit.Test;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.user_operation.ProfileDAO;
import by.tc.auction.entity.User;

public class ProfileTest {

	@Test
	public void testGetUserInfo() {
		ProfileDAO profileDAO = DAOFactory.getInstance().getProfileDAO();
		
		try {
			User user = profileDAO.getUserInfo("InfoUser");
			Assert.assertEquals(true, user.getLogin().equals("InfoUser") &&
					user.getSurname().equals("InfoUser") && 
					user.getName().equals("InfoUser") &&
					user.getEmail().equals("InfoUser@InfoUser.InfoUser") &&
					user.getCountry().equals("Belarus") &&
					user.getPassportId().equals("InfoUser") &&
					user.getPassportIssuedBy().equals("InfoUser") &&
					user.getPhone().equals("InfoUser"));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetUserLots() {
		ProfileDAO profileDAO = DAOFactory.getInstance().getProfileDAO();
		
		try {
			Assert.assertEquals(false, profileDAO.getUserLots("Admin").isEmpty());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetUserWinLots() {
		ProfileDAO profileDAO = DAOFactory.getInstance().getProfileDAO();
		
		try {
			Assert.assertEquals(false, profileDAO.getUserWinLots("Admin").isEmpty());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetUserAuctionParticipation() {
		ProfileDAO profileDAO = DAOFactory.getInstance().getProfileDAO();
		
		try {
			Assert.assertEquals(false, profileDAO.getUserAuctionParticipation("Admin").isEmpty());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEditUserInfo() {
		ProfileDAO profileDAO = DAOFactory.getInstance().getProfileDAO();
		
		User editUser = new User();
		editUser.setLogin("EditUser");
		editUser.setSurname("EditedUser");
		editUser.setName("EditedUser");
		editUser.setEmail("EditedUser@EditedUser.EditedUser");
		editUser.setPhone("EditedUser");
		editUser.setPassportId("EditedUser");
		editUser.setPassportIssuedBy("EditedUser");
		editUser.setPassword("");
		
		try {
			profileDAO.editUserInfo(editUser);
			User editedUser = profileDAO.getUserInfo(editUser.getLogin());
			
			Assert.assertEquals(true, editUser.getSurname().equals(editedUser.getSurname()) &&
					editUser.getName().equals(editedUser.getSurname()) &&
					editUser.getEmail().equals(editedUser.getEmail()) &&
					editUser.getPhone().equals(editedUser.getPhone()) &&
					editUser.getPassportId().equals(editedUser.getPassportId()) &&
					editUser.getPassportIssuedBy().equals(editedUser.getPassportIssuedBy()));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
