package by.tc.auction.controller.command.realization.user_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.tc.auction.controller.command.ServletCommand;
import by.tc.auction.entity.LotsInfo;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.user_operation.ProfileService;

public class GetUserWinLots implements ServletCommand {

	private static final Logger logger = Logger.getLogger(GetUserWinLots.class);
	
	private static final String USER_LOGIN = "userLogin";
	
	private static final String CHOOSEN_LOTS_PAGE_NUMBER = "choosenLotsPageNumber";
	private static final String USER_WIN_LOTS_INFO = "userWinLotsInfo";
	
	private static final String ERROR_PAGE = "error.jsp";
	private static final String NOT_FOUND_PAGE = "404.jsp";
	private static final String CURRENT_PAGE = "user.jsp";
	private static final String ACCESS_DENIED_PAGE = "access-denied.jsp";
	
	private ProfileService service;
	
	public GetUserWinLots() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getProfileService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String userLogin = request.getParameter(USER_LOGIN);
			if (!userLogin.equals((String)request.getSession().getAttribute(USER_LOGIN))) {
				response.sendRedirect(ACCESS_DENIED_PAGE);
				return;
			}
			int page = Integer.valueOf((String)request.getParameter(CHOOSEN_LOTS_PAGE_NUMBER));
			LotsInfo lotsInfo = service.getUserWinLots(userLogin, page);
			if (lotsInfo != null) {
				request.setAttribute(USER_LOGIN, userLogin);
				request.setAttribute(USER_WIN_LOTS_INFO, lotsInfo);
				request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
			}
			else {
				response.sendRedirect(NOT_FOUND_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("Error in GetUserWinLots", e);
			response.sendRedirect(ERROR_PAGE);
		}
	}

}
