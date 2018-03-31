package by.bsu.auction.service.lot_operation.realization;

import java.util.ArrayList;

import by.bsu.auction.dao.DAOFactory;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.lot_operation.LotOperationDAO;
import by.bsu.auction.service.exception.LotInfoException;
import by.bsu.auction.service.exception.ServiceException;
import by.bsu.auction.service.lot_operation.LotOperationService;
import by.bsu.auction.service.lot_operation.realization.validation.Validator;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;
import by.tc.auction.entity.LotsInfo;

public class LotOperationServiceImpl implements LotOperationService {

	private static final String ERROR_MESSAGE = "Invalid lot info";
	
	LotOperationDAO lotOperationDAO;
	LotPortionGetter lotPortionGetter = LotPortionGetter.getInstance();
	
	public LotOperationServiceImpl(){
		DAOFactory factory = DAOFactory.getInstance();
		lotOperationDAO = factory.getLotOperationDAO();
	}

	@Override
	public boolean offerLot(Lot lot) throws ServiceException, LotInfoException {
		try {
			if (!Validator.validateLotInfo(lot)) {
				throw new LotInfoException(ERROR_MESSAGE);
			}
			return lotOperationDAO.offerLot(lot);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Lot getLotInfo(Integer lotId) throws ServiceException {
		try {
			return lotOperationDAO.getLotInfo(lotId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean deleteConfirmingLot(Integer lotId) throws ServiceException {
		try {
			return lotOperationDAO.deleteConfirmingLot(lotId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean editConfirmingLot(Lot lot) throws ServiceException, LotInfoException {
		try {
			if (!Validator.validateLotInfo(lot)) {
				throw new LotInfoException(ERROR_MESSAGE);
			}
			return lotOperationDAO.editConfirmingLot(lot);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public LotsInfo getLotsList(int page) throws ServiceException {
		try {
			ArrayList<Lot> lots = lotOperationDAO.getLotsList();
			return lotPortionGetter.getLotsPortion(lots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public LotsInfo getLotsBySearching(String searchLine, int page) throws ServiceException {
		try {
			ArrayList<Lot> lots = lotOperationDAO.getLotsBySearching(searchLine);
			return lotPortionGetter.getLotsPortion(lots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public LotsInfo getLotsByType(LotType lotType, int page) throws ServiceException {
		try {
			ArrayList<Lot> lots = lotOperationDAO.getLotsByType(lotType);
			return lotPortionGetter.getLotsPortion(lots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
}
