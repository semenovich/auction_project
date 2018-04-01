package by.bsu.auction.controller.command.realization.user_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsu.auction.controller.command.ServletCommand;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.user_operation.UserOperationService;

public class PayForLot implements ServletCommand {

	private static final Logger logger = Logger.getLogger(PayForLot.class);
	
	private static final String AUCTION_ID = "auctionId";
	
	private static final String LOT_ID = "lotId";
	
	private static final String SUCCESSFUL_PAGE = "successful.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private UserOperationService service;
	
	public PayForLot() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getUserOperationService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer auctionId = Integer.valueOf(request.getParameter(AUCTION_ID));
			Integer lotId = Integer.valueOf(request.getParameter(LOT_ID));
			service.payForLot(auctionId, lotId);
			response.sendRedirect(SUCCESSFUL_PAGE);
		} catch (ServiceException e) {
			logger.error("Error in PayForLot", e);
			response.sendRedirect(ERROR_PAGE);
		}
	}

}
