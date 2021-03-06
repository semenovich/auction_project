package by.tc.auction.controller.command.realization.lot_operation;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.tc.auction.controller.command.ServletCommand;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotStatus;
import by.tc.auction.entity.LotType;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.exception.LotInfoException;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.lot_operation.LotOperationService;

/**
 * A class is used to provide the lot offering method to a controller.
 * @author semenovich
 *
 */
public class OfferLot implements ServletCommand {

	private static final Logger logger = Logger.getLogger(OfferLot.class);
	
	private static final String USER_LOGIN = "userLogin";
	
	private static final String LOCALE = "locale";
	
	private static final String LOT_NAME = "lotName";
	private static final String LOT_DESCRIPTION = "lotDescription";
	private static final String LOT_QUANTITY = "lotQuantity";
	private static final String LOT_TYPE = "lotType";
	private static final String LOT_PICTURE = "lotPicture";
	private static final LotStatus LOT_STATUS = LotStatus.CONFIRMING;
	
	private static final String LOT_DATA_INVALID = "isLotDataInvalid";
	
	private static final String SUCCESSFUL_PAGE = "FrontController?command=GET_USER_LOTS&choosenLotsPageNumber=1&userLogin=";
	private static final String CURRENT_PAGE = "lot-offer.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private LotOperationService service;
	
	/**
	 * Default constructor.
	 */
	public OfferLot() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getLotOpeationService();
	}

	/**
	 * Offers a lot.
	 * <br> The method expects the following parameters with values:
	 * <br> 1. "lotName" - a lot name.
	 * <br> 2. "lotDescription" - a lot description.
	 * <br> 3. "lotQuantity" - a lot quantity.
	 * <br> 4. "lotType" - a lot type.
	 * <br> 5. "userLogin" - an owner login (from a session).
	 * <br> 6. "locale" - a locale (from a session).
	 * <br>
	 * <br> In the event of an error, a redirect to the error page occurs.
	 * <br> If lot data is incorrect, the attribute "isLotDataInvalid = true" will be sent back.
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			
			String ownerLogin = (String) session.getAttribute(USER_LOGIN);
			Locale locale = Locale.valueOf((String) session.getAttribute(LOCALE));
			Lot lot = parseLot(request, ownerLogin, locale);
			
			service.createLot(lot);
			
			response.sendRedirect(SUCCESSFUL_PAGE + (String) session.getAttribute(USER_LOGIN));
		} catch (ServiceException e) {
			logger.error("Error in OfferLot", e);
			response.sendRedirect(ERROR_PAGE);
		} catch (LotInfoException e) {
			request.setAttribute(LOT_DATA_INVALID, true);
			request.getRequestDispatcher(CURRENT_PAGE).forward(request, response);
		}
	}
	
	private Lot parseLot(HttpServletRequest request, String ownerLogin, Locale locale) {
		Lot lot = new Lot();
		lot.setOwner(ownerLogin);
		lot.setName(request.getParameter(LOT_NAME));
		lot.setDescription(request.getParameter(LOT_DESCRIPTION));
		lot.setQuantity(Integer.valueOf(request.getParameter(LOT_QUANTITY)));
		lot.setType(LotType.valueOf(request.getParameter(LOT_TYPE)));
		lot.setAdded(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		lot.setStatus(LOT_STATUS);
		lot.setPicture(request.getParameter(LOT_PICTURE));
		lot.setLocale(locale);
		return lot;
	}
}
