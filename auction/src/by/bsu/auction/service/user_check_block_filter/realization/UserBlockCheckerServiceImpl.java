package by.bsu.auction.service.user_check_block_filter.realization;

import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.user_check_block_filter.UserBlockCheckerDAO;
import by.bsu.auction.dao.user_check_block_filter.realization.UserBlockCheckerDAOImpl;
import by.bsu.auction.service.exception.ServiceException;
import by.bsu.auction.service.user_check_block_filter.UserBlockCheckerService;

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
