package by.bsu.auction.controller.command.realization.lot_operation;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.bsu.auction.controller.command.ServletCommand;
import by.bsu.auction.entity.Lot;
import by.bsu.auction.entity.LotStatus;
import by.bsu.auction.entity.LotType;
import by.bsu.auction.service.ServiceFactory;
import by.bsu.auction.service.exception.LotInfoException;
import by.bsu.auction.service.exception.ServiceException;
import by.bsu.auction.service.lot_operation.LotOperationService;

public class OfferLot implements ServletCommand {

	private static final Logger logger = Logger.getLogger(OfferLot.class);
	
	private static final String CURRENT_USER_LOGIN = "userLogin";
	
	private static final String LOT_NAME = "lotName";
	private static final String LOT_DESCRIPTION = "lotDescription";
	private static final String LOT_QUANTITY = "lotQuantity";
	private static final String LOT_TYPE = "lotType";
	private static final String LOT_PICTURE = "lotPicture";
	private static final LotStatus LOT_STATUS = LotStatus.CONFIRMING;
	
	private static final String LOT_DATA_INVALID = "isLotDataInvalid";
	
	private static final String SUCCESSFUL_PAGE = "successful.jsp";
	private static final String CURRENT_PAGE = "lot-offer.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private LotOperationService service;
	
	public OfferLot() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getLotOpeationService();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String ownerLogin = (String) session.getAttribute(CURRENT_USER_LOGIN);
			Lot lot = parseLot(request, ownerLogin);
			service.offerLot(lot);
			response.sendRedirect(SUCCESSFUL_PAGE);
		} catch (ServiceException e) {
			logger.error("Error in OfferLot", e);
			response.sendRedirect(ERROR_PAGE);
		} catch (LotInfoException e) {
			request.setAttribute(LOT_DATA_INVALID, true);
			request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
		}
	}
	
	private Lot parseLot(HttpServletRequest request, String ownerLogin) {
		Lot lot = new Lot();
		lot.setOwner(ownerLogin);
		lot.setName(request.getParameter(LOT_NAME));
		lot.setDescription(request.getParameter(LOT_DESCRIPTION));
		lot.setQuantity(Integer.valueOf(request.getParameter(LOT_QUANTITY)));
		lot.setType(LotType.valueOf(request.getParameter(LOT_TYPE)));
		lot.setAdded(new Date(Calendar.getInstance().getTimeInMillis()));
		lot.setStatus(LOT_STATUS);
		lot.setPicture(request.getParameter(LOT_PICTURE));
		return lot;
	}
}
