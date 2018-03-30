package by.bsu.auction.dao.user_operation.realization;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.bsu.auction.dao.connection_pool.ConnectionPool;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.exception.DBConnectionException;
import by.bsu.auction.dao.user_operation.UsersDAO;
import by.bsu.auction.dao.user_operation.realization.util.UserInfoGetter;
import by.tc.auction.entity.User;

public class UsersDAOImpl implements UsersDAO {

	private UserInfoGetter userInfoGetter = new UserInfoGetter();
	private static final Logger logger = Logger.getLogger(UsersDAOImpl.class);

	public UsersDAOImpl() {}

	@Override
	public ArrayList<User> getUsers() throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return userInfoGetter.getUsers(connection);
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in UsersDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ArrayList<User> getUsersByNameSearching(String searchLine) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return userInfoGetter.getUsersBySearching(connection, searchLine);
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in UsersDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

}
