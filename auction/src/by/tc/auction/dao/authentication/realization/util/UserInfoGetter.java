package by.tc.auction.dao.authentication.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.tc.auction.dao.util.EntityCreator;
import by.tc.auction.entity.User;

/**
 * A class is used to create and execute a query to a database to get info about a user in a database.
 * @author semenovich
 *
 */
public class UserInfoGetter {

	private static final String GET_USER_SQL_STATEMENT = "SELECT u.su_login AS userLogin, u.su_surname AS userSurname, u.su_Name AS userName, u.su_password AS userPassword, u.su_email AS userEmail, u.su_phone AS userPhone, u.su_passport_id AS userPassportId, u.su_passport_issued_by AS userPassportIssuedBy, c.uc_name AS userCountry, u.su_blocked AS isBlocked, r.sur_role_name AS userRole, u.su_picture AS userPicture   FROM auction.site_users u  INNER JOIN auction.site_users_role r ON u.sur_id = r.sur_id  INNER JOIN auction.users_countries c ON u.uc_id = c.uc_id  WHERE u.su_login=?";
	
	private static final Logger logger = Logger.getLogger(UserInfoGetter.class);
	
	private final EntityCreator creator = EntityCreator.getInstance();
	
	/**
	 * Creates and executes query to a database to get info except a password about a user.
	 * @param connection - a connection to a database.
	 * @param login - a user login.
	 * @return User if user exists in a database. {@code null} if user doesn't exist
	 * @throws SQLException - if a database access error or other errors occurred.
	 */
	public User getPersonalUserInfo(Connection connection, String login) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_SQL_STATEMENT)){
			preparedStatement.setString(1, login);
			ResultSet result = preparedStatement.executeQuery();
			return creator.createUser(result);
		} catch (SQLException e){
			logger.error("Error in UserInfoGetter", e);
			throw e;
		}
	}
}
