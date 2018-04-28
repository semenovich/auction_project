package by.tc.auction.service.lot_operation;

import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;
import by.tc.auction.entity.LotsInfo;
import by.tc.auction.service.exception.LotInfoException;
import by.tc.auction.service.exception.ServiceException;

/**
 * An interface of operations with lots. An interface which provides methods for working with lots in an application logic and in a source.
 * @author semenovich
 *
 */
public interface LotOperationService {
	
	/**
	 * Creates a lot. 
	 * In the method the same DAO method should be used.
	 * @param lot - a lot which will be created.
	 * @return {@code true} - if a lot has been created. {@code false} - if a lot hasn't been created.
	 * @throws ServiceException - if an error occurred during operation. 
	 * @throws LotInfoException - if lot info is incorrect.
	 */
	boolean createLot(Lot lot) throws ServiceException, LotInfoException;
	
	/**
	 * Returns a lot info.
	 * In the method the same DAO method should be used.
	 * @param lotId - a lot ID.
	 * @return A lot if a lot exists. {@code null} if a lot doesn't exist.
	 * @throws ServiceException - if an error occurred during operation. 
	 */
	Lot getLotInfo(Integer lotId) throws ServiceException;
	
	/**
	 * Removes a waiting lot.
	 * In the method the same DAO method should be used.
	 * @param lotId - a lot ID.
	 * @return {@code true} - if a lot has been removed. {@code false} - if a lot hasn't been removed.
	 * @throws ServiceException - if an error occurred during operation. 
	 */
	boolean deleteWaitingLot(Integer lotId) throws ServiceException;
	
	/**
	 * Edits a waiting lot.
	 * In the method the same DAO method should be used.
	 * @param lot -  updated lot info.
	 * @return {@code true} - if a lot has been edited. {@code false} - if a lot hasn't been edited.
	 * @throws ServiceException - if an error occurred during operation. 
	 * @throws LotInfoException - if lot info is incorrect.
	 */
	boolean editWaitingLot(Lot lot) throws ServiceException, LotInfoException;
	
	/**
	 * Uploads a lot image.
	 * In the method the same DAO method should be used.
	 * @param lotId - a lot ID which image will be uploaded.
	 * @param imagePath - a path of the image.
	 * @return {@code true} - if a lot image has been uploaded. {@code false} - if a lot image hasn't been uploaded.
	 * @throws ServiceException - if an error occurred during operation. 
	 */
	boolean uploadLotImage(Integer lotId, String imagePath) throws ServiceException;
	
	/**
	 * Returns lots portion list.
	 * In the method the same DAO method should be used.
	 * @param locale - a locale of lots.
	 * @param page - a page of a lots list.
	 * @return A list of lots if lots exist. Empty list if lots don't exist.
	 * @throws ServiceException - if an error occurred during operation.
	 */
	LotsInfo getLotsList(Locale locale, int page) throws ServiceException;
	
	/**
	 * Returns a list of lots portion by matching a name of a lots.
	 * In the method the same DAO method should be used.
	 * @param searchLine - a search line which will be matched with a lots name.
	 * @param locale - a locale of lots.
	 * @param page - a page of a lots list.
	 * @return A list of lots if such lots exist. Empty list if such lots don't exist.
	 * @throws ServiceException - if an error occurred during operation.
	 */
	LotsInfo getLotsBySearching(String searchLine, Locale locale, int page) throws ServiceException;
	
	/**
	 * Returns a list of lots portion by searching by the type of a lots.
	 * @param lotType - a lot type.
	 * @param locale - a locale of lots.
	 * @param page - a page of a lots list.
	 * @return A list of lots if such lots exist. Empty list if such lots don't exist.
	 * @throws ServiceException - if an error occurred during operation.
	 */
	LotsInfo getLotsByType(LotType lotType, Locale locale, int page) throws ServiceException;
	
	/**
	 * Returns a list of waiting lots portion.
	 * @param locale - a locale of lots.
	 * @param page - a page of a lots list.
	 * @return A list of lots if such lots exist. Empty list if such lots don't exist.
	 * @throws ServiceException - if an error occurred during operation.
	 */
	LotsInfo getWaitingLots(Locale locale, int page) throws ServiceException;
}
