package by.tc.auction.service.authentication.realization.validation;

public class UserLoginValidator {

	private UserLoginValidator() {}
	
	public static boolean validate(String login, String password) {
		if (login == null || password == null) {
			return false;
		}
		if (login.isEmpty() || !login.matches("^\\w+$")) {
			return false;
		}
		if (password.isEmpty()) {
			return false;
		}
		return true;
	}
}
