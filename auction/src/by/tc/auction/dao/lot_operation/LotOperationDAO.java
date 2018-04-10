package by.tc.auction.dao.lot_operation;

import java.util.ArrayList;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;

public interface LotOperationDAO {
	boolean offerLot(Lot lot) throws DAOException;
	Lot getLotInfo(Integer lotId) throws DAOException;
	boolean deleteConfirmingLot(Integer lotId) throws DAOException;
	boolean editConfirmingLot(Lot lot) throws DAOException;
	ArrayList<Lot> getLotsList(Locale locale) throws DAOException;
	ArrayList<Lot> getLotsBySearching(String searchLine, Locale locale) throws DAOException;
	ArrayList<Lot> getLotsByType(LotType lotType, Locale locale) throws DAOException;
}
