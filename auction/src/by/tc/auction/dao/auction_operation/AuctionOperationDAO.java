package by.tc.auction.dao.auction_operation;

import java.util.ArrayList;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;

public interface AuctionOperationDAO {
	boolean createAuctionWithLot(Auction auction, Lot lot) throws DAOException;
	boolean createAuctionFromExistingLot(Auction auction, Integer lotId) throws DAOException;
	Auction getAuctionInfo(Integer auctionId) throws DAOException;
	ArrayList<Auction> getAuctions(Locale locale) throws DAOException;
	ArrayList<Auction> getAuctionsBySearching(String searchLine, Locale locale) throws DAOException;
	ArrayList<Auction> getAuctionsByLotType(LotType lotType, Locale locale) throws DAOException;
}
