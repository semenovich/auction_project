package by.tc.auction.service.user_check_lock_filter.realization;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.user_check_lock_filter.UserLockCheckerDAO;
import by.tc.auction.dao.user_check_lock_filter.realization.UserLockCheckerDAOImpl;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.user_check_lock_filter.UserLockCheckerService;

public class UserLockCheckerServiceImpl implements UserLockCheckerService{

	UserLockCheckerDAO userLockCheckerDAO = new UserLockCheckerDAOImpl();

	@Override
	public boolean isBlocked(String login) throws ServiceException {
		try {
			return userLockCheckerDAO.isBlocked(login);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

}
