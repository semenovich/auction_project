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
		if (!LotInfoValidator.validate(lot)) {
			throw new LotInfoException(ERROR_MESSAGE);
		}
		try {
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
	public boolean deleteWaitingLot(Integer lotId) throws ServiceException {
		try {
			return lotOperationDAO.deleteWaitingLot(lotId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

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

	@Override
	public LotsInfo getLotsList(Locale locale, int page) throws ServiceException {
		try {
			ArrayList<Lot> lots = lotOperationDAO.getLotsList(locale);
			return lotPortionGetter.getLotsPortion(lots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public LotsInfo getLotsBySearching(String searchLine, Locale locale, int page) throws ServiceException {
		try {
			ArrayList<Lot> lots = lotOperationDAO.getLotsBySearching(searchLine, locale);
			return lotPortionGetter.getLotsPortion(lots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public LotsInfo getLotsByType(LotType lotType, Locale locale, int page) throws ServiceException {
		try {
			ArrayList<Lot> lots = lotOperationDAO.getLotsByType(lotType, locale);
			return lotPortionGetter.getLotsPortion(lots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public LotsInfo getWaitingLots(Locale locale, int page) throws ServiceException {
		try {
			ArrayList<Lot> lots = lotOperationDAO.getWaitingLots(locale);
			return lotPortionGetter.getLotsPortion(lots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean uploadLotImage(Integer lotId, String imagePath) throws ServiceException {
		try {
			return lotOperationDAO.uploadLotImage(lotId, imagePath);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
}
