package by.tc.auction.service.authentication.realization.validation;

/**
 * A class is used to validate a user log in.
 * @author semenovich
 *
 */
public class UserLoginValidator {

	/**
	 * Default constructor.
	 */
	private UserLoginValidator() {}
	
	/**
	 * Validates user log in info.
	 * <br> A login must be "A-z, 0-9" and not empty.
	 * <br> A password must be not empty.
	 * @param login - a user login.
	 * @param password - a user password.
	 * @return {@code true} - if user info is correct. {@code false} - if user info is incorrect.
	 */
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
