package by.bsu.auction.dao.user_operation.realization.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import by.tc.auction.entity.User;

public final class UserProcessor {
	
	private static final String EDIT_USER_INFO_SQL_STATEMENT = "UPDATE auction.site_users SET su_surname=?, su_name=?, su_password=?, su_email=?, su_phone=?, su_passport_id=?, su_passport_issued_by=?, su_picture=? WHERE su_login=?";
	
	public boolean edit(Connection connection, User user) throws SQLException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(EDIT_USER_INFO_SQL_STATEMENT)) {
			fillEditPreparedStatement(preparedStatement, user);
			preparedStatement.executeUpdate();
			return true;
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
		preparedStatement.setString(8, user.getPicture());
		preparedStatement.setString(9, user.getLogin());
	}
}
