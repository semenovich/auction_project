package by.tc.auction.controller.command.realization.user_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.tc.auction.controller.command.ServletCommand;
import by.tc.auction.entity.User;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.exception.UserInfoException;
import by.tc.auction.service.user_operation.ProfileService;

/**
 * A class is used to provide the user profile editing method to a controller.
 * @author semenovich
 *
 */
public class EditUserInfo implements ServletCommand{

	private static final Logger logger = Logger.getLogger(EditUserInfo.class);
	
	private static final String USER_LOGIN = "userLogin";
	private static final String USER_PASSWORD = "userPassword";
	private static final String USER_SURNAME = "userSurname";
	private static final String USER_NAME = "userName";
	private static final String USER_EMAIL = "userEmail";
	private static final String USER_PHONE = "userPhone";
	private static final String USER_PASSPORT_ID = "userPassportId";
	private static final String USER_PASSPORT_ISSUED_BY = "userPassportIssuedBy";

    private static final String USER_DATA_INVALID = "isUserDataInvalid";
	
    private static final String SUCCESSFUL_PAGE = "FrontController?command=GET_USER_INFO&userLogin=";
    private static final String NOT_FOUND_PAGE = "404.jsp";
    private static final String INVALID_DATA_PAGE = "FrontController?command=GET_USER_INFO&userLogin=";
    private static final String ACCESS_DENIED_PAGE = "access-denied,jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private ProfileService service;
	
	/**
	 * Default constructor.
	 */
	public EditUserInfo() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getProfileService();
	}

	/**
	 * Edits an application user profile.
	 * <br> The method expects the following parameters with values:
	 * <br> 1. "userLogin" - a user login (doesn't change).
	 * <br> 2. "userPassword" - a user password.
	 * <br> 3. "userSurname" - a user surname.
	 * <br> 4. "userName" - a user name.
	 * <br> 5. "userEmail" - a user email.
	 * <br> 6. "userPhone" - a user phone.
	 * <br> 7. "userPassportId" - a passport ID.
	 * <br> 8. "userPassportIssuedBy" - a passport issued by.
	 * <br> 
	 * <br> In the event of an error, a redirect to the error page occurs.
	 * <br> If a user doesn't have enough privileges, a redirect to the access denied page occurs.
	 * <br> If a user doesn't exist, a redirect to the 404 page occurs.
	 * <br> If lot data is incorrect, the attribute "isUserDataInvalid = true" will be sent back.
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (!request.getParameter(USER_LOGIN).equals((String)session.getAttribute(USER_LOGIN))) {
			response.sendRedirect(ACCESS_DENIED_PAGE);
			return;
		}
		User user = parseUser(request);
		try {
			if (service.editUserInfo(user)) {
				response.sendRedirect(SUCCESSFUL_PAGE + user.getLogin());
			}
			else {
				if (service.getUserInfo(user.getLogin()) == null){
					response.sendRedirect(NOT_FOUND_PAGE);
				}
				else {
					response.sendRedirect(ERROR_PAGE);
				}
			}
		} catch (UserInfoException e) {
			request.setAttribute(USER_DATA_INVALID, true);
			request.getRequestDispatcher(INVALID_DATA_PAGE + user.getLogin()).forward(request, response);
		} catch (ServiceException e1) {
			logger.error("Error in EditUserInfo", e1);
			response.sendRedirect(ERROR_PAGE);
		}
	}
	
	private User parseUser(HttpServletRequest request) {
		User user = new User();
		user.setLogin(request.getParameter(USER_LOGIN));
		user.setPassword(request.getParameter(USER_PASSWORD));
		user.setSurname(request.getParameter(USER_SURNAME));
		user.setName(request.getParameter(USER_NAME));
		user.setEmail(request.getParameter(USER_EMAIL));
		user.setPhone(request.getParameter(USER_PHONE));
		user.setPassportId(request.getParameter(USER_PASSPORT_ID));
		user.setPassportIssuedBy(request.getParameter(USER_PASSPORT_ISSUED_BY));
		return user;
	}
}
