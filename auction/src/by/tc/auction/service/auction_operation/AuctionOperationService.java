package by.tc.auction.service.auction_operation;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionsInfo;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;
import by.tc.auction.service.exception.LotInfoException;
import by.tc.auction.service.exception.ServiceException;

public interface AuctionOperationService {
	boolean createAuctionWithLot(Auction auction, Lot lot) throws ServiceException, LotInfoException;
	boolean createAuctionFromExistingLot(Auction auction, Integer lotId) throws ServiceException;
	Auction getAuctionInfo(Integer auctionId) throws ServiceException;
	AuctionsInfo getAuctions(Locale locale, int page) throws ServiceException;
	AuctionsInfo getAuctionsBySearching(String searchLine, Locale locale, int page) throws ServiceException;
	AuctionsInfo getAuctionsByLotType(LotType lotType, Locale locale, int page) throws ServiceException;
}
