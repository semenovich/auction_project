package by.bsu.auction.controller.command.realization.user_operation;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsu.auction.controller.command.ServletCommand;
import by.bsu.auction.entity.Auction;
import by.bsu.auction.entity.Bet;
import by.bsu.auction.service.ServiceFactory;
import by.bsu.auction.service.exception.BetException;
import by.bsu.auction.service.exception.ServiceException;
import by.bsu.auction.service.user_operation.UserOperationService;

public class PlaceBet implements ServletCommand {

	private static final Logger logger = Logger.getLogger(PlaceBet.class);
	
	private static final String CURRENT_USER_LOGIN = "userLogin";
	private static final String USER_BET = "userBet";
	
	private static final String AUCTION_ID = "auctionId";
	private static final String AUCTION_MINIMUM_BET = "auctionMinimumPrice";
	
	
	private static final String SUCCESSFUL_PAGE = "successful.jsp";
	private static final String BET_ERROR_PAGE = "bet-error.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private UserOperationService service;
	
	public PlaceBet() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getUserOperationService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Bet userBet = new Bet();
			userBet.setValue(Double.valueOf(request.getParameter(USER_BET)));
			Date betTime = new Date(Calendar.getInstance().getTimeInMillis());
			Auction auction = parseAuction(request);
			if (service.placeBet(auction, request.getSession().getAttribute(CURRENT_USER_LOGIN).toString(), userBet, betTime)) {
				response.sendRedirect(SUCCESSFUL_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("Error in PlaceBet", e);
			response.sendRedirect(ERROR_PAGE);
		} catch (BetException e) {
			response.sendRedirect(BET_ERROR_PAGE);
		}
	}

	private Auction parseAuction(HttpServletRequest request) {
		Auction auction = new Auction();
		auction.setId(Integer.valueOf(request.getParameter(AUCTION_ID)));
		Bet minBet = new Bet();
		minBet.setValue(Double.valueOf(request.getParameter(AUCTION_MINIMUM_BET)));
		auction.setMinBet(minBet);
		return auction;
	}
}
