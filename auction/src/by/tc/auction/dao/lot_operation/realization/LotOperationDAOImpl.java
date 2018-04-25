package by.tc.auction.dao.lot_operation.realization;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.tc.auction.dao.authentication.realization.AuthDAOImpl;
import by.tc.auction.dao.connection_pool.ConnectionPool;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.exception.ConnectionPoolException;
import by.tc.auction.dao.lot_operation.LotOperationDAO;
import by.tc.auction.dao.lot_operation.realization.util.LotChecker;
import by.tc.auction.dao.lot_operation.realization.util.LotInfoGetter;
import by.tc.auction.dao.lot_operation.realization.util.LotProcessor;
import by.tc.auction.dao.lot_operation.realization.util.LotsSearcher;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;

/**
 * A class is used to provide methods for working with lots in a database.
 * @author semenovich
 *
 */
public class LotOperationDAOImpl implements LotOperationDAO {

	private final LotProcessor lotProcessor = new LotProcessor();
	private final LotInfoGetter lotInfoGetter = new LotInfoGetter();
	private final LotsSearcher lotSearcher = new LotsSearcher();
	private final LotChecker lotCheker = new LotChecker();
	private static final Logger logger = Logger.getLogger(AuthDAOImpl.class);
	
	/**
	 * Default constructor.
	 */
	public LotOperationDAOImpl() {}
	
	/**
	 * Creates a lot in a database. 
	 * @param lot - a lot which will be created. All fields must be filled in.
	 * @return {@code true} - if a lot has been created in a database. {@code false} - if a lot hasn't been created in a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database or not all fields are filled.
	 */
	@Override
	public boolean createLot(Lot lot) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			lotProcessor.createLot(connection, lot);
			return true;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a lot info from a database.
	 * @param lotId - a lot ID.
	 * @return A lot if a lot exists in a database. {@code null} if a lot doesn't exist in a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public Lot getLotInfo(Integer lotId) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
 			if (lotCheker.isLotExist(connection, lotId)) {
				return lotInfoGetter.getLotInfo(connection, lotId);
			}
			return null;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Removes a waiting lot from a database.
	 * <br> A waiting lot - is which has status 'CONFRMING' or 'READY' in a database.
	 * @param lotId - a waiting lot ID.
	 * @return {@code true} - if a lot has been removed from a database. {@code false} - if a lot hasn't been removed from a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public boolean deleteWaitingLot(Integer lotId) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (lotCheker.isLotWaiting(connection, lotId)) {
				lotProcessor.deleteLot(connection, lotId);
				return true;
			}
			return false;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Edits a waiting lot.
	 * @param lot - an update lot info. Only ID, name, description, quantity fields must be filled in.
	 * @return {@code true} - if a lot has been edited. {@code false} - if a lot is not waiting and hasn't been edited.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public boolean editWaitingLot(Lot lot) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (lotCheker.isLotWaiting(connection, lot.getId())) {
				lotProcessor.editLot(connection, lot);
				return true;
			}
			return false;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a lot list from a database.
	 * @param locale - a locale of lots.
	 * @return A list of lots if lots exist in a database. Empty list if lots don't exist in a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public ArrayList<Lot> getLotsList(Locale locale) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return lotInfoGetter.getLotsList(connection, locale);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a list of lots by matching a name of a lots from a database.
	 * @param searchLine - a search line which will be matched with a lots name.
	 * @param locale - a locale of lots.
	 * @return A list of lots if such lots exist in a database. Empty list if such lots don't exist in a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public ArrayList<Lot> getLotsBySearching(String searchLine, Locale locale) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return lotSearcher.getLotsBySearching(connection, searchLine, locale);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a list of lots by searching by the type of a lots from a database.
	 * @param lotType - a type of lots.
	 * @param locale - a locale of lots.
	 * @return A list of lots if such lots exist in a database. Empty list if such lots don't exist in a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public ArrayList<Lot> getLotsByType(LotType lotType, Locale locale) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return lotSearcher.getLotsByType(connection, lotType, locale);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a list of waiting lots from a database.
	 * <br> A waiting lot - is which has status 'CONFRMING' or 'READY' in a database.
	 * @param locale - a locale of lots.
	 * @return A list of lots if such lots exist in a database. Empty list if such lots don't exist in a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public ArrayList<Lot> getWaitingLots(Locale locale) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return lotSearcher.getWaitingLots(connection, locale);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Uploads a lot image in a database.
	 * @param lotId - a lot ID which image will be uploaded.
	 * @param imagePath - a path of the image.
	 * @return {@code true} - if a lot image has been uploaded in a database. {@code false} - if a lot doesn't exist and a image hasn't been uploaded in a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */	
	@Override
	public boolean uploadLotImage(Integer lotId, String imagePath) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (lotCheker.isLotExist(connection, lotId)) {
				lotProcessor.uploadLotImage(connection, lotId, imagePath);
				return true;
			}
			return false;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
}
