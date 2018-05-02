package by.tc.auction.controller.command.realization.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.tc.auction.controller.command.ServletCommand;
import by.tc.auction.entity.User;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.authentication.AuthService;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.exception.UserInfoException;

/**
 * A class is used to provide the user registration method to a controller.
 * @author semenovich
 *
 */
public class Register implements ServletCommand{

	private static final Logger logger = Logger.getLogger(Register.class);
	
	private static final String USER_LOGIN = "userLogin";
	private static final String USER_PASSWORD = "userPassword";
	private static final String USER_ROLE = "userRole";
	private static final String USER_SURNAME = "userSurname";
	private static final String USER_NAME = "userName";
	private static final String USER_COUNTRY = "userCountry";
	private static final String USER_EMAIL = "userEmail";
	private static final String USER_PHONE = "userPhone";
	private static final String USER_PASSPORT_ID = "userPassportId";
	private static final String USER_PASSPORT_ISSUED_BY = "userPassportIssuedBy";
	
	private static final String CURRENT_PAGE = "register.jsp";
	private static final String MAIN_PAGE = "index.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private static final String USER_DATA_INVALID = "isUserDataInvalid";
	private static final String USER_EXISTS = "isUserExists";
	
	private AuthService service;
	
	/**
	 * Default constructor.
	 */
	public Register() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		service = serviceFactory.getAuthService(); 
	}
	
	/**
	 * Registers a user in an application.
	 * <br> The method expects the following parameters with values: 
	 * <br> 1. "userLogin".
	 * <br> 2. "userPassword".
	 * <br> 3. "userSurname".
	 * <br> 4. "userName".
	 * <br> 5. "userCountry".
	 * <br> 6. "userEmail".
	 * <br> 7. "userPhone".
	 * <br> 8. "userPassportId".
	 * <br> 9. "userPassportIssuedBy".
	 * <br>
	 * <br> In the event of an error, a redirect to the error page occurs.
	 * <br> If a user with the same login exists, the "isUserExists = true" attribute will be sent.
	 * <br> If a user data is incorrect, the "isUserDataInvalid = true" attribute will be sent.
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = parseUser(request);
			HttpSession session = request.getSession();
			user = service.register(user);
			if (user != null) {
				session.setAttribute(USER_LOGIN, user.getLogin());
			    session.setAttribute(USER_ROLE, user.getRole());
			    response.sendRedirect(MAIN_PAGE);
			}
			else {
				request.setAttribute(USER_EXISTS, true);
				request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
			}
		} catch (UserInfoException e) {
			request.setAttribute(USER_DATA_INVALID, true);
			request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
		} catch (ServiceException e) {
			logger.error("Error in Register", e);
			response.sendRedirect(ERROR_PAGE);
		}
	}
	
	private User parseUser(HttpServletRequest request) {
		User user = new User();
		user.setLogin(request.getParameter(USER_LOGIN));
		user.setPassword(request.getParameter(USER_PASSWORD));
		user.setSurname(request.getParameter(USER_SURNAME));
		user.setName(request.getParameter(USER_NAME));
		user.setCountry(request.getParameter(USER_COUNTRY));
		user.setEmail(request.getParameter(USER_EMAIL));
		user.setPhone(request.getParameter(USER_PHONE));
		user.setPassportId(request.getParameter(USER_PASSPORT_ID));
		user.setPassportIssuedBy(request.getParameter(USER_PASSPORT_ISSUED_BY));
		return user;
	}
}
