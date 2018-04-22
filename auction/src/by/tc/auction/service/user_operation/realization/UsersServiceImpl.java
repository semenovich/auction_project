package by.tc.auction.service.user_operation.realization;

import java.util.ArrayList;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.user_operation.UsersDAO;
import by.tc.auction.entity.User;
import by.tc.auction.entity.UsersInfo;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.user_operation.UsersService;
import by.tc.auction.service.user_operation.realization.util.PortionGetter;

public class UsersServiceImpl implements UsersService {

	private UsersDAO usersDAO;
	private PortionGetter portionGetter = PortionGetter.getInstance();
	
	public UsersServiceImpl() {
		DAOFactory factory = DAOFactory.getInstance();
		usersDAO = factory.getUsersDAO();
	}

	@Override
	public UsersInfo getUsers(int page) throws ServiceException {
		try {
			ArrayList<User> users = usersDAO.getUsers();
			return portionGetter.getUsersPortion(users, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public UsersInfo getUsersBySearching(String searchLine, int page) throws ServiceException {
		try {
			ArrayList<User> users = usersDAO.getUsersBySearching(searchLine);
			return portionGetter.getUsersPortion(users, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
}
