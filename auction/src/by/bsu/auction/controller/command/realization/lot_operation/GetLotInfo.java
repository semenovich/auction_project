package by.bsu.auction.controller.command.realization.lot_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsu.auction.controller.command.ServletCommand;
import by.tc.auction.entity.Lot;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.lot_operation.LotOperationService;

public class GetLotInfo implements ServletCommand {	
	
	private static final Logger logger = Logger.getLogger(GetLotInfo.class);
	
	private static final String LOT = "lot";
	
	private static final String LOT_ID = "lotId";
	
	private static final String LOT_PAGE = "lot.jsp";
	private static final String NOT_FOUND_PAGE = "404.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private LotOperationService service;
	
	public GetLotInfo() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getLotOpeationService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String lotId = request.getParameter(LOT_ID);
			Lot lot =  null;
			if (!lotId.isEmpty()) {
				lot = service.getLotInfo(Integer.valueOf(lotId));
			}
			if (lot != null) {
				request.setAttribute(LOT, lot);
				request.getRequestDispatcher(LOT_PAGE).forward(request, response);
			}
			else {
				response.sendRedirect(NOT_FOUND_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("Error in GetLotInfo", e);
			response.sendRedirect(ERROR_PAGE);
		}
	}

}
