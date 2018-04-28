package by.tc.auction.service.authentication;

import by.tc.auction.entity.User;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.exception.UserInfoException;

/**
 * An interface of authentication operations. An interface which provides methods for working with an authentication in an application logic and in a source.
 * @author semenovich
 *
 */
public interface AuthService {
	
	/**
	 * Creates a user.
	 * In the method the same DAO method should be used.
	 * @param user - a user which will be created.
	 * @return A user if a user has been created. {@code null} if a user hasn't been created.
	 * @throws ServiceException - if an error occurred during operation. 
	 * @throws UserInfoException - if a user has incorrect info.
	 */
	User register(User user) throws ServiceException, UserInfoException;
	
	/**
	 * Returns user info except a password if user login and password are correct.
	 * In the method the same DAO method should be used.
	 * @param login - a user login.
	 * @param password - a user password.
	 * @return User info if user login. {@code null} if user doesn't login.
	 * @throws ServiceException - if an error occurred during operation. 
	 * @throws UserInfoException - if a user has incorrect info.
	 */
	User login(String login, String password) throws ServiceException, UserInfoException;
}
