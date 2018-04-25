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

/**
 * A class is used to provide methods for a user authentication in a database.
 * @author semenovich
 *
 */
public class AuthDAOImpl implements AuthDAO {
	
	private final UserProcessor userProcessor = new UserProcessor();
	private final UserInfoGetter userInfoGetter = new UserInfoGetter();
	private final UserChecker userChecker = new UserChecker();
	private static final Logger logger = Logger.getLogger(AuthDAOImpl.class);

	/**
	 * Default constructor.
	 */
	public AuthDAOImpl() {}
	
	/**
	 * Creates a user in a database.
	 * @param user - a user which will be created in a database. All fields must be filled in.
	 * @return {@code true} - if a user has been created. {@code false} - if a user exists and hasn't been created.
	 * @throws DAOException - if an error occurred during operation with (in) a database or not all fields are filled in.
	 */
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

	/**
	 * Returns user info if user login and password are correct in a database.
	 * @param login - a user login.
	 * @param password - a user password.
	 * @return User info except a password if user login. {@code null} if user doesn't login.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
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
