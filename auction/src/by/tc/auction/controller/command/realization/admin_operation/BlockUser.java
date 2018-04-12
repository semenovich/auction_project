package by.tc.auction.controller.command.realization.admin_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.tc.auction.controller.command.ServletCommand;
import by.tc.auction.entity.UserRole;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.admin_operation.AdminOperationService;
import by.tc.auction.service.exception.ServiceException;

public class BlockUser implements ServletCommand {
	
	private static final Logger logger = Logger.getLogger(BlockUser.class);
	
	private static final String USER_LOGIN = "userLogin";
	
	private static final String USER_ROLE = "userRole";
	
	private static final String SUCCESSFUL_PAGE = "FrontController?command=GET_USER_INFO&userLogin=";
	private static final String NOT_FOUND_PAGE = "404.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	private static final String ACCESS_DENIED_PAGE = "access-denied.jsp";

	private AdminOperationService service;
	
	public BlockUser() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getAdminOperationService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getSession().getAttribute(USER_ROLE) != UserRole.ADMIN) {
				response.sendRedirect(ACCESS_DENIED_PAGE);
				return;
			}
			String userLogin = request.getParameter(USER_LOGIN);
			if (service.blockUser(userLogin)) {
				response.sendRedirect(SUCCESSFUL_PAGE + userLogin);	
			}
			else {
				response.sendRedirect(NOT_FOUND_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("Error in BlockUser", e);
			response.sendRedirect(ERROR_PAGE);
		}
	}
}