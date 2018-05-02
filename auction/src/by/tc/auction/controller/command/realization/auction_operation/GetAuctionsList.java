package by.tc.auction.controller.command.realization.auction_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.tc.auction.controller.command.ServletCommand;
import by.tc.auction.entity.AuctionsInfo;
import by.tc.auction.entity.Locale;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.auction_operation.AuctionOperationService;
import by.tc.auction.service.exception.ServiceException;

/**
 * A class is used to provide the getting auctions list to a controller.
 * @author semenovich
 *
 */
public class GetAuctionsList implements ServletCommand {

	private static final Logger logger = Logger.getLogger(GetAuctionsList.class);
	
	private static final String LOCALE = "locale";
	private static final String CHOOSEN_AUCTIONS_PAGE_NUMBER = "auctionsPageNumber";
	
	private static final String LIST_TYPE = "listType";
	private static final String LIST = "list";
	private static final String AUCTIONS_INFO = "auctionsInfo";
	
	private static final String ERROR_PAGE = "error.jsp";
	private static final String AUCTIONS_INFO_PAGE = "auction-list.jsp";
	
	private AuctionOperationService service;
	
	/**
	 * Default constructor. 
	 */
	public GetAuctionsList() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getAuctionOperationService();
	}

	/**
	 * Gets an auctions list.
	 * <br> The method expects the following parameters:
	 * <br> 1. "locale" - a user locale (in a session).
	 * <br> 2. "auctionsPageNumber" - a chosen page.
	 * <br>
	 * <br> The method puts the following attributes:
	 * <br> 1. "auctionsInfo" (an auctions list).
	 * <br> 2. "listType" ("list").
	 * <br>
	 * <br> In the event of an error, a redirect to the error page occurs.
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int page = Integer.valueOf((String)request.getParameter(CHOOSEN_AUCTIONS_PAGE_NUMBER));
			AuctionsInfo auctionsInfo = service.getAuctions(Locale.valueOf((String) request.getSession().getAttribute(LOCALE)), page);
			request.setAttribute(AUCTIONS_INFO, auctionsInfo);
			request.setAttribute(LIST_TYPE, LIST);
			request.getRequestDispatcher(AUCTIONS_INFO_PAGE).forward(request, response);
		} catch (ServiceException e) {
			logger.error("Error in GetAuctionsList", e);
			response.sendRedirect(ERROR_PAGE);
		}	
	}
}
