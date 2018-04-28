package by.tc.auction.dao.admin_operation;

import by.tc.auction.dao.exception.DAOException;

/**
 * An interface of administrator operations. An interface provides methods for an administrator working in an application source.
 * @author semenovich
 *
 */
public interface AdminOperationDAO {
	
	/**
	 * Blocks a user by a login. 
	 * @param userLogin - a login of a user which will be blocked.
	 * @return {@code true} - if a user has been blocked. {@code false} - if a user hasn't been blocked.
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean blockUser(String userLogin) throws DAOException;
	
	/**
	 * Unblocks a user by a login. 
	 * @param userLogin - a login of a user which will be unblocked.
	 * @return {@code true} - if a user has been unblocked. {@code false} - if a user hasn't been unblocked.
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean unblockUser(String userLogin) throws DAOException;
	
	/**
	 * Blocks a lot by an ID. 
	 * @param lotId - an ID of a lot which will be blocked.
	 * @return {@code true} - if a lot has been blocked. {@code false} - if a lot hasn't been blocked.
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean blockLot(Integer lotId) throws DAOException;
	
	/**
	 * Unblocks a lot by an ID. 
	 * @param lotId - an ID of a lot which will be unblocked.
	 * @return {@code true} - if a lot has been unblocked. {@code false} - if a lot hasn't been unblocked.
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean unblockLot(Integer lotId) throws DAOException;
}
