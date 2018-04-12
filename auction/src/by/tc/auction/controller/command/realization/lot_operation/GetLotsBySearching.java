package by.tc.auction.controller.command.realization.lot_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.tc.auction.controller.command.ServletCommand;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.LotsInfo;
import by.tc.auction.entity.UserRole;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.lot_operation.LotOperationService;

public class GetLotsBySearching implements ServletCommand {

	private static final Logger logger = Logger.getLogger(GetLotsBySearching.class);
	
	private static final String LOCALE = "locale";
	
	private static final String USER_ROLE = "userRole";
	
	private static final String SEARCH_LINE = "searchLine";
	private static final String LIST_TYPE = "listType";
	private static final String SEARCHING_BY_SEARCH_LINE = "searchingBySearchLine";
	private static final String CHOOSEN_LOTS_PAGE_NUMBER = "lotsPageNumber";
	private static final String LOTS_INFO = "lotsInfo";
	
	private static final String ERROR_PAGE = "error.jsp";
	private static final String CURRENT_PAGE = "lot-list.jsp";
	private static final String ACCESS_DENIED_PAGE = "access-denied.jsp";
	
	private LotOperationService service;
	
	public GetLotsBySearching() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getLotOpeationService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getSession().getAttribute(USER_ROLE) != UserRole.ADMIN) {
				response.sendRedirect(ACCESS_DENIED_PAGE);
				return;
			}
			int page = Integer.valueOf((String)request.getParameter(CHOOSEN_LOTS_PAGE_NUMBER));
			LotsInfo lotsInfo = service.getLotsBySearching(request.getParameter(SEARCH_LINE), 
					Locale.valueOf((String) request.getSession().getAttribute(LOCALE)), page);
			request.setAttribute(LOTS_INFO, lotsInfo);
			request.setAttribute(LIST_TYPE, SEARCHING_BY_SEARCH_LINE);
			request.setAttribute(SEARCH_LINE, request.getParameter(SEARCH_LINE));
			request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
		} catch (ServiceException e) {
			logger.error("Error in GetLotsBySearching", e);
			response.sendRedirect(ERROR_PAGE);
		}
	}
}