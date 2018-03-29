package by.bsu.auction.dao.auction_operation;

import java.util.ArrayList;

import by.bsu.auction.dao.exception.DAOException;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Lot;

public interface AuctionOperationDAO {
	boolean createAuctionWithLot(Auction auction, Lot lot) throws DAOException;
	boolean createAuctionFromExistingLot(Auction auction, Integer lotId) throws DAOException;
	Auction getAuctionInfo(Integer auctionId) throws DAOException;
	ArrayList<Auction> getAuctions() throws DAOException;
	ArrayList<Auction> getAuctionsBySearching(String searchLine) throws DAOException;
}
