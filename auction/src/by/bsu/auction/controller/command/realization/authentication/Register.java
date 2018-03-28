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
	private static final String USER_PICTURE = "userPicture";
	
	private static final String CURRENT_PAGE = "register.jsp";
	private static final String MAIN_PAGE = "index.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private static final String USER_DATA_INVALID = "isUserDataInvalid";
	private static final String USER_EXISTS = "isUserExists";
	
	private AuthService service;
	
	public Register() {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		service = serviceFactory.getAuthService(); 
	}
	
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
		user.setPicture(request.getParameter(USER_PICTURE));
		return user;
	}
}
