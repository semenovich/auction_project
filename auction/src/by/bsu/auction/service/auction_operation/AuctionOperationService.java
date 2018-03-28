package by.bsu.auction.service.auction_operation;

import by.bsu.auction.entity.Auction;
import by.bsu.auction.entity.AuctionsInfo;
import by.bsu.auction.entity.Lot;
import by.bsu.auction.service.exception.LotInfoException;
import by.bsu.auction.service.exception.ServiceException;

public interface AuctionOperationService {
	boolean createAuctionWithLot(Auction auction, Lot lot) throws ServiceException, LotInfoException;
	boolean createAuctionFromExistingLot(Auction auction, Integer lotId) throws ServiceException;
	Auction getAuctionInfo(Integer auctionId) throws ServiceException;
	AuctionsInfo getAuctions(int page) throws ServiceException;
	AuctionsInfo getAuctionsBySearching(String searchLine, int page) throws ServiceException;
}
