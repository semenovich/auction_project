package by.bsu.auction.controller.command.realization.user_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsu.auction.controller.command.ServletCommand;
import by.bsu.auction.entity.UsersInfo;
import by.bsu.auction.service.ServiceFactory;
import by.bsu.auction.service.exception.ServiceException;
import by.bsu.auction.service.user_operation.UsersService;

public class GetUsersList implements ServletCommand {

	private static final Logger logger = Logger.getLogger(GetUsersList.class);
	
	private static final String CHOOSEN_USERS_PAGE_NUMBER = "usersPageNumber";
	private static final String USERS_INFO = "usersInfo";
	private static final String ERROR_PAGE = "error.jsp";
	private static final String CURRENT_PAGE = "user-list.jsp";
	
	UsersService service;
	
	public GetUsersList() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getUsersService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int page = Integer.valueOf((String)request.getParameter(CHOOSEN_USERS_PAGE_NUMBER));
			UsersInfo usersInfo = service.getUsers(page);
			request.setAttribute(USERS_INFO, usersInfo);
			request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
		} catch (ServiceException e) {
			logger.error("Error in GetUserList", e);
			response.sendRedirect(ERROR_PAGE);
		}	
	}

}
