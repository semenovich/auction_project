package by.bsu.auction.service.lot_operation.realization;

import java.util.ArrayList;

import by.bsu.auction.dao.DAOFactory;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.lot_operation.LotOperationDAO;
import by.bsu.auction.entity.Lot;
import by.bsu.auction.entity.LotsInfo;
import by.bsu.auction.service.exception.LotInfoException;
import by.bsu.auction.service.exception.ServiceException;
import by.bsu.auction.service.lot_operation.LotOperationService;
import by.bsu.auction.service.lot_operation.realization.validation.Validator;

public class LotOperationServiceImpl implements LotOperationService {


	private static final int LOT_PORTION_QUANTITY = 10;
	private static final String ERROR_MESSAGE = "Invalid lot info";
	
	LotOperationDAO lotOperationDAO;
	
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
			return getLotsPortion(lots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public LotsInfo getLotsBySearching(String searchLine, int page) throws ServiceException {
		try {
			ArrayList<Lot> lots = lotOperationDAO.getLotsBySearching(searchLine);
			return getLotsPortion(lots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
	
	private LotsInfo getLotsPortion(ArrayList<Lot> userLots, int page){
		if (userLots == null) {
			return null;
		}
		ArrayList<Lot> returnLots = new ArrayList<>();
		LotsInfo lotsInfo = new LotsInfo();
		for (int i = (page - 1) * LOT_PORTION_QUANTITY; i < userLots.size() && i < page * LOT_PORTION_QUANTITY; i++) {
			returnLots.add(userLots.get(i));
		}
		lotsInfo.setLots(returnLots);
		lotsInfo.setCurrentPage(page);
		lotsInfo.setPages((int) Math.ceil(((double) userLots.size()) / LOT_PORTION_QUANTITY));
		return lotsInfo;
	}
}
