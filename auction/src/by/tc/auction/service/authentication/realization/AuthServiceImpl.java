package by.tc.auction.service.authentication.realization;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.authentication.AuthDAO;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.User;
import by.tc.auction.service.authentication.AuthService;
import by.tc.auction.service.authentication.realization.validation.Validator;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.exception.UserInfoException;

public class AuthServiceImpl implements AuthService{

	private static final String ERROR_MESSAGE = "Invalid user info";
	private AuthDAO authDAO;
	
	public AuthServiceImpl() {
		DAOFactory factory = DAOFactory.getInstance();
		authDAO = factory.getAuthDAO();
	}
	
	@Override
	public User register(User user) throws ServiceException, UserInfoException {
		if (!Validator.validateRegistration(user)) {
			throw new UserInfoException(ERROR_MESSAGE);
		}
		try {
			if (authDAO.register(user)) {
				user = authDAO.login(user.getLogin(), user.getPassword());
				
			}
			else {
				user = null;
			}
			return user;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public User login(String login, String password) throws ServiceException, UserInfoException {
		if (!Validator.validateLogin(login, password)) {
			throw new UserInfoException(ERROR_MESSAGE);
		}
		try {
			User user = authDAO.login(login, password);
			return user;
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
}
