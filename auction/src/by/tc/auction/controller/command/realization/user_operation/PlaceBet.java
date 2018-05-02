package by.tc.auction.controller.command.realization.user_operation;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.tc.auction.controller.command.ServletCommand;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Bet;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.exception.BetException;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.user_operation.UserOperationService;

/**
 * A class is used to provide the placing bet method to a controller.
 * @author semenovich
 *
 */
public class PlaceBet implements ServletCommand {

	private static final Logger logger = Logger.getLogger(PlaceBet.class);
	
	private static final String CURRENT_USER_LOGIN = "userLogin";
	private static final String USER_BET = "userBet";
	
	private static final String AUCTION_ID = "auctionId";
	private static final String AUCTION_MINIMUM_BET = "auctionMinimumPrice";
	
	private static final String INVALID_BET = "isBetInvalid";
	
	private static final String SUCCESSFUL_PAGE = "FrontController?command=GET_AUCTION_INFO&auctionId=";
	private static final String BET_ERROR_PAGE = "FrontController?command=GET_AUCTION_INFO&auctionId=";
	private static final String ERROR_PAGE = "error.jsp";
	
	private UserOperationService service;
	
	/**
	 * Default constructor.
	 */
	public PlaceBet() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getUserOperationService();
	}

	/**
	 * Places a bet in an auction.
	 * <br> The methods expects the following parameters:
	 * <br> 1. "userLogin" - a user login (from a session). 
	 * <br> 2. "userBet" - a user bet (a number).
	 * <br> 3. "auctionId" - an auction ID.
	 * <br> 4. "auctionMinimumPrice"  - an auction MIN bet.
	 * <br>
	 * <br> In the event of an error, a redirect to the error page occurs.
	 * <br> If bet data is incorrect, the attribute "isBetInvalid = true" will be sent back.
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Auction auction = null;
		try {
			auction = parseAuction(request);
			Bet userBet = new Bet();
			userBet.setValue(Double.valueOf(request.getParameter(USER_BET)));
			Timestamp betTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
			if (service.placeBet(auction, request.getSession().getAttribute(CURRENT_USER_LOGIN).toString(), userBet, betTime)) {
				response.sendRedirect(SUCCESSFUL_PAGE + auction.getId());
			}
			else {
				response.sendRedirect(ERROR_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("Error in PlaceBet", e);
			response.sendRedirect(ERROR_PAGE);
		} catch (BetException | NumberFormatException e) {
			request.setAttribute(INVALID_BET, true);
			request.getRequestDispatcher(BET_ERROR_PAGE + auction.getId()).forward(request, response);
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
