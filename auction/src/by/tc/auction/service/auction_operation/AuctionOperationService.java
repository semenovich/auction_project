package by.tc.auction.service.auction_operation;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionsInfo;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;
import by.tc.auction.service.exception.LotInfoException;
import by.tc.auction.service.exception.ServiceException;

/**
 * An interface of operations with auctions. An interface which provides methods for working with auctions in an application logic and in a source.
 * @author semenovich
 *
 */
public interface AuctionOperationService {
	
	/**
	 * Creates an auction with a lot.
	 * In the method the same DAO method should be used.
	 * @param auction - an auction which will be created.
	 * @param lot - a lot which will be created and used in auction.
	 * @return {@code true} - if an auction and a lot have been created. {@code false} - if an auction and a lot haven't been created. 
	 * @throws ServiceException - if an error occurred during operation. 
	 * @throws LotInfoException - if a lot has incorrect info.
	 */
	boolean createAuctionWithLot(Auction auction, Lot lot) throws ServiceException, LotInfoException;
	
	/**
	 * Creates an auction with an existing lot.
	 * In the method the same DAO method should be used.
	 * @param auction - an auction which will be created.
	 * @param lotId - an ID of an existing lot.
	 * @return {@code true} - if an auction has been created. {@code false} - if an auction hasn't been created. 
	 * @throws ServiceException - if an error occurred during operation. 
	 */
	boolean createAuctionWithExistingLot(Auction auction, Integer lotId) throws ServiceException;
	
	/**
	 * Returns info of an auction.
	 * In the method the same DAO method should be used.
	 * @param auctionId - an ID of an auction.
	 * @return Auction - if an auction is exist. {@code null} - if an auction doesn't exist.
	 * @throws ServiceException - if an error occurred during operation. 
	 */
	Auction getAuctionInfo(Integer auctionId) throws ServiceException;
	
	/**
	 * Returns a list of auctions portion. 
	 * In the method the same DAO method should be used.
	 * @param locale - a locale of auctions.
	 * @param page - a page of an auctions list.
	 * @return Auctions portion list of auctions if auctions exist. Empty list if auctions don't exist.
	 * @throws ServiceException - if an error occurred during operation. 
	 */
	AuctionsInfo getAuctions(Locale locale, int page) throws ServiceException;
	
	/**
	 * Returns a list of auctions portion by matching a name of a lot.
	 * In the method the same DAO method should be used.
	 * @param searchLine - a search line which will be matched with a lot name.
	 * @param locale - a locale of auctions.
	 * @param page - a page of an auctions list.
	 * @return Auctions portion list of auctions if such auctions exist. Empty list if such auctions don't exist.
	 * @throws ServiceException - if an error occurred during operation. 
	 */
	AuctionsInfo getAuctionsBySearching(String searchLine, Locale locale, int page) throws ServiceException;
	
	/**
	 * Returns a list of auctions portion by searching by the type of a lot.
	 * @param lotType - a type of lot.
	 * @param locale - a locale of auctions.
	 * @param page - a page of an auctions list.
	 * @return Auctions portion list of auctions if such auctions exist. Empty list if such auctions don't exist.
	 * @throws ServiceException - if an error occurred during operation.
	 */
	AuctionsInfo getAuctionsByLotType(LotType lotType, Locale locale, int page) throws ServiceException;
}
