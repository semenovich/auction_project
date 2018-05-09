package by.tc.auction.controller.command.realization.lot_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.tc.auction.controller.command.ServletCommand;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.lot_operation.LotOperationService;

/**
 * A class is used to provide the lot deleting method to a controller.
 * @author semenovich
 *
 */
public class DeleteWaitingLot implements ServletCommand {

	private static final Logger logger = Logger.getLogger(DeleteWaitingLot.class);
	
	private static final String USER_LOGIN = "userLogin";
	
	private static final String LOT_ID = "lotId";
	private static final String LOT_OWNER = "lotOwner";
	
	private static final String SUCCESSFUL_PAGE = "FrontController?command=GET_USER_LOTS&choosenLotsPageNumber=1&userLogin=";
	private static final String ACCESS_DENIED_PAGE = "access-denied,jsp";
	private static final String NOT_FOUND_PAGE = "404.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private LotOperationService service;
	
	/**
	 * Default constructor.
	 */
	public DeleteWaitingLot() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getLotOpeationService();
	}

	/**
	 * Remove a lot from an application.
	 * <br> The methods expects a "lotId", "lotOwner" and "userLogin"(from a session) parameters with values.
	 * <br>
	 * <br> In the event of an error, a redirect to the error page occurs.
	 * <br> If a user doesn't have enough privileges, a redirect to the access denied page occurs.
	 * <br> If a lot doesn't exist, a redirect to the 404 page occurs.
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String lotIdStr = request.getParameter(LOT_ID);
			Integer lotId = null;
			
			if (!lotIdStr.isEmpty()) {
				lotId = Integer.valueOf(lotIdStr);
			}
			else {
				response.sendRedirect(NOT_FOUND_PAGE);
				return;
			}
			
			if (checkOwner(request) && service.deleteWaitingLot(lotId)) {
				response.sendRedirect(SUCCESSFUL_PAGE + (String)request.getSession().getAttribute(USER_LOGIN));
			}
			else {
				if (service.getLotInfo(lotId) == null) {
					response.sendRedirect(ERROR_PAGE);
				}
				else {
					response.sendRedirect(ACCESS_DENIED_PAGE);
				}
			}
		} catch (ServiceException e) {
			logger.error("Error in DeleteConfirmingLot", e);
			response.sendRedirect(ERROR_PAGE);
		}
	}
	
	private boolean checkOwner(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return request.getParameter(LOT_OWNER).equals((String)session.getAttribute(USER_LOGIN));
		
	}
}
