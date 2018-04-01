package by.bsu.auction.controller.command.realization.admin_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.bsu.auction.controller.command.ServletCommand;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.admin_operation.AdminOperationService;
import by.tc.auction.service.exception.ServiceException;

public class UnblockLot implements ServletCommand {

	private static final Logger logger = Logger.getLogger(UnblockLot.class);
	
	private static final String LOT_ID = "lotId";
	
	private static final String SUCCESSFUL_PAGE = "successful.jsp";
	private static final String NOT_FOUND_PAGE = "404.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private AdminOperationService service;
	
	public UnblockLot() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getAdminOperationService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String lotId = request.getParameter(LOT_ID);
			if (!lotId.isEmpty() && service.unblockLot(Integer.valueOf(lotId))) {
				response.sendRedirect(SUCCESSFUL_PAGE);	
			}
			else {
				response.sendRedirect(NOT_FOUND_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("Error in UnblockLot", e);
			response.sendRedirect(ERROR_PAGE);
		}
	}

}
