package by.tc.auction.service.lot_operation;

import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;
import by.tc.auction.entity.LotsInfo;
import by.tc.auction.service.exception.LotInfoException;
import by.tc.auction.service.exception.ServiceException;

public interface LotOperationService {
	boolean offerLot(Lot lot) throws ServiceException, LotInfoException;
	Lot getLotInfo(Integer lotId) throws ServiceException;
	boolean deleteConfirmingLot(Integer lotId) throws ServiceException;
	boolean editConfirmingLot(Lot lot) throws ServiceException, LotInfoException;
	LotsInfo getLotsList(Locale locale, int page) throws ServiceException;
	LotsInfo getLotsBySearching(String searchLine, Locale locale, int page) throws ServiceException;
	LotsInfo getLotsByType(LotType lotType, Locale locale, int page) throws ServiceException;
}
