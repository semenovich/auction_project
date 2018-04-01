package by.bsu.auction.controller.command.realization.user_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsu.auction.controller.command.ServletCommand;
import by.tc.auction.entity.AuctionsInfo;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.user_operation.ProfileService;

public class GetUserAuctionParticipations implements ServletCommand {

	private static final Logger logger = Logger.getLogger(GetUserAuctionParticipations.class);
	
	private static final String USER_LOGIN = "userLogin";
	private static final String CHOOSEN_AUCTIONS_PAGE_NUMBER = "choosenAuctionsPageNumber";
	private static final String USER_AUCTION_PARTICIPATIONS_INFO = "userAuctionParticipationsInfo";
	private static final String ERROR_PAGE = "error.jsp";
	private static final String NOT_FOUND_PAGE = "404.jsp";
	private static final String CURRENT_PAGE = "user.jsp";
	
	private ProfileService service;
	
	public GetUserAuctionParticipations() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getProfileService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String userLogin = request.getParameter(USER_LOGIN);
			int page = Integer.valueOf((String)request.getParameter(CHOOSEN_AUCTIONS_PAGE_NUMBER));
			AuctionsInfo acutionsInfo = service.getUserAuctionParticipations(userLogin, page);
			if (acutionsInfo != null) {
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


