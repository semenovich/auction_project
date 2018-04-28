package by.tc.auction.service.user_check_lock_filter.realization;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.user_check_lock_filter.UserLockCheckerDAO;
import by.tc.auction.dao.user_check_lock_filter.realization.UserLockCheckerDAOImpl;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.user_check_lock_filter.UserLockCheckerService;

/**
 * A class is used to provide methods for working with a user lock checking on an application logic level and in a database.
 * @author semenovich
 *
 */
public class UserLockCheckerServiceImpl implements UserLockCheckerService{

	private UserLockCheckerDAO userLockCheckerDAO = new UserLockCheckerDAOImpl();

	/**
	 * Default constructor.
	 */
	public UserLockCheckerServiceImpl() {}
	
	/**
	 * Checks if the user is blocked out in a database.
	 * @param login - a login of a user.
	 * @return {@code true} - if a user is blocked out. {@code false} - if a user isn't blocked out.
	 * @throws ServiceException - if an error occurred during operation in a database. 
	 */
	@Override
	public boolean isBlocked(String login) throws ServiceException {
		try {
			return userLockCheckerDAO.isBlocked(login);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
}
