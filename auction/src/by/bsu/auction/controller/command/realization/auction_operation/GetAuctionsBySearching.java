package by.bsu.auction.controller.command.realization.auction_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsu.auction.controller.command.ServletCommand;
import by.bsu.auction.entity.AuctionsInfo;
import by.bsu.auction.service.ServiceFactory;
import by.bsu.auction.service.auction_operation.AuctionOperationService;
import by.bsu.auction.service.exception.ServiceException;

public class GetAuctionsBySearching implements ServletCommand {

	private static final Logger logger = Logger.getLogger(GetAuctionsBySearching.class);
	
	private static final String SEARCH_LINE = "searchLine";
	private static final String CHOOSEN_AUCTIONS_PAGE_NUMBER = "auctionsPageNumber";
	private static final String AUCTIONS_INFO = "auctionsInfo";
	private static final String ERROR_PAGE = "error.jsp";
	private static final String CURRENT_PAGE = "auction-list.jsp";
	
	private AuctionOperationService service;
	
	public GetAuctionsBySearching() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getAuctionOperationService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int page = Integer.valueOf((String)request.getParameter(CHOOSEN_AUCTIONS_PAGE_NUMBER));
			AuctionsInfo auctionsInfo = service.getAuctionsBySearching(request.getParameter(SEARCH_LINE), page);
			request.setAttribute(AUCTIONS_INFO, auctionsInfo);
			request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
		} catch (ServiceException e) {
			logger.error("Error in GetAuctionsBySearching", e);
			response.sendRedirect(ERROR_PAGE);
		}	
	}

}
