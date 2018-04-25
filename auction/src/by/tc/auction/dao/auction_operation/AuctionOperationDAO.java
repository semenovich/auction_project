package by.tc.auction.dao.auction_operation;

import java.util.ArrayList;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;

/**
 * An interface of operations with auctions. An interface which provides methods for working with auctions.
 * @author semenovich
 *
 */
public interface AuctionOperationDAO {
	
	/**
	 * Creates an auction with a lot.
	 * @param auction - an auction which will be created.
	 * @param lot - a lot which will be created and used in auction.
	 * @return {@code true} - if an auction and a lot have been created. {@code false} - if an auction and a lot haven't been created. 
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean createAuctionWithLot(Auction auction, Lot lot) throws DAOException;
	
	/**
	 * Creates an auction with an existing lot.
	 * @param auction - an auction which will be created.
	 * @param lot - an existing lot.
	 * @return {@code true} - if an auction has been created. {@code false} - if an auction hasn't been created. 
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean createAuctionWithExistingLot(Auction auction, Integer lotId) throws DAOException;
	
	/**
	 * Returns an info of an auction.
	 * @param auctionId - an ID of an auction.
	 * @return Auction - if an auction is exist. {@code null} - if an auction doesn't exist.
	 * @throws DAOException - if an error occurred during operation.
	 */
	Auction getAuctionInfo(Integer auctionId) throws DAOException;
	
	/**
	 * Returns a list of auctions. 
	 * @param locale - a locale of auctions.
	 * @return Auction list of auctions if auctions exist. Empty list if auctions don't exist.
	 * @throws DAOException - if an error occurred during operation.
	 */
	ArrayList<Auction> getAuctions(Locale locale) throws DAOException;
	
	/**
	 * Returns a list of auctions by matching a name of a lot.
	 * @param searchLine - a search line which will be matched with a lot name.
	 * @param locale - a locale of auctions.
	 * @return Auction list of auctions if such auctions exist. Empty list if such auctions don't exist.
	 * @throws DAOException - if an error occurred during operation.
	 */
	ArrayList<Auction> getAuctionsBySearching(String searchLine, Locale locale) throws DAOException;
	
	/**
	 * Returns a list of auctions by searching by the type of a lot.
	 * @param lotType - a type of lot.
	 * @param locale - a locale of auctions.
	 * @return Auction list of auctions if such auctions exist. Empty list if such auctions don't exist.
	 * @throws DAOException - if an error occurred during operation.
	 */
	ArrayList<Auction> getAuctionsByLotType(LotType lotType, Locale locale) throws DAOException;
}
