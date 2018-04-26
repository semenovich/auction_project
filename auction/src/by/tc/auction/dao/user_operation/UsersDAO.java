package by.tc.auction.dao.user_operation;

import java.util.ArrayList;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.User;

/**
 * An interface of operations on users. An interface provides methods of operations on users in an application source.
 * @author semenovich
 *
 */
public interface UsersDAO {
	
	/**
	 * Returns a list of all users.
	 * @return A list of all users. Empty list if users don't exist.
	 * @throws DAOException - if an error occurred during operation.
	 */
	ArrayList<User> getUsers() throws DAOException;
	
	/**
	 * Returns a list of users by matching a login of users.
	 * @param searchLine - a search line which will be matched with a user name.
	 * @return A list of such users. Empty list if such users don't exist.
	 * @throws DAOException - if an error occurred during operation.
	 */
	ArrayList<User> getUsersBySearching(String searchLine) throws DAOException;
}
