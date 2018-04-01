package by.bsu.auction.controller.command.realization.auction_operation;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsu.auction.controller.command.ServletCommand;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionType;
import by.tc.auction.entity.Bet;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotStatus;
import by.tc.auction.entity.LotType;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.auction_operation.AuctionOperationService;
import by.tc.auction.service.exception.LotInfoException;
import by.tc.auction.service.exception.ServiceException;

public class CreateAuctionWithLot implements ServletCommand {

	private static final Logger logger = Logger.getLogger(CreateAuctionWithLot.class);
	
	private static final String CURRENT_USER_LOGIN = "userLogin";

	private static final String AUCTION_TYPE = "auctionType"; 
	private static final String AUCTION_END_TIME = "auctionEndTime";
	private static final String AUCTION_MINIMUM_BET = "auctionMinimumPrice";
	
	private static final String LOT_NAME = "lotName";
	private static final String LOT_DESCRIPTION = "lotDescription";
	private static final String LOT_QUANTITY = "lotQuantity";
	private static final String LOT_TYPE = "lotType";
	private static final String LOT_PICTURE = "lotPicture";
	private static final LotStatus LOT_STATUS = LotStatus.READY;
	
	private static final String LOT_DATA_INVALID = "isLotDataInvalid";
	
	private static final String SUCCESSFUL_PAGE = "successful.jsp";
	private static final String CURRENT_PAGE = "auction-create.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	AuctionOperationService service;
	
	public CreateAuctionWithLot() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getAuctionOperationService();
	}

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

	private Lot parseLot(HttpServletRequest request) {
		Lot lot = new Lot();
		lot.setOwner((String)request.getSession().getAttribute(CURRENT_USER_LOGIN));
		lot.setName(request.getParameter(LOT_NAME));
		lot.setDescription(request.getParameter(LOT_DESCRIPTION));
		lot.setQuantity(Integer.valueOf(request.getParameter(LOT_QUANTITY)));
		lot.setType(LotType.valueOf(request.getParameter(LOT_TYPE)));
		lot.setAdded(new Date(Calendar.getInstance().getTimeInMillis()));
		lot.setStatus(LOT_STATUS);
		lot.setPicture(request.getParameter(LOT_PICTURE));
		return lot;
	}
}
