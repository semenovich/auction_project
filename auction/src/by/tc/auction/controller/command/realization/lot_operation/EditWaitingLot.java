package by.tc.auction.controller.command.realization.lot_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.tc.auction.controller.command.ServletCommand;
import by.tc.auction.entity.Lot;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.exception.LotInfoException;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.lot_operation.LotOperationService;
import by.tc.auction.service.lot_operation.realization.LotOperationServiceImpl;

public class EditWaitingLot implements ServletCommand {

	private static final Logger logger = Logger.getLogger(EditWaitingLot.class);
	
	private static final String USER_LOGIN = "userLogin";
	
	private static final String LOT_ID = "lotId";
	private static final String LOT_NAME = "lotName";
	private static final String LOT_DESCRIPTION = "lotDescription";
	private static final String LOT_QUANTITY = "lotQuantity";
	private static final String LOT_OWNER = "lotOwner";
	
	private static final String LOT_DATA_INVALID = "isLotDataInvalid";
	
	private static final String SUCCESSFUL_PAGE = "FrontController?command=GET_LOT_INFO&lotId=";
	private static final String INVAID_DATA_PAGE = "FrontController?command=GET_LOT_INFO&lotId=";
	private static final String ACCESS_DENIED_PAGE = "access_denied,jsp";
	private static final String ERROR_PAGE = "error.jsp";

	private LotOperationService service = new LotOperationServiceImpl();

	public EditWaitingLot() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getLotOpeationService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Lot lot = parseLot(request);
		try {
			if (checkOwner(request) && service.editWaitingLot(lot)) {
				response.sendRedirect(SUCCESSFUL_PAGE + lot.getId());
			}
			else {
				if (service.getLotInfo(lot.getId()) == null) {
					response.sendRedirect(ERROR_PAGE);
				}
				else{
					response.sendRedirect(ACCESS_DENIED_PAGE);
				}
			}
		} catch (ServiceException e) {
			logger.error("Error in EditConfirmingLot", e);
			response.sendRedirect(ERROR_PAGE);
		} catch (LotInfoException e) {
			request.setAttribute(LOT_DATA_INVALID, true);
			request.getRequestDispatcher(INVAID_DATA_PAGE + lot.getId()).forward(request, response);
		}
	}

	private boolean checkOwner(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return request.getParameter(LOT_OWNER).equals((String)session.getAttribute(USER_LOGIN));	
	}
	
	private Lot parseLot(HttpServletRequest request) {
		Lot lot = new Lot();
		lot.setId(Integer.valueOf(request.getParameter(LOT_ID)));
		lot.setName(request.getParameter(LOT_NAME));
		lot.setDescription(request.getParameter(LOT_DESCRIPTION));
		lot.setQuantity(Integer.valueOf(request.getParameter(LOT_QUANTITY)));
		return lot;
	}
}
