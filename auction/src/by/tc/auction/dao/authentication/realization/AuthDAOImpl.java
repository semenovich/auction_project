package by.tc.auction.dao.authentication.realization;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.tc.auction.dao.authentication.AuthDAO;
import by.tc.auction.dao.authentication.realization.util.UserChecker;
import by.tc.auction.dao.authentication.realization.util.UserInfoGetter;
import by.tc.auction.dao.authentication.realization.util.UserProcessor;
import by.tc.auction.dao.connection_pool.ConnectionPool;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.exception.ConnectionPoolException;
import by.tc.auction.entity.User;

public class AuthDAOImpl implements AuthDAO {
	
	private final UserProcessor userProcessor = new UserProcessor();
	private final UserInfoGetter userInfoGetter = new UserInfoGetter();
	private final UserChecker userChecker = new UserChecker();
	private static final Logger logger = Logger.getLogger(AuthDAOImpl.class);
	
	@Override
	public boolean register(User user) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (!userChecker.isUserExist(connection, user.getLogin())) {
				userProcessor.registerUser(connection, user);
				return true;
			}
			return false;
		} catch (SQLException | ConnectionPoolException e) {
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
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in AuthDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
}
