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
	
	public GetUsersBySearching() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getUsersService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getSession().getAttribute(USER_ROLE) != UserRole.ADMIN) {
				response.sendRedirect(ACCESS_DENIED_PAGE);
				return;
			}
			int page = Integer.valueOf((String)request.getParameter(CHOOSEN_USERS_PAGE_NUMBER));
			UsersInfo usersInfo = service.getUsersByNameSearch(request.getParameter(SEARCH_LINE), page);
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
