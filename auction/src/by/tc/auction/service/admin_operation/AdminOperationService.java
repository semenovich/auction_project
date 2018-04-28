package by.tc.auction.service.admin_operation;

import by.tc.auction.service.exception.ServiceException;

/**
 * An interface of administrator operations. An interface provides methods for an administrator working in an application logic and in a source.
 * @author semenovich
 *
 */
public interface AdminOperationService {
	
	/**
	 * Blocks a user by a login.
	 * In the method the same DAO method should be used.
	 * @param userLogin - a user login.
	 * @return {@code true} - if a user has been blocked. {@code false} - if a user hasn't been blocked.
	 * @throws ServiceException - if an error occurred during operation. 
	 */
	boolean blockUser(String userLogin) throws ServiceException;
	
	/**
	 * Unblocks a user by a login.
	 * In the method the same DAO method should be used.
	 * @param userLogin - a user login.
	 * @return {@code true} - if a user has been unblocked. {@code false} - if a user hasn't been unblocked.
	 * @throws ServiceException - if an error occurred during operation. 
	 */
	boolean unblockUser(String userLogin) throws ServiceException;
	
	/**
	 * Blocks a lot by an ID.
	 * In the method the same DAO method should be used.
	 * @param lotId - a lot ID.
	 * @return {@code true} - if a lot has been blocked. {@code false} - if a lot hasn't been blocked.
	 * @throws ServiceException - if an error occurred during operation. 
	 */
	boolean blockLot(Integer lotId) throws ServiceException;
	
	/**
	 * Unblocks a lot by an ID.
	 * In the method the same DAO method should be used.
	 * @param lotId - a lot ID.
	 * @return {@code true} - if a lot has been unblocked. {@code false} - if a lot hasn't been unblocked.
	 * @throws ServiceException - if an error occurred during operation. 
	 */
	boolean unblockLot(Integer lotId) throws ServiceException;
}
