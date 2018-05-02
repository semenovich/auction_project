package by.tc.auction.controller.command.realization.user_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.tc.auction.controller.command.ServletCommand;
import by.tc.auction.entity.AuctionsInfo;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.user_operation.ProfileService;

/**
 * A class is used to provide the user auctions participations method to a controller.
 * @author semenovich
 *
 */
public class GetUserAuctionParticipations implements ServletCommand {

	private static final Logger logger = Logger.getLogger(GetUserAuctionParticipations.class);
	
	private static final String USER_LOGIN = "userLogin";
	
	private static final String CHOOSEN_AUCTIONS_PAGE_NUMBER = "choosenAuctionsPageNumber";
	private static final String USER_AUCTION_PARTICIPATIONS_INFO = "userAuctionParticipationsInfo";
	
	private static final String ERROR_PAGE = "error.jsp";
	private static final String NOT_FOUND_PAGE = "404.jsp";
	private static final String CURRENT_PAGE = "user.jsp";
	private static final String ACCESS_DENIED_PAGE = "access-denied.jsp";
	
	private ProfileService service;
	
	/**
	 * Default constructor.
	 */
	public GetUserAuctionParticipations() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getProfileService();
	}

	/**
	 * Gets a user auctions participations.
	 * <br> The method expects a "userLogin" (from a request and a session) and "choosenAuctionsPageNumber" parameters with values.
	 * <br>
	 * <br> The method puts the following attributes:
	 * <br> 1. "userAuctionParticipationsInfo" - an auctions list.
	 * <br> 2. "choosenAuctionsPageNumber" - a chosen page.
	 * <br>
	 * <br> In the event of an error, a redirect to the error page occurs.
	 * <br> If a user doesn't have enough privileges, a redirect to the access denied page occurs.
	 * <br> If a user doesn't exist, a redirect to the 404 page occurs.
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String userLogin = request.getParameter(USER_LOGIN);
			if (!userLogin.equals((String)request.getSession().getAttribute(USER_LOGIN))) {
				response.sendRedirect(ACCESS_DENIED_PAGE);
				return;
			}
			int page = Integer.valueOf((String)request.getParameter(CHOOSEN_AUCTIONS_PAGE_NUMBER));
			AuctionsInfo acutionsInfo = service.getUserAuctionParticipations(userLogin, page);
			if (acutionsInfo != null) {
				request.setAttribute(USER_LOGIN, userLogin);
				request.setAttribute(USER_AUCTION_PARTICIPATIONS_INFO, acutionsInfo);
				request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
			}
			else {
				response.sendRedirect(NOT_FOUND_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("Error in GetUserAuctionParticipations", e);
			response.sendRedirect(ERROR_PAGE);
		}		
	}
}


