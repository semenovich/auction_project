package by.tc.auction.dao.lot_operation;

import java.util.ArrayList;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;

/**
 * An interface of operations with lots. An interface which provides methods for working with lots.
 * @author semenovich
 *
 */
public interface LotOperationDAO {
	
	/**
	 * Creates a lot. 
	 * @param lot - a lot which will be created.
	 * @return {@code true} - if a lot has been created. {@code false} - if a lot hasn't been created.
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean createLot(Lot lot) throws DAOException;
	
	/**
	 * Returns a lot info.
	 * @param lotId - a lot ID.
	 * @return A lot if a lot exists. {@code null} if a lot doesn't exist.
	 * @throws DAOException - if an error occurred during operation.
	 */
	Lot getLotInfo(Integer lotId) throws DAOException;
	
	/**
	 * Removes a waiting lot.
	 * @param lotId - a waiting lot ID.
	 * @return {@code true} - if a lot has been removed. {@code false} - if a lot hasn't been removed.
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean deleteWaitingLot(Integer lotId) throws DAOException;
	
	/**
	 * Edits a waiting lot.
	 * @param lot - an update lot info.
	 * @return {@code true} - if a lot has been edited. {@code false} - if a lot hasn't been edited.
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean editWaitingLot(Lot lot) throws DAOException;
	
	/**
	 * Uploads a lot image.
	 * @param lotId - a lot ID which image will be uploaded.
	 * @param imagePath - a path of the image.
	 * @return {@code true} - if a lot image has been uploaded. {@code false} - if a lot image hasn't been uploaded.
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean uploadLotImage(Integer lotId, String imagePath) throws DAOException;
	
	/**
	 * Returns a lot list.
	 * @param locale - a locale of lots.
	 * @return A list of lots if lots exist. Empty list if lots don't exist.
	 * @throws DAOException - if an error occurred during operation.
	 */
	ArrayList<Lot> getLotsList(Locale locale) throws DAOException;
	
	/**
	 * Returns a list of lots by matching a name of a lots.
	 * @param searchLine - a search line which will be matched with a lots name.
	 * @param locale - a locale of lots.
	 * @return A list of lots if such lots exist. Empty list if such lots don't exist.
	 * @throws DAOException - if an error occurred during operation.
	 */
	ArrayList<Lot> getLotsBySearching(String searchLine, Locale locale) throws DAOException;
	
	/**
	 * Returns a list of lots by searching by the type of a lots.
	 * @param lotType - a type of lots.
	 * @param locale - a locale of lots.
	 * @return A list of lots if such lots exist. Empty list if such lots don't exist.
	 * @throws DAOException - if an error occurred during operation.
	 */
	ArrayList<Lot> getLotsByType(LotType lotType, Locale locale) throws DAOException;
	
	/**
	 * Returns a list of waiting lots.
	 * @param locale - a locale of lots.
	 * @return A list of lots if such lots exist. Empty list if such lots don't exist.
	 * @throws DAOException - if an error occurred during operation.
	 */
	ArrayList<Lot> getWaitingLots(Locale locale) throws DAOException;
}
