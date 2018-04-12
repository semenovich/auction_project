package by.tc.auction.dao.user_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.tc.auction.entity.User;

public final class UserProcessor {

	private static final String EDIT_USER_INFO_SQL_STATEMENT = "UPDATE auction.site_users SET su_surname=?, su_name=?, su_password=MD5(?), su_email=?, su_phone=?, su_passport_id=?, su_passport_issued_by=? WHERE su_login=?";
	private static final String EDIT_USER_INFO_WIHOUT_PASSWORD_SQL_STATEMENT = "UPDATE auction.site_users SET su_surname=?, su_name=?, su_email=?, su_phone=?, su_passport_id=?, su_passport_issued_by=? WHERE su_login=?";
	private static final String UPLOAD_USER_IMAGE_SQL_STATEMENT = "UPDATE auction.site_users SET su_picture=? WHERE su_login=?"; 
	
	private static final Logger logger = Logger.getLogger(UserProcessor.class);

	public boolean edit(Connection connection, User user) throws SQLException {
		try {
			if (user.getPassword().isEmpty()) {
				try (PreparedStatement preparedStatement = connection.prepareStatement(EDIT_USER_INFO_WIHOUT_PASSWORD_SQL_STATEMENT)) {
					fillEditWithoutPasswordPreparedStatement(preparedStatement, user);
					preparedStatement.executeUpdate();
					return true;
				}
			} else {
				try (PreparedStatement preparedStatement = connection.prepareStatement(EDIT_USER_INFO_SQL_STATEMENT)) {
					fillEditPreparedStatement(preparedStatement, user);
					preparedStatement.executeUpdate();
					return true;
				}
			}
		} catch (SQLException e) {
			logger.error("Error in UserProcessor", e);
			throw e;
		}
	}
	
	public boolean uploadUserImage(Connection connection, String userLogin, String imagePath) throws SQLException {
		try (PreparedStatement preparedStatement = connection.prepareStatement(UPLOAD_USER_IMAGE_SQL_STATEMENT)) {
			preparedStatement.setString(1, imagePath);
			preparedStatement.setString(2, userLogin);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Error in UserProcessor", e);
			throw e;
		}
	}	

	private void fillEditPreparedStatement(PreparedStatement preparedStatement, User user) throws SQLException {
		preparedStatement.setString(1, user.getSurname());
		preparedStatement.setString(2, user.getName());
		preparedStatement.setString(3, user.getPassword());
		preparedStatement.setString(4, user.getEmail());
		preparedStatement.setString(5, user.getPhone());
		preparedStatement.setString(6, user.getPassportId());
		preparedStatement.setString(7, user.getPassportIssuedBy());
		preparedStatement.setString(8, user.getLogin());
	}

	private void fillEditWithoutPasswordPreparedStatement(PreparedStatement preparedStatement, User user) throws SQLException {
		preparedStatement.setString(1, user.getSurname());
		preparedStatement.setString(2, user.getName());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.setString(4, user.getPhone());
		preparedStatement.setString(5, user.getPassportId());
		preparedStatement.setString(6, user.getPassportIssuedBy());
		preparedStatement.setString(7, user.getLogin());
	}
}
