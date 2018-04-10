package by.tc.auction.service.user_check_block_filter.realization;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.user_check_block_filter.UserBlockCheckerDAO;
import by.tc.auction.dao.user_check_block_filter.realization.UserBlockCheckerDAOImpl;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.user_check_block_filter.UserBlockCheckerService;

public class UserBlockCheckerServiceImpl implements UserBlockCheckerService{

	UserBlockCheckerDAO userBlockCheckerDAO = new UserBlockCheckerDAOImpl();

	@Override
	public boolean isBlocked(String login) throws ServiceException {
		try {
			return userBlockCheckerDAO.isBlocked(login);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

}
