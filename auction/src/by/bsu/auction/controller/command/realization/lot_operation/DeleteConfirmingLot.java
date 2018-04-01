package by.bsu.auction.controller.command.realization.lot_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.bsu.auction.controller.command.ServletCommand;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.lot_operation.LotOperationService;

public class DeleteConfirmingLot implements ServletCommand {

	private static final Logger logger = Logger.getLogger(DeleteConfirmingLot.class);
	
	private static final String USER_LOGIN = "userLogin";
	
	private static final String LOT_ID = "lotId";
	private static final String LOT_OWNER = "lotOwner";
	
	private static final String SUCCESSFUL_PAGE = "successful.jsp";
	private static final String ACCESS_DENIED_PAGE = "access_denied,jsp";
	private static final String NOT_FOUND_PAGE = "404.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private LotOperationService service;
	
	public DeleteConfirmingLot() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getLotOpeationService();
	}

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
			}
			if (checkOwner(request) && service.deleteConfirmingLot(lotId)) {
				response.sendRedirect(SUCCESSFUL_PAGE);
			}
			else {
				if (service.getLotInfo(lotId) == null) {
					response.sendRedirect(NOT_FOUND_PAGE);
				}
				response.sendRedirect(ACCESS_DENIED_PAGE);
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
