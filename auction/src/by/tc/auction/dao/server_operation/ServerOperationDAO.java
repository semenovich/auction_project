package by.tc.auction.dao.server_operation;

import java.util.ArrayList;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.Auction;

/**
 * An interface of server operations. An interface which provides methods for server working in an application source.
 * @author semenovich
 *
 */
public interface ServerOperationDAO {
	
	/**
	 * Returns a list of active auctions. 
	 * @return A list of active auctions. Empty list if active auctions doesn't exist.
	 * @throws DAOException - if an error occurred during operation.
	 */
	ArrayList<Auction> getActiveAuctions() throws DAOException;
	
	/**
	 * Completes auctions.
	 * @param auctions - a list of auctions which will be completed.
	 * @return {@code true} if auctions have been completed. {@code false} if auctions haven't been completed.
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean completeAuctions(ArrayList<Auction> auctions) throws DAOException;
}
