package by.tc.auction.dao.user_check_lock_filter;

import by.tc.auction.dao.exception.DAOException;

/**
 * An interface of user lock checking operations. An interface which provides methods for user lock checker.
 * @author semenovich
 *
 */
public interface UserLockCheckerDAO {
	
	/**
	 * Checks if the user is blocked out.
	 * @param login - a login of a user.
	 * @return {@code true} - if a user is blocked out. {@code false} - if a user isn't blocked out.
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean isBlocked(String login) throws DAOException;
}
