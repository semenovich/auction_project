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

/**
 * A class is used to provide the getting lots list method to a controller.
 * @author semenovich
 *
 */
public class GetLotsList implements ServletCommand {

	private static final Logger logger = Logger.getLogger(GetLotsList.class);
	
	private static final String LOCALE = "locale";
	
	private static final String USER_ROLE = "userRole";
	
	private static final String CHOOSEN_LOTS_PAGE_NUMBER = "lotsPageNumber";
	private static final String LIST_TYPE = "listType";
	private static final String LIST = "list";
	private static final String LOTS_INFO = "lotsInfo";
	
	private static final String ERROR_PAGE = "error.jsp";
	private static final String CURRENT_PAGE = "lot-list.jsp";
	private static final String ACCESS_DENIED_PAGE = "access-denied.jsp";
	
	private LotOperationService service;
	
	/**
	 * Default constructor.
	 */
	public GetLotsList() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getLotOpeationService();
	}

	/**
	 * Gets a lots list.
	 * <br> The method expects the following parameters:
	 * <br> 1. "locale" - a user locale (in a session).
	 * <br> 2. "lotsPageNumber" - a chosen page.
	 * <br> 3. "userRole" - a user role.
	 * <br>
	 * <br> The method puts the following attributes:
	 * <br> 1. "lotsInfo" (a lots list).
	 * <br> 2. "listType" ("list").
	 * <br>
	 * <br> In the event of an error, a redirect to the error page occurs.
	 * <br> If a user doesn't have enough privileges, a redirect to the access denied page occurs.
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getSession().getAttribute(USER_ROLE) != UserRole.ADMIN) {
				response.sendRedirect(ACCESS_DENIED_PAGE);
				return;
			}
			
			int page = Integer.valueOf((String)request.getParameter(CHOOSEN_LOTS_PAGE_NUMBER));
			LotsInfo lotsInfo = service.getLotsList(Locale.valueOf((String) request.getSession().getAttribute(LOCALE)), page);
			
			request.setAttribute(LOTS_INFO, lotsInfo);
			request.setAttribute(LIST_TYPE, LIST);
			
			request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
		} catch (ServiceException e) {
			logger.error("Error in GetLotsList", e);
			response.sendRedirect(ERROR_PAGE);
		}	
	}

}
