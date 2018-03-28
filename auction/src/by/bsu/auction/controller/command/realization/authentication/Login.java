package by.bsu.auction.controller.command.realization.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.bsu.auction.controller.command.ServletCommand;
import by.bsu.auction.entity.User;
import by.bsu.auction.service.ServiceFactory;
import by.bsu.auction.service.authentication.AuthService;
import by.bsu.auction.service.exception.ServiceException;
import by.bsu.auction.service.exception.UserInfoException;

public class Login implements ServletCommand {
	
	private static final Logger logger = Logger.getLogger(Login.class);
	
    private static final String USER_LOGIN = "userLogin";
    private static final String USER_PASSWORD = "userPassword";
    private static final String USER_ROLE = "userRole";
    private static final String MAIN_PAGE = "index.jsp";
    private static final String CURRENT_PAGE = "login.jsp";
    private static final String USER_DATA_INVALID = "isUserDataInvalid";
    private static final String USER_NOT_EXIST = "isUserNotExist";
	private static final String ERROR_PAGE = "error.jsp";
	
    private AuthService service;
    
    public Login() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		service = serviceFactory.getAuthService(); 
    }
    
    @Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String userLogin = request.getParameter(USER_LOGIN);
			String userPassword = request.getParameter(USER_PASSWORD);
			User user = service.login(userLogin, userPassword);
			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute(USER_LOGIN, user.getLogin());
		        session.setAttribute(USER_ROLE, user.getRole());
		        response.sendRedirect(MAIN_PAGE);
			}
			else {
				request.setAttribute(USER_NOT_EXIST, true);
				request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
			}
		} catch (ServiceException e) {
			logger.error("Error in Login", e);
			response.sendRedirect(ERROR_PAGE);
		} catch (UserInfoException e) {
			request.setAttribute(USER_DATA_INVALID, true);
			request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
		}
	}
}
