package by.bsu.auction.controller.command.realization.auction_operation;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsu.auction.controller.command.ServletCommand;
import by.bsu.auction.service.ServiceFactory;
import by.bsu.auction.service.auction_operation.AuctionOperationService;
import by.bsu.auction.service.exception.ServiceException;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionType;
import by.tc.auction.entity.Bet;

public class CreateAuctionFromExistingLot implements ServletCommand {

	private static final Logger logger = Logger.getLogger(CreateAuctionFromExistingLot.class);
	
	private static final String AUCTION_TYPE = "auctionType"; 
	private static final String AUCTION_END_TIME = "auctionEndTime";
	private static final String AUCTION_MINIMUM_BET = "auctionMinimumPrice";
	
	private static final String LOT_ID = "lotId";
	
	private static final String SUCCESSFUL_PAGE = "successful.jsp";
	private static final String NOT_FOUND_PAGE = "404.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private AuctionOperationService service;
	
	public CreateAuctionFromExistingLot() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getAuctionOperationService();
	}

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
			}
			Auction auction = parseAuction(request);
			if (service.createAuctionFromExistingLot(auction, lotId)) {
				response.sendRedirect(SUCCESSFUL_PAGE);
			}
			else {
				response.sendRedirect(ERROR_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("Error in CreateAuctionFromExistingLot", e);
			response.sendRedirect(ERROR_PAGE);
		}
	}

	private Auction parseAuction(HttpServletRequest request) {
		Auction auction = new Auction();
		Long currentTime = Calendar.getInstance().getTimeInMillis();
		auction.setType(AuctionType.valueOf(request.getParameter(AUCTION_TYPE)));
		auction.setStartTime(new Date(currentTime));
		Bet minBet = new Bet();
		minBet.setValue(Double.valueOf(request.getParameter(AUCTION_MINIMUM_BET)));
		auction.setMinBet(minBet);;
		if (AuctionType.valueOf(request.getParameter(AUCTION_TYPE)) == AuctionType.ONLINE) {
			Long remainTime = TimeGetter.getInstance().getMilliseconds(Time.valueOf(request.getParameter(AUCTION_END_TIME)));
			auction.setEndTime(new Date(currentTime + remainTime));
		}
		return auction;
	}
}
