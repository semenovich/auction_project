package by.tc.auction.dao.user_operation.realization;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.tc.auction.dao.connection_pool.ConnectionPool;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.exception.ConnectionPoolException;
import by.tc.auction.dao.user_operation.UsersDAO;
import by.tc.auction.dao.user_operation.realization.util.UserInfoGetter;
import by.tc.auction.entity.User;

public class UsersDAOImpl implements UsersDAO {

	private final UserInfoGetter userInfoGetter = new UserInfoGetter();
	
	private static final Logger logger = Logger.getLogger(UsersDAOImpl.class);

	public UsersDAOImpl() {}

	@Override
	public ArrayList<User> getUsers() throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return userInfoGetter.getUsers(connection);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in UsersDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ArrayList<User> getUsersBySearching(String searchLine) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return userInfoGetter.getUsersBySearching(connection, searchLine);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in UsersDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

}
