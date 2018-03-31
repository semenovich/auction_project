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
import by.tc.auction.entity.AuctionsInfo;
import by.tc.auction.entity.LotType;

public class GetAuctionsByLotType implements ServletCommand {

	private static final Logger logger = Logger.getLogger(GetAuctionsByLotType.class);
	
	private static final String LOT_TYPE = "lotType";
	private static final String LIST_TYPE = "listType";
	private static final String SEARCHING_BY_LOT_TYPE = "searchingByLotType";
	private static final String CHOOSEN_AUCTIONS_PAGE_NUMBER = "auctionsPageNumber";
	private static final String AUCTIONS_INFO = "auctionsInfo";
	private static final String ERROR_PAGE = "error.jsp";
	private static final String CURRENT_PAGE = "auction-list.jsp";
	
	private AuctionOperationService service;
	
	public GetAuctionsByLotType() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getAuctionOperationService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int page = Integer.valueOf((String)request.getParameter(CHOOSEN_AUCTIONS_PAGE_NUMBER));
			LotType lotType = LotType.valueOf(request.getParameter(LOT_TYPE));
			AuctionsInfo auctionsInfo = service.getAuctionsByLotType(lotType, page);
			request.setAttribute(AUCTIONS_INFO, auctionsInfo);
			request.setAttribute(LIST_TYPE, SEARCHING_BY_LOT_TYPE);
			request.setAttribute(LOT_TYPE, lotType);
			request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
		} catch (ServiceException e) {
			logger.error("Error in GetAuctionsByLotType", e);
			response.sendRedirect(ERROR_PAGE);
		}	
	}

}
