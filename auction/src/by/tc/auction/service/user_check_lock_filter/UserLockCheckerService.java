package by.tc.auction.service.user_check_lock_filter;

import by.tc.auction.service.exception.ServiceException;

/**
 * An interface of user lock checking operations. An interface which provides methods for user lock checker in an application logic and in a source.
 * @author semenovich
 *
 */
public interface UserLockCheckerService {
	
	/**
	 * Checks if the user is blocked out.
	 * In the method the same DAO method should be used.
	 * @param login - a login of a user.
	 * @return {@code true} - if a user is blocked out. {@code false} - if a user isn't blocked out.
	 * @throws ServiceException - if an error occurred during operation. 
	 */
	boolean isBlocked(String login) throws ServiceException;
}
