package by.tc.auction.controller.command.realization.user_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.tc.auction.controller.command.ServletCommand;
import by.tc.auction.entity.User;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.user_operation.ProfileService;

/**
 * A class is used to provide the user info getting method to a controller.
 * @author semenovich
 *
 */
public class GetUserInfo implements ServletCommand {

	private static final Logger logger = Logger.getLogger(GetUserInfo.class);
	
	private static final String USER_LOGIN = "userLogin";
	private static final String USER = "user"; 
	
	private static final String ERROR_PAGE = "error.jsp";
	private static final String NOT_FOUND_PAGE = "404.jsp";
	private static final String USER_PAGE = "user.jsp";
	
	private ProfileService service;
	
	/**
	 * Default constructor.
	 */
	public GetUserInfo() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getProfileService();
	}

	/**
	 * Gets user info in a response.
	 * <br> The method expects "userLogin" parameter with a value.
	 * <br> The method puts a user object to "user" attribute.
	 * <br>
	 * <br> In the event of an error, a redirect to the error page occurs.
	 * <br> If a lot hasn't been found, a redirect to the 404 page occurs.
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				String userLogin = request.getParameter(USER_LOGIN);
				User user = service.getUserInfo(userLogin);
				if (user == null) {
					response.sendRedirect(NOT_FOUND_PAGE);
				}
				else {
					request.setAttribute(USER_LOGIN, user.getLogin());
					request.setAttribute(USER, user);
					request.getRequestDispatcher(USER_PAGE).forward(request, response);
				}
			} catch (ServiceException e) {
				logger.error("Error in GetUserInfo", e);
				response.sendRedirect(ERROR_PAGE);
			}	
	}
}
