package by.tc.auction.service.user_operation;

import java.sql.Timestamp;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Bet;
import by.tc.auction.service.exception.BetException;
import by.tc.auction.service.exception.ServiceException;

/**
 * An interface of user operations. An interface provides methods of user operations on users profiles in an application logic and in a source.
 * @author semenovich
 *
 */
public interface UserOperationService {
	
	/**
	 * Places a bet.
	 * In the method the same DAO method and {@code Bet getAuctionCurrentBet()} methods should be used.
	 * @param auction - an auction in which bet will be placed.
	 * @param userLogin - a login of user which has placed bet.
	 * @param bet - a bet.
	 * @param betTime - time when bet was placed.
	 * @return {@code true} - if a bet has been placed. {@code false} - if a bet hasn't been placed.
	 * @throws ServiceException - if an error occurred during operation.
	 * @throws BetException - if a bet is incorrect.
	 */
	boolean placeBet(Auction auction, String userLogin, Bet bet, Timestamp betTime) throws ServiceException, BetException;
	
	/**
	 * Pays for a lot.
	 * In the method the same DAO method should be used.
	 * @param auctionId - an ID of an auction for which lot a user pays.
	 * @param lotId - an ID of lot for which a user pays.
	 * @return {@code true} - if successful. {@code false} - if not successful.
	 * @throws ServiceException - if an error occurred during operation.
	 */
	boolean payForLot(Integer auctionId, Integer lotId) throws ServiceException;
}
