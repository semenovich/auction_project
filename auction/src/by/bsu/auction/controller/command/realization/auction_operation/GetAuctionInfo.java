package by.bsu.auction.controller.command.realization.auction_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsu.auction.controller.command.ServletCommand;
import by.bsu.auction.service.ServiceFactory;
import by.bsu.auction.service.auction_operation.AuctionOperationService;
import by.bsu.auction.service.exception.ServiceException;
import by.tc.auction.entity.Auction;

public class GetAuctionInfo implements ServletCommand {
	
	private static final Logger logger = Logger.getLogger(GetAuctionInfo.class);

	private static final String AUCTION = "auction";
	
	private static final String AUCTION_ID = "auctionId";
	
	private static final String AUCTION_PAGE = "auction.jsp";
	private static final String NOT_FOUND_PAGE = "404.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private AuctionOperationService service;
	
	public GetAuctionInfo() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getAuctionOperationService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String auctionId = request.getParameter(AUCTION_ID);
			Auction auction = null;
			if (!auctionId.isEmpty()) {
				auction = service.getAuctionInfo(Integer.valueOf(auctionId));
			}
			if (auction != null) {
				request.setAttribute(AUCTION, auction);
				request.getRequestDispatcher(AUCTION_PAGE).forward(request, response);
			}
			else {
				response.sendRedirect(NOT_FOUND_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("Error in GetAuctionInfo", e);
			response.sendRedirect(ERROR_PAGE);
		}
	}

}
