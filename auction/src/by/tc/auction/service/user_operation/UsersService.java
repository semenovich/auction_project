package by.tc.auction.service.user_operation;

import by.tc.auction.entity.UsersInfo;
import by.tc.auction.service.exception.ServiceException;

/**
 * An interface of operations on users. An interface provides methods of operations on users in an application logic and in a source.
 * @author semenovich
 *
 */
public interface UsersService {
	
	/**
	 * Returns a list of all users portion.
	 * In the method the same DAO method should be used.
	 * @param page - a page of a users list.
	 * @return A list of all users portion. Empty list if users don't exist.
	 * @throws ServiceException - if an error occurred during operation.
	 */
	UsersInfo getUsers(int page) throws ServiceException;
	
	/**
	 * Returns a list of users portion by matching a login of users.
	 * In the method the same DAO method should be used.
	 * @param searchLine - a search line which will be matched with a user name.
	 * @param page - a page of a users portion list.
	 * @return A list of such users portion. Empty list if such users don't exist.
	 * @throws ServiceException - if an error occurred during operation.
	 */
	UsersInfo getUsersBySearching(String searchLine, int page) throws ServiceException;
}
