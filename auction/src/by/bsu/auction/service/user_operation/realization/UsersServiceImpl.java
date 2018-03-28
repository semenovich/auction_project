package by.bsu.auction.service.user_operation.realization;

import java.util.ArrayList;

import by.bsu.auction.dao.DAOFactory;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.user_operation.UsersDAO;
import by.bsu.auction.entity.User;
import by.bsu.auction.entity.UsersInfo;
import by.bsu.auction.service.exception.ServiceException;
import by.bsu.auction.service.user_operation.UsersService;

public class UsersServiceImpl implements UsersService {

	private UsersDAO usersDAO;
	
	private static final int USERS_PORTION_QUANTITY = 10;

	
	public UsersServiceImpl() {
		DAOFactory factory = DAOFactory.getInstance();
		usersDAO = factory.getUsersDAO();
	}

	@Override
	public UsersInfo getUsers(int page) throws ServiceException {
		try {
			ArrayList<User> users = usersDAO.getUsers();
			return getUsersPortion(users, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public UsersInfo getUsersByNameSearch(String searchLine, int page) throws ServiceException {
		try {
			ArrayList<User> users = usersDAO.getUsersByNameSearching(searchLine);
			return getUsersPortion(users, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
	
	private UsersInfo getUsersPortion(ArrayList<User> users, int page){
		if (users == null) {
			return null;
		}
		ArrayList<User> returnUsers = new ArrayList<>();
		UsersInfo usersInfo = new UsersInfo();
		for (int i = (page - 1) * USERS_PORTION_QUANTITY; i < page * USERS_PORTION_QUANTITY && i < users.size() ; i++) {
			returnUsers.add(users.get(i));
		}
		usersInfo.setUsers(returnUsers);
		usersInfo.setCurrentPage(page);
		usersInfo.setPages((int) Math.ceil(((double) users.size()) / USERS_PORTION_QUANTITY));
		return usersInfo;
	}

}
