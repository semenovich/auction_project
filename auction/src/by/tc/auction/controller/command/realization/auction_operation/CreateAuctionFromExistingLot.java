package by.tc.auction.controller.command.realization.auction_operation;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger; 

import by.tc.auction.controller.command.ServletCommand;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionType;
import by.tc.auction.entity.Bet;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.auction_operation.AuctionOperationService;
import by.tc.auction.service.exception.ServiceException;

/**
 * A class is used to provide the creating an auction from an existing lot method to a controller.
 * @author semenovich
 *
 */
public class CreateAuctionFromExistingLot implements ServletCommand {

	private static final Logger logger = Logger.getLogger(CreateAuctionFromExistingLot.class);
	
	private static final String AUCTION_TYPE = "auctionType"; 
	private static final String AUCTION_END_TIME = "auctionEndTime";
	private static final String AUCTION_MINIMUM_BET = "auctionMinimumPrice";
	
	private static final String LOT_ID = "lotId";
	
	private static final String INVALID_BET = "isBetInvalid";
	
	private static final String CURRENT_PAGE = "lot.jsp";
	private static final String SUCCESSFUL_PAGE = "FrontController?command=GET_AUCTIONS_LIST&auctionsPageNumber=1";
	private static final String NOT_FOUND_PAGE = "404.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private AuctionOperationService service;
	
	/**
	 * Default constructor.
	 */
	public CreateAuctionFromExistingLot() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getAuctionOperationService();
	}

	/**
	 * Creates an auction from an existing lot.
	 * <br> The method expects the following parameters with values:
	 * <br> 1. "lotId" - a lot ID.
	 * <br> 2. "auctionType" - an auction type.
	 * <br> 3. "auctionEndTime" - auction end time (only for ONLINE type; only "TEN_MINUTES", "HOUR", "DAY", "WEEK" values must be).
	 * <br> 4. "auctionMinimumPrice" - an auction MIN bet.
	 * <br>
	 * <br> In the event of an error, a redirect to the error page occurs.
	 * <br> If the minimum bet is incorrect, the attribute "isBetInvalid = true" will be sent back.
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String lotIdStr = request.getParameter(LOT_ID);
			Integer lotId = null;
			if (!lotIdStr.isEmpty()) {
				lotId = Integer.valueOf(lotIdStr);
			}
			else {
				response.sendRedirect(NOT_FOUND_PAGE);
				return;
			}
			Auction auction = parseAuction(request);
			if (service.createAuctionWithExistingLot(auction, lotId)) {
				response.sendRedirect(SUCCESSFUL_PAGE);
			}
			else {
				response.sendRedirect(ERROR_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("Error in CreateAuctionFromExistingLot", e);
			response.sendRedirect(ERROR_PAGE);
		} catch (NumberFormatException e1) {
			request.setAttribute(INVALID_BET, true);
			request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
		}
	}

	private Auction parseAuction(HttpServletRequest request)  {
		Auction auction = new Auction();
		Long currentTime = Calendar.getInstance().getTimeInMillis();
		auction.setType(AuctionType.valueOf(request.getParameter(AUCTION_TYPE)));
		auction.setStartTime(new Timestamp(currentTime));
		Bet minBet = new Bet();
		minBet.setValue(Double.parseDouble(request.getParameter(AUCTION_MINIMUM_BET)));
		auction.setMinBet(minBet);;
		if (AuctionType.valueOf(request.getParameter(AUCTION_TYPE)) == AuctionType.ONLINE) {
			Long remainTime = TimeGetter.getInstance().getMilliseconds(Time.valueOf(request.getParameter(AUCTION_END_TIME)));
			auction.setEndTime(new Timestamp(currentTime + remainTime));
		}
		return auction;
	}
}
