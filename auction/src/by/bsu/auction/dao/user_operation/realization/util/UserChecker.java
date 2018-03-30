package by.bsu.auction.dao.user_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserChecker {

	private static final String CHECK_USER_LOGIN_AND_PASSWORD_SQL_STATEMENT = "SELECT u.su_login AS userLogin, u.su_surname AS userSurname, u.su_Name AS userName, u.su_password AS userPassword, u.su_email AS userEmail, u.su_phone AS userPhone, u.su_passport_id AS userPassportId, u.su_passport_issued_by AS userPassportIssuedBy, c.uc_name AS userCountry, u.su_blocked AS isBlocked, r.sur_role_name AS userRole, u.su_picture AS userPicture FROM auction.site_users u INNER JOIN auction.site_users_role r ON u.sur_id = r.sur_id INNER JOIN auction.users_countries c ON u.uc_id = c.uc_id WHERE u.su_login=? AND u.su_password=MD5(?)";
	private static final String CHECK_USER_LOGIN_SQL_STATEMENT = "SELECT su_login AS userLogin FROM auction.site_users WHERE su_login=?";
	
	public boolean isUserExist(Connection connection, String login) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_LOGIN_SQL_STATEMENT)){
			preparedStatement.setString(1, login);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		}
	}
	
	public boolean isUserExist(Connection connection, String login, String password) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_LOGIN_AND_PASSWORD_SQL_STATEMENT)){
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			ResultSet result = preparedStatement.executeQuery();
			return result.next();
		}
	}
}
