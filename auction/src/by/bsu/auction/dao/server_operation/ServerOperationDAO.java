package by.bsu.auction.dao.server_operation;

import java.util.ArrayList;

import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.entity.Auction;

public interface ServerOperationDAO {
	ArrayList<Auction> getActiveAuctions() throws DAOException;
	boolean completeAuctions(ArrayList<Auction> auctions) throws DAOException;
}
