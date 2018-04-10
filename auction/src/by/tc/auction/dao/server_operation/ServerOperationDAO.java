package by.tc.auction.dao.server_operation;

import java.util.ArrayList;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.Auction;

public interface ServerOperationDAO {
	ArrayList<Auction> getActiveAuctions() throws DAOException;
	boolean completeAuctions(ArrayList<Auction> auctions) throws DAOException;
}
