package validation;

import org.junit.Assert;
import org.junit.Test;

import by.tc.auction.entity.User;
import by.tc.auction.service.authentication.realization.validation.UserLoginValidator;
import by.tc.auction.service.authentication.realization.validation.UserRegistrationValidator;

public class AuthenticationValidatorTest {

	@Test
	public void testValidateLogin() {
		String login = "";
		String password = "";
		
		Assert.assertEquals(false, UserLoginValidator.validate(login, password));
		
		login = "login";
		password = "password";
		
		Assert.assertEquals(true, UserLoginValidator.validate(login, password));
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
		
		Assert.assertEquals(true, UserRegistrationValidator.validate(user));
		Assert.assertEquals(false, UserRegistrationValidator.validate(user2));
	}

}
