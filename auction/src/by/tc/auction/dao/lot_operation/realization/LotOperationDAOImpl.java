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

public class LotOperationDAOImpl implements LotOperationDAO {

	private final LotProcessor lotProcessor = new LotProcessor();
	private final LotInfoGetter lotInfoGetter = new LotInfoGetter();
	private final LotsSearcher lotSearcher = new LotsSearcher();
	private final LotChecker lotCheker = new LotChecker();
	private static final Logger logger = Logger.getLogger(AuthDAOImpl.class);
	
	@Override
	public boolean offerLot(Lot lot) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return lotProcessor.createLot(connection, lot);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

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

	@Override
	public boolean deleteWaitingLot(Integer lotId) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (lotCheker.isLotWaiting(connection, lotId)) {
				return lotProcessor.deleteLot(connection, lotId);
			}
			return false;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean editWaitingLot(Lot lot) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (lotCheker.isLotWaiting(connection, lot.getId())) {
				return lotProcessor.editLot(connection, lot);
			}
			return false;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ArrayList<Lot> getLotsList(Locale locale) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return lotInfoGetter.getLotsList(connection, locale);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ArrayList<Lot> getLotsBySearching(String searchLine, Locale locale) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return lotSearcher.getLotsBySearching(connection, searchLine, locale);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ArrayList<Lot> getLotsByType(LotType lotType, Locale locale) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return lotSearcher.getLotsByType(connection, lotType, locale);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean uploadLotImage(Integer lotId, String imagePath) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (lotCheker.isLotExist(connection, lotId)) {
				return lotProcessor.uploadLotImage(connection, lotId, imagePath);
			}
			return false;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
}
