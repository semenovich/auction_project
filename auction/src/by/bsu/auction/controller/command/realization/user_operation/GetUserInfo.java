package by.bsu.auction.controller.command.realization.user_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.bsu.auction.controller.command.ServletCommand;
import by.tc.auction.entity.User;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.user_operation.ProfileService;

public class GetUserInfo implements ServletCommand {

	private static final Logger logger = Logger.getLogger(GetUserInfo.class);
	
	private static final String USER_LOGIN = "userLogin";
	private static final String PROFILE_OWNER = "owner";
	private static final String USER = "user"; 
	private static final String ERROR_PAGE = "error.jsp";
	private static final String NOT_FOUND_PAGE = "404.jsp";
	private static final String CURRENT_PAGE = "user.jsp";
	
	private ProfileService service;
	
	public GetUserInfo() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getProfileService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				HttpSession session = request.getSession();
				String userLogin = request.getParameter(USER_LOGIN);
				String currentUserLogin = (String) session.getAttribute(USER_LOGIN);
				if (userLogin.isEmpty()) {
					userLogin = currentUserLogin;
				}
				User user = service.getUserInfo(userLogin);
				if (user != null && currentUserLogin.equals(userLogin)) {
					request.setAttribute(PROFILE_OWNER, true);
				}
				if (user == null) {
					response.sendRedirect(NOT_FOUND_PAGE);
				}
				request.setAttribute(USER, user);
				request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
			} catch (ServiceException e) {
				logger.error("Error in GetUserInfo", e);
				response.sendRedirect(ERROR_PAGE);
			}	
	}
}
