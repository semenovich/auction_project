package test;

import org.junit.Assert;
import org.junit.Test;

import by.tc.auction.entity.User;
import by.tc.auction.service.authentication.realization.validation.Validator;

public class AuthenticationValidatorTest {

	@Test
	public void testValidateLogin() {
		String login = "";
		String password = "";
		
		Assert.assertEquals(false, Validator.validateLogin(login, password));
		
		login = "login";
		password = "password";
		
		Assert.assertEquals(true, Validator.validateLogin(login, password));
	}

	@Test
	public void testValidateRegistration() {
		User user = new User();		
		user.setLogin("Login");
		user.setPassword("Password");
		user.setPhone("1234567899");
		user.setEmail("q@q.q");
		user.setCountry("Belarus");
		user.setName("Name");
		user.setSurname("Surname");
		user.setPassportId("passportID");
		user.setPassportIssuedBy("issuedBy");
		
		User user2 = new User();
		user2.setLogin("Login");
		user2.setPhone("1234567899");
		user2.setEmail("q@q.q");
		user2.setCountry("Belarus");
		user2.setName("Name");
		user2.setSurname("Surname");
		user2.setPassportId("passportID");
		user2.setPassportIssuedBy("issuedBy");
		user2.setPassword("");
		
		Assert.assertEquals(true, Validator.validateRegistration(user));
		Assert.assertEquals(false, Validator.validateRegistration(user2));
	}

}
