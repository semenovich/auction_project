package by.tc.auction.dao.authentication.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.tc.auction.entity.User;

public final class UserProcessor {
	
	private static final String REGISTER_USER_SQL_STATEMENT = "INSERT INTO auction.site_users (su_login, su_surname, su_name, su_password, su_email, su_phone, su_passport_id, su_passport_issued_by, uc_id, su_picture) VALUES (?, ?, ?, MD5(?), ?, ?, ?, ?, (SELECT uc_id FROM auction.users_countries WHERE uc_name=?), ?)";
	
	private static final Logger logger = Logger.getLogger(UserProcessor.class);
	
	public boolean registerUser(Connection connection, User user) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_USER_SQL_STATEMENT)){
			fillRegisterPreparedStatement(preparedStatement, user);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e){
			logger.error("Error in UserProcessor", e);
			throw e;
		}
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
