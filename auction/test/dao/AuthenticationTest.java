package dao;

import org.junit.Assert;
import org.junit.Test;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.authentication.AuthDAO;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.user_operation.ProfileDAO;
import by.tc.auction.entity.User;

public class AuthenticationTest {

	@Test
	public void testRegister() {
		AuthDAO authDAO = DAOFactory.getInstance().getAuthDAO();
		ProfileDAO profileDAO = DAOFactory.getInstance().getProfileDAO();
		
		User registerUser = new User();
		
		registerUser.setLogin("RegisterUser");
		registerUser.setPassword("pass");
		registerUser.setSurname("user");
		registerUser.setName("user");
		registerUser.setCountry("Belarus");
		registerUser.setPassportId("user");
		registerUser.setPassportIssuedBy("user");
		registerUser.setPhone("user");
		registerUser.setEmail("user");
		
		try {
			
			authDAO.register(registerUser);
			User registeredUser = profileDAO.getUserInfo(registerUser.getLogin());
			
			Assert.assertEquals(true, registerUser.getLogin().equals(registeredUser.getLogin()) &&
					registerUser.getSurname().equals(registeredUser.getSurname()) &&
					registerUser.getName().equals(registeredUser.getName()) &&
					registerUser.getCountry().equals(registeredUser.getCountry()) &&
					registerUser.getEmail().equals(registeredUser.getEmail()) &&
					registerUser.getPhone().equals(registeredUser.getPhone()) &&
					registerUser.getPassportId().equals(registeredUser.getPassportId()) &&
					registerUser.getPassportIssuedBy().equals(registeredUser.getPassportIssuedBy()));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLogin() {
		AuthDAO authDAO = DAOFactory.getInstance().getAuthDAO();
		ProfileDAO profileDAO = DAOFactory.getInstance().getProfileDAO();
		
		User loginUser = new User();
		
		loginUser.setLogin("LoginUser");
		loginUser.setPassword("Admin");
		
		try {
			User loginedUser = authDAO.login(loginUser.getLogin(), loginUser.getPassword());
			Assert.assertEquals(profileDAO.getUserInfo(loginUser.getLogin()), loginedUser);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
