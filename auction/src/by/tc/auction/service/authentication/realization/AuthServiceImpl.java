package by.tc.auction.service.authentication.realization;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.authentication.AuthDAO;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.User;
import by.tc.auction.service.authentication.AuthService;
import by.tc.auction.service.authentication.realization.validation.UserLoginValidator;
import by.tc.auction.service.authentication.realization.validation.UserRegistrationValidator;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.exception.UserInfoException;

/**
 * A class is used to provide methods for authentication working with on an application logic level and in a database.
 * @author semenovich
 *
 */
public class AuthServiceImpl implements AuthService{

	private static final String ERROR_MESSAGE = "Invalid user info";
	private AuthDAO authDAO;
	
	/**
	 * Default constructor.
	 */
	public AuthServiceImpl() {
		DAOFactory factory = DAOFactory.getInstance();
		authDAO = factory.getAuthDAO();
	}
	
	/**
	 * Creates a user in a database if user info is valid.
	 * <br> A login must be "A-z, 0-9" and without spaces.
	 * <br> A password must be not empty.
	 * <br> An email must be "example@adress.com".
	 * <br> Phone must be like "1234567890".
	 * <br> All fields must be not empty.
	 * @param user - a user which will be created.
	 * @return A user if a user has been created. {@code null} if a user hasn't been created.
	 * @throws ServiceException - if an error occurred during operation in a database. 
	 * @throws UserInfoException - if a user has incorrect info.
	 */
	@Override
	public User register(User user) throws ServiceException, UserInfoException {
		if (!UserRegistrationValidator.validate(user)) {
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

	/**
	 * Returns user info except a password if user login and password are correct.
	 * <br> A login must be "A-z, 0-9" and not empty.
	 * <br> A password must be not empty.
	 * @param login - a user login.
	 * @param password - a user password.
	 * @return User info if user login. {@code null} if user doesn't login.
	 * @throws ServiceException - if an error occurred during operation. 
	 * @throws UserInfoException - if a user has incorrect info.
	 */
	@Override
	public User login(String login, String password) throws ServiceException, UserInfoException {
		if (!UserLoginValidator.validate(login, password)) {
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
