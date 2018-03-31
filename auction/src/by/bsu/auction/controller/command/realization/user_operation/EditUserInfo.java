package by.bsu.auction.controller.command.realization.user_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.bsu.auction.controller.command.ServletCommand;
import by.bsu.auction.service.ServiceFactory;
import by.bsu.auction.service.exception.ServiceException;
import by.bsu.auction.service.exception.UserInfoException;
import by.bsu.auction.service.user_operation.ProfileService;
import by.tc.auction.entity.User;

public class EditUserInfo implements ServletCommand{

	private static final Logger logger = Logger.getLogger(EditUserInfo.class);
	
	private static final String USER = "user"; 
	private static final String PROFILE_OWNER = "owner";
	private static final String USER_LOGIN = "userLogin";
	private static final String USER_PASSWORD = "userPassword";
	private static final String USER_SURNAME = "userSurname";
	private static final String USER_NAME = "userName";
	private static final String USER_COUNTRY = "userCountry";
	private static final String USER_EMAIL = "userEmail";
	private static final String USER_PHONE = "userPhone";
	private static final String USER_PASSPORT_ID = "userPassportId";
	private static final String USER_PASSPORT_ISSUED_BY = "userPassportIssuedBy";
	private static final String USER_PICTURE = "userPicture";

    private static final String USER_DATA_INVALID = "isUserDataInvalid";
	
    private static final String ACCESS_DENIED_PAGE = "access_denied,jsp";
	private static final String NOT_FOUND_PAGE = "404.jsp";
	private static final String CURRENT_PAGE = "user.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private ProfileService service;
	
	public EditUserInfo() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getProfileService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (!request.getParameter(USER_LOGIN).equals((String)session.getAttribute(USER_LOGIN))) {
			response.sendRedirect(ACCESS_DENIED_PAGE);
		}
		User user = parseUser(request);
		try {
			if (service.editUserInfo(user)) {
				user = service.getUserInfo(user.getLogin());
				request.setAttribute(PROFILE_OWNER, true);
				request.setAttribute(USER, user);
				request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
			}
			else {
				response.sendRedirect(NOT_FOUND_PAGE);
			}
		} catch (UserInfoException e) {
			try {
				request.setAttribute(USER, service.getUserInfo(user.getLogin()));
				request.setAttribute(PROFILE_OWNER, true);
				request.setAttribute(USER_DATA_INVALID, true);
				request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
			} catch (ServiceException e1) {
				logger.error("Error in EditUserInfo", e1);
				response.sendRedirect(ERROR_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("Error in EditUserInfo", e);
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
