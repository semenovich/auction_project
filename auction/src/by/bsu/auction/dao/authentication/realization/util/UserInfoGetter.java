package by.bsu.auction.dao.authentication.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.tc.auction.entity.User;
import by.tc.auction.entity.UserRole;

public class UserInfoGetter {

	private static final String GET_USER_SQL_STATEMENT = "SELECT u.su_login AS userLogin, u.su_surname AS userSurname, u.su_Name AS userName, u.su_password AS userPassword, u.su_email AS userEmail, u.su_phone AS userPhone, u.su_passport_id AS userPassportId, u.su_passport_issued_by AS userPassportIssuedBy, c.uc_name AS userCountry, u.su_blocked AS isBlocked, r.sur_role_name AS userRole, u.su_picture AS userPicture   FROM auction.site_users u  INNER JOIN auction.site_users_role r ON u.sur_id = r.sur_id  INNER JOIN auction.users_countries c ON u.uc_id = c.uc_id  WHERE u.su_login=?";
	
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
	
	public User getPersonalUserInfo(Connection connection, String login) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_SQL_STATEMENT)){
			preparedStatement.setString(1, login);
			ResultSet result = preparedStatement.executeQuery();
			return parseUser(result);
		}
	}
	
	private User parseUser(ResultSet result) throws SQLException {
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
