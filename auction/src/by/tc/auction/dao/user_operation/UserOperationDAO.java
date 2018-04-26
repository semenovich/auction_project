package by.tc.auction.dao.user_operation;

import java.sql.Timestamp;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Bet;

/**
 * An interface of user operations. An interface provides methods of user operations in an application source.
 * @author semenovich
 *
 */
public interface UserOperationDAO {
	
	/**
	 * Places bet.
	 * @param auction - an auction in which bet will be placed.
	 * @param userLogin - a login of user which has placed bet.
	 * @param bet - a bet.
	 * @param betTime - time when bet was placed.
	 * @return {@code true} - if a bet has been placed. {@code false} - if a bet hasn't been placed.
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean placeBet(Auction auction, String userLogin, Bet bet, Timestamp betTime) throws DAOException;
	
	/**
	 * Pays for lot.
	 * @param auctionId - an ID of an auction for which lot a user pays.
	 * @param lotId - an ID of lot for which a user pays.
	 * @return {@code true} - if successful. {@code false} - if not successful.
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean payForLot(Integer auctionId, Integer lotId) throws DAOException;
	
	/**
	 * Returns an auction current bet.
	 * @param auction - an auction.
	 * @return An auction current bet. {@code null} if an auction doesn't exist.
	 * @throws DAOException - if an error occurred during operation.
	 */
	Bet getAuctionCurrentBet(Auction auction) throws DAOException;
}
