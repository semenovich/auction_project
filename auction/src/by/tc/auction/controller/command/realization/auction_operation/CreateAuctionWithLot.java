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
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotStatus;
import by.tc.auction.entity.LotType;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.auction_operation.AuctionOperationService;
import by.tc.auction.service.exception.LotInfoException;
import by.tc.auction.service.exception.ServiceException;

/**
 * A class is used to provide the creating an auction with a lot method to a controller.
 * @author semenovich
 *
 */
public class CreateAuctionWithLot implements ServletCommand {

	private static final Logger logger = Logger.getLogger(CreateAuctionWithLot.class);
	
	private static final String CURRENT_USER_LOGIN = "userLogin";

	private static final String LOCALE = "locale";
	
	private static final String AUCTION_TYPE = "auctionType"; 
	private static final String AUCTION_END_TIME = "auctionEndTime";
	private static final String AUCTION_MINIMUM_BET = "auctionMinimumPrice";
	
	private static final String LOT_NAME = "lotName";
	private static final String LOT_DESCRIPTION = "lotDescription";
	private static final String LOT_QUANTITY = "lotQuantity";
	private static final String LOT_TYPE = "lotType";
	private static final LotStatus LOT_STATUS = LotStatus.READY;
	
	private static final String INVALID_BET = "isBetInvalid";
	private static final String LOT_DATA_INVALID = "isLotDataInvalid";
	
	private static final String SUCCESSFUL_PAGE = "FrontController?command=GET_AUCTIONS_LIST&auctionsPageNumber=1";
	private static final String CURRENT_PAGE = "auction-create.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private AuctionOperationService service;
	
	/**
	 * Default constructor.
	 */
	public CreateAuctionWithLot() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getAuctionOperationService();
	}

	/**
	 * Creates an auction from an existing lot.
	 * <br> The method expects the following parameters with values:
	 * <br> 1. "lotName" - a lot name.
	 * <br> 2. "lotDescription" - a lot description.
	 * <br> 3. "lotQuantity" - a lot quantity.
	 * <br> 4. "lotType" - a lot type.
	 * <br> 5. "auctionType" - an auction type.
	 * <br> 6. "auctionEndTime" - auction end time (only for ONLINE type; only "TEN_MINUTES", "HOUR", "DAY", "WEEK" values must be).
	 * <br> 7. "auctionMinimumPrice" - an auction MIN bet.
	 * <br>
	 * <br> In the event of an error, a redirect to the error page occurs.
	 * <br> If the minimum bet is incorrect, the attribute "isBetInvalid = true" will be sent back.
	 * <br> If lot data is incorrect, the attribute "isLotDataInvalid = true" will be sent back.
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Auction auction = parseAuction(request);
			Lot lot = parseLot(request);
			service.createAuctionWithLot(auction, lot);
			response.sendRedirect(SUCCESSFUL_PAGE);
		} catch (ServiceException e) {
			logger.error("CreateAuctionWithLot", e);
			response.sendRedirect(ERROR_PAGE);
		} catch (LotInfoException e) {
			request.setAttribute(LOT_DATA_INVALID, true);
			request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
		} catch (NumberFormatException e1) {
			request.setAttribute(INVALID_BET, true);
			request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
		}
	}
	
	private Auction parseAuction(HttpServletRequest request) throws NumberFormatException {
		Auction auction = new Auction();
		Long currentTime = Calendar.getInstance().getTimeInMillis();
		
		auction.setType(AuctionType.valueOf(request.getParameter(AUCTION_TYPE)));
		auction.setStartTime(new Timestamp(currentTime));
		
		Bet minBet = new Bet();
		minBet.setValue(Double.valueOf(request.getParameter(AUCTION_MINIMUM_BET)));
		
		auction.setMinBet(minBet);;
		
		if (AuctionType.valueOf(request.getParameter(AUCTION_TYPE)) == AuctionType.ONLINE) {
			Long remainTime = TimeGetter.getInstance().getMilliseconds(Time.valueOf(request.getParameter(AUCTION_END_TIME)));
			auction.setEndTime(new Timestamp(currentTime + remainTime));
		}
		return auction;
	}

	private Lot parseLot(HttpServletRequest request) {
		Lot lot = new Lot();
		lot.setOwner((String)request.getSession().getAttribute(CURRENT_USER_LOGIN));
		lot.setName(request.getParameter(LOT_NAME));
		lot.setDescription(request.getParameter(LOT_DESCRIPTION));
		lot.setQuantity(Integer.valueOf(request.getParameter(LOT_QUANTITY)));
		lot.setType(LotType.valueOf(request.getParameter(LOT_TYPE)));
		lot.setAdded(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		lot.setStatus(LOT_STATUS);
		lot.setLocale(Locale.valueOf((String)request.getSession().getAttribute(LOCALE)));
		return lot;
	}
}
