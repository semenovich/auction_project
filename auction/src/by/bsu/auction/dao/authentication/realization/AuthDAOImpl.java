package by.bsu.auction.dao.authentication.realization;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.bsu.auction.dao.authentication.AuthDAO;
import by.bsu.auction.dao.authentication.realization.util.UserChecker;
import by.bsu.auction.dao.authentication.realization.util.UserInfoGetter;
import by.bsu.auction.dao.authentication.realization.util.UserProcessor;
import by.bsu.auction.dao.connection_pool.ConnectionPool;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.exception.DBConnectionException;
import by.tc.auction.entity.User;

public class AuthDAOImpl implements AuthDAO {
	
	private UserProcessor userProcessor = new UserProcessor();
	private UserInfoGetter userInfoGetter = new UserInfoGetter();
	private UserChecker userChecker = new UserChecker();
	private static final Logger logger = Logger.getLogger(AuthDAOImpl.class);
	
	@Override
	public boolean register(User user) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (!userChecker.isUserExist(connection, user.getLogin())) {
				userProcessor.registerUser(connection, user);
				return true;
			}
			return false;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in AuthDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public User login(String login, String password) throws DAOException {
		User user = null;
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (userChecker.isUserExist(connection, login, password)) {
				user = userInfoGetter.getPersonalUserInfo(connection, login);
			}
			return user;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in AuthDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
}
