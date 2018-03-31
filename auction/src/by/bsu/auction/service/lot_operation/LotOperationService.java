package by.bsu.auction.service.lot_operation;

import by.bsu.auction.service.exception.LotInfoException;
import by.bsu.auction.service.exception.ServiceException;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;
import by.tc.auction.entity.LotsInfo;

public interface LotOperationService {
	boolean offerLot(Lot lot) throws ServiceException, LotInfoException;
	Lot getLotInfo(Integer lotId) throws ServiceException;
	boolean deleteConfirmingLot(Integer lotId) throws ServiceException;
	boolean editConfirmingLot(Lot lot) throws ServiceException, LotInfoException;
	LotsInfo getLotsList(int page) throws ServiceException;
	LotsInfo getLotsBySearching(String searchLine, int page) throws ServiceException;
	LotsInfo getLotsByType(LotType lotType, int page) throws ServiceException;
}
