package by.bsu.auction.dao.authentication.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.tc.auction.entity.User;
import by.tc.auction.entity.UserRole;

public final class UserProcessor {
	
	private static final String GET_USER_SQL_STATEMENT = "SELECT u.su_login AS userLogin, u.su_surname AS userSurname, u.su_Name AS userName, u.su_password AS userPassword, u.su_email AS userEmail, u.su_phone AS userPhone, u.su_passport_id AS userPassportId, u.su_passport_issued_by AS userPassportIssuedBy, c.uc_name AS userCountry, u.su_blocked AS isBlocked, r.sur_role_name AS userRole, u.su_picture AS userPicture   FROM auction.site_users u  INNER JOIN auction.site_users_role r ON u.sur_id = r.sur_id  INNER JOIN auction.users_countries c ON u.uc_id = c.uc_id  WHERE u.su_login=?";
	private static final String REGISTER_USER_SQL_STATEMENT = "INSERT INTO auction.site_users (su_login, su_surname, su_name, su_password, su_email, su_phone, su_passport_id, su_passport_issued_by, uc_id, su_picture) VALUES (?, ?, ?, MD5(?), ?, ?, ?, ?, (SELECT uc_id FROM auction.users_countries WHERE uc_name=?), ?)";
	
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
	
	public boolean registerUser(Connection connection, User user) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_USER_SQL_STATEMENT)){
			fillRegisterPreparedStatement(preparedStatement, user);
			preparedStatement.executeUpdate();
			return true;
		}
	}
	
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

	private void fillRegisterPreparedStatement(PreparedStatement preparedStatement, User user) throws SQLException {
		preparedStatement.setString(1, user.getLogin());
		preparedStatement.setString(2, user.getSurname());
		preparedStatement.setString(3, user.getName());
		preparedStatement.setString(4, user.getPassword());
		preparedStatement.setString(5, user.getEmail());
		preparedStatement.setString(6, user.getPhone());
		preparedStatement.setString(7, user.getPassportId());
		preparedStatement.setString(8, user.getPassportIssuedBy());
		preparedStatement.setString(9, user.getCountry());
		preparedStatement.setString(10, user.getPicture());
	}	
}
