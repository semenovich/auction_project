package by.tc.auction.dao.authentication;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.User;

/**
 * An interface of authentication operations. An interface provides methods for an authentication.
 * @author semenovich
 *
 */
public interface AuthDAO {
	
	/**
	 * Creates a user.
	 * @param user - a user which will be created.
	 * @return {@code true} - if a user has been created. {@code false} - if a user hasn't been created.
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean register(User user) throws DAOException;
	
	/**
	 * Returns user info except a password if user login and password are correct.
	 * @param login - a user login.
	 * @param password - a user password.
	 * @return User info if user login. {@code null} if user doesn't login.
	 * @throws DAOException - if an error occurred during operation.
	 */
	User login(String login, String password) throws DAOException;
}
