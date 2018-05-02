package by.tc.auction.controller.command.realization.user_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.tc.auction.controller.command.ServletCommand;
import by.tc.auction.entity.UserRole;
import by.tc.auction.entity.UsersInfo;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.user_operation.UsersService;

/**
 * A class is used to provide the getting users list by searching method to a controller.
 * @author semenovich
 *
 */
public class GetUsersBySearching implements ServletCommand {

	private static final Logger logger = Logger.getLogger(GetUsersBySearching.class);
	
	private static final String USER_ROLE = "userRole";
	
	private static final String SEARCH_LINE = "searchLine";
	private static final String CHOOSEN_USERS_PAGE_NUMBER = "usersPageNumber";
	private static final String LIST_TYPE = "listType";
	private static final String SEARCHING_BY_SEARCH_LINE = "searchingBySearchLine";
	private static final String USERS_INFO = "usersInfo";
	
	private static final String ERROR_PAGE = "error.jsp";
	private static final String CURRENT_PAGE = "user-list.jsp";
	private static final String ACCESS_DENIED_PAGE = "access-denied.jsp";
	
	private UsersService service;
	
	/**
	 * Default constructor.
	 */
	public GetUsersBySearching() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getUsersService();
	}

	/**
	 * Gets a users list by searching.
	 * <br> The method expects the following parameters:
	 * <br> 1. "searchLine" - a search line.
	 * <br> 2. "usersPageNumber" - a chosen page.
	 * <br> 3. "userRole" - a user role.
	 * <br>
	 * <br> The method puts the following attributes:
	 * <br> 1. "usersInfo" (a users list).
	 * <br> 2. "listType" ("searchingBySearchLine").
	 * <br> 3. "searchLine" (with the chosen value).
	 * <br>
	 * <br> In the event of an error, a redirect to the error page occurs.
	 * <br> If a user doesn't have enough privileges, a redirect to the access denied page occurs.
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getSession().getAttribute(USER_ROLE) != UserRole.ADMIN) {
				response.sendRedirect(ACCESS_DENIED_PAGE);
				return;
			}
			int page = Integer.valueOf((String)request.getParameter(CHOOSEN_USERS_PAGE_NUMBER));
			UsersInfo usersInfo = service.getUsersBySearching(request.getParameter(SEARCH_LINE), page);
			request.setAttribute(USERS_INFO, usersInfo);
			request.setAttribute(SEARCH_LINE, request.getParameter(SEARCH_LINE));
			request.setAttribute(LIST_TYPE, SEARCHING_BY_SEARCH_LINE);
			request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
		} catch (ServiceException e) {
			logger.error("Error in GetUsersBySearching", e);
			response.sendRedirect(ERROR_PAGE);
		}	
	}

}
