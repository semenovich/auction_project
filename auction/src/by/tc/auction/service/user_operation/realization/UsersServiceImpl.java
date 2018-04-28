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

/**
 *  A class is used to provide methods for operations on users on an application logic level and in a database.
 * @author semenovich
 *
 */
public class UsersServiceImpl implements UsersService {

	private UsersDAO usersDAO;
	private PortionGetter portionGetter = PortionGetter.getInstance();
	
	/**
	 * Default constructor.
	 */
	public UsersServiceImpl() {
		DAOFactory factory = DAOFactory.getInstance();
		usersDAO = factory.getUsersDAO();
	}

	/**
	 * Returns a list of all users portion from a database.
	 * @param page - a page of a users list.
	 * @return A list of all users 10(<= if users in portion are less than 10). Empty list if users don't exist.
	 * @throws ServiceException - if an error occurred during operation in a database.
	 */
	@Override
	public UsersInfo getUsers(int page) throws ServiceException {
		try {
			ArrayList<User> users = usersDAO.getUsers();
			return portionGetter.getUsersPortion(users, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a list of users portion by matching a login of users from a database.
	 * @param searchLine - a search line which will be matched with a user name.
	 * @param page - a page of a users portion list.
	 * @return A list of such users 10(<= if users in portion are less than 10). Empty list if such users don't exist.
	 * @throws ServiceException - if an error occurred during operation in a database.
	 */
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
