package by.bsu.auction.dao.lot_operation.realization;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.bsu.auction.dao.authentication.realization.AuthDAOImpl;
import by.bsu.auction.dao.connection_pool.ConnectionPool;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.exception.DBConnectionException;
import by.bsu.auction.dao.lot_operation.LotOperationDAO;
import by.bsu.auction.dao.lot_operation.realization.util.LotProcessor;
import by.tc.auction.entity.Lot;

public class LotOperationDAOImpl implements LotOperationDAO {

	private LotProcessor lotProcessor = new LotProcessor();
	private static final Logger logger = Logger.getLogger(AuthDAOImpl.class);
	
	@Override
	public boolean offerLot(Lot lot) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return lotProcessor.createLot(connection, lot);
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Lot getLotInfo(Integer lotId) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (lotProcessor.isLotExist(connection, lotId)) {
				return lotProcessor.getLotInfo(connection, lotId);
			}
			return null;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean deleteConfirmingLot(Integer lotId) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (lotProcessor.checkIsLotConfirming(connection, lotId)) {
				return lotProcessor.deleteLot(connection, lotId);
			}
			return false;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean editConfirmingLot(Lot lot) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (lotProcessor.checkIsLotConfirming(connection, lot.getId())) {
				return lotProcessor.editLot(connection, lot);
			}
			return false;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ArrayList<Lot> getLotsList() throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return lotProcessor.getLotsList(connection);
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ArrayList<Lot> getLotsBySearching(String searchLine) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return lotProcessor.getLotsBySearching(connection, searchLine);
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in LotOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
}
