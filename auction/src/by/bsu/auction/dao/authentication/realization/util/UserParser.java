package by.bsu.auction.dao.authentication.realization.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.tc.auction.entity.User;
import by.tc.auction.entity.UserRole;

public class UserParser {

	private static final String USER_LOGIN = "userLogin";
	private static final String USER_PASSWORD = "userPassword";
	private static final String USER_ROLE = "userRole";
	private static final String USER_SURNAME = "userSurname";
	private static final String USER_NAME = "userName";
	private static final String USER_COUNTRY = "userCountry";
	private static final String USER_EMAIL = "userEmail";
	private static final String USER_PHONE = "userPhone";
	private static final String USER_PASSPORT_ID = "userPassportId";
	private static final String USER_PASSPORT_ISSUED_BY = "userPassportIssuedBy";	
	private static final String USER_IS_BLOCKED = "isBlocked";
	private static final String USER_PICTURE = "userPicture";
	
	private static final UserParser instance = new UserParser();
	
	private UserParser() {}
	
	public static UserParser getInstance() {
		return instance;
	}
	
	public User parseUser(ResultSet result) throws SQLException {
		User user = null;
		if (result.next()) {
			user = new User();
			user.setLogin(result.getString(USER_LOGIN));
			user.setSurname(result.getString(USER_SURNAME));
			user.setName(result.getString(USER_NAME));
			user.setPassword(result.getString(USER_PASSWORD));
			user.setPassportId(result.getString(USER_PASSPORT_ID));
			user.setPassportIssuedBy(result.getString(USER_PASSPORT_ISSUED_BY));
			user.setPhone(result.getString(USER_PHONE));
			user.setEmail(result.getString(USER_EMAIL));
			user.setCountry(result.getString(USER_COUNTRY));
			user.setBlocked(result.getBoolean(USER_IS_BLOCKED));
			user.setRole(UserRole.valueOf(result.getString(USER_ROLE)));
			user.setPicture(result.getString(USER_PICTURE));
		}
		return user;
	}
}
