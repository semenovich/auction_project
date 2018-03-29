package by.bsu.auction.dao.lot_operation;

import java.util.ArrayList;

import by.bsu.auction.dao.exception.DAOException;
import by.tc.auction.entity.Lot;

public interface LotOperationDAO {
	boolean offerLot(Lot lot) throws DAOException;
	Lot getLotInfo(Integer lotId) throws DAOException;
	boolean deleteConfirmingLot(Integer lotId) throws DAOException;
	boolean editConfirmingLot(Lot lot) throws DAOException;
	ArrayList<Lot> getLotsList() throws DAOException;
	ArrayList<Lot> getLotsBySearching(String searchLine) throws DAOException;
}
