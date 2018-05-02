package by.tc.auction.service.lot_operation.realization;

import java.util.ArrayList;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.lot_operation.LotOperationDAO;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;
import by.tc.auction.entity.LotsInfo;
import by.tc.auction.service.exception.LotInfoException;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.lot_operation.LotOperationService;
import by.tc.auction.service.lot_operation.realization.util.LotPortionGetter;
import by.tc.auction.service.lot_operation.realization.validation.LotInfoValidator;

/**
 * A class is used to provide methods for working with lots on an application logic level and in a database.
 * @author semenovich
 *
 */
public class LotOperationServiceImpl implements LotOperationService {

	private static final String ERROR_MESSAGE = "Invalid lot info";
	
	private LotOperationDAO lotOperationDAO;
	private LotPortionGetter lotPortionGetter = LotPortionGetter.getInstance();
	
	/**
	 * Default constructor.
	 */
	public LotOperationServiceImpl(){
		DAOFactory factory = DAOFactory.getInstance();
		lotOperationDAO = factory.getLotOperationDAO();
	}

	/**
	 * Creates a lot in a database if lot info is correct.
	 * <br> A lot name must be not empty.
	 * <br> A lot description must be not empty.
	 * <br> A lot quantity must be more than 0.
	 * @param lot - a lot which will be created.
	 * @return {@code true} - if a lot has been created. {@code false} - if a lot hasn't been created.
	 * @throws ServiceException - if an error occurred during operation in a database. 
	 * @throws LotInfoException - if lot info is incorrect.
	 */
	@Override
	public boolean createLot(Lot lot) throws ServiceException, LotInfoException {
		if (!LotInfoValidator.validate(lot)) {
			throw new LotInfoException(ERROR_MESSAGE);
		}
		try {
			return lotOperationDAO.createLot(lot);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a lot info from a database.
	 * @param lotId - a lot ID.
	 * @return A lot if a lot exists. {@code null} if a lot doesn't exist.
	 * @throws ServiceException - if an error occurred during operation in a database. 
	 */
	@Override
	public Lot getLotInfo(Integer lotId) throws ServiceException {
		try {
			return lotOperationDAO.getLotInfo(lotId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Removes a waiting lot from a database.
	 * In the method the same DAO method should be used.
	 * @param lotId - a lot ID.
	 * @return {@code true} - if a lot has been removed. {@code false} - if a lot hasn't been removed.
	 * @throws ServiceException - if an error occurred during operation in a database. 
	 */
	@Override
	public boolean deleteWaitingLot(Integer lotId) throws ServiceException {
		try {
			return lotOperationDAO.deleteWaitingLot(lotId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Edits a waiting lot in a database if lot info is correct.
	 * <br> A lot name must be not empty.
	 * <br> A lot description must be not empty.
	 * <br> A lot quantity must be more than 0.
	 * @param lot -  updated lot info.
	 * @return {@code true} - if a lot has been edited. {@code false} - if a lot hasn't been edited.
	 * @throws ServiceException - if an error occurred during operation in a database. 
	 * @throws LotInfoException - if lot info is incorrect.
	 */
	@Override
	public boolean editWaitingLot(Lot lot) throws ServiceException, LotInfoException {
		if (!LotInfoValidator.validate(lot)) {
			throw new LotInfoException(ERROR_MESSAGE);
		}
		try {
			return lotOperationDAO.editWaitingLot(lot);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns lots portion list from a database.
	 * @param locale - a locale of lots.
	 * @param page - a page of a lots list.
	 * @return A list of lots 10(&le; if lots in portion are less than 10) if lots exist. Empty list if lots don't exist.
	 * @throws ServiceException - if an error occurred during operation in a database.
	 */
	@Override
	public LotsInfo getLotsList(Locale locale, int page) throws ServiceException {
		try {
			ArrayList<Lot> lots = lotOperationDAO.getLotsList(locale);
			return lotPortionGetter.getLotsPortion(lots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a list of lots portion by matching a name of a lots from a database.
	 * @param searchLine - a search line which will be matched with a lots name.
	 * @param locale - a locale of lots.
	 * @param page - a page of a lots list.
	 * @return A list of lots 10(&le; if lots in portion are less than 10) if such lots exist. Empty list if such lots don't exist.
	 * @throws ServiceException - if an error occurred during operation in a database.
	 */
	@Override
	public LotsInfo getLotsBySearching(String searchLine, Locale locale, int page) throws ServiceException {
		try {
			ArrayList<Lot> lots = lotOperationDAO.getLotsBySearching(searchLine, locale);
			return lotPortionGetter.getLotsPortion(lots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a list of lots by searching by the type of a lots from a database.
	 * @param lotType - a lot type.
	 * @param locale - a locale of lots.
	 * @param page - a page of a lots list.
	 * @return A list of lots 10(&le; if lots in portion are less than 10) if such lots exist. Empty list if such lots don't exist.
	 * @throws ServiceException - if an error occurred during operation in a database.
	 */
	@Override
	public LotsInfo getLotsByType(LotType lotType, Locale locale, int page) throws ServiceException {
		try {
			ArrayList<Lot> lots = lotOperationDAO.getLotsByType(lotType, locale);
			return lotPortionGetter.getLotsPortion(lots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a list of waiting lots portion from a database.
	 * @param locale - a locale of lots.
	 * @param page - a page of a lots list.
	 * @return A list of lots 10(&le; if lots in portion are less than 10) if such lots exist. Empty list if such lots don't exist.
	 * @throws ServiceException - if an error occurred during operation in a database.
	 */
	@Override
	public LotsInfo getWaitingLots(Locale locale, int page) throws ServiceException {
		try {
			ArrayList<Lot> lots = lotOperationDAO.getWaitingLots(locale);
			return lotPortionGetter.getLotsPortion(lots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Uploads a lot image in a database.
	 * @param lotId - a lot ID which image will be uploaded.
	 * @param imagePath - a path of the image.
	 * @return {@code true} - if a lot image has been uploaded. {@code false} - if a lot image hasn't been uploaded.
	 * @throws ServiceException - if an error occurred during operation in a database. 
	 */
	@Override
	public boolean uploadLotImage(Integer lotId, String imagePath) throws ServiceException {
		try {
			return lotOperationDAO.uploadLotImage(lotId, imagePath);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
}
