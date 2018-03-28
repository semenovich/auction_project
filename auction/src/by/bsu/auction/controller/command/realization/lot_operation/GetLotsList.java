package by.bsu.auction.controller.command.realization.lot_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsu.auction.controller.command.ServletCommand;
import by.bsu.auction.entity.LotsInfo;
import by.bsu.auction.service.ServiceFactory;
import by.bsu.auction.service.exception.ServiceException;
import by.bsu.auction.service.lot_operation.LotOperationService;

public class GetLotsList implements ServletCommand {

	private static final Logger logger = Logger.getLogger(GetLotsList.class);
	
	private static final String CHOOSEN_LOTS_PAGE_NUMBER = "lotsPageNumber";
	private static final String LOTS_INFO = "lotsInfo";
	private static final String ERROR_PAGE = "error.jsp";
	private static final String CURRENT_PAGE = "lot-list.jsp";
	
	private LotOperationService service;
	
	public GetLotsList() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getLotOpeationService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int page = Integer.valueOf((String)request.getParameter(CHOOSEN_LOTS_PAGE_NUMBER));
			LotsInfo lotsInfo = service.getLotsList(page);
			request.setAttribute(LOTS_INFO, lotsInfo);
			request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
		} catch (ServiceException e) {
			logger.error("Error inn GetLotsList", e);
			response.sendRedirect(ERROR_PAGE);
		}	
	}

}
