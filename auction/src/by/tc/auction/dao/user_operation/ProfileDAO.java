package by.tc.auction.dao.user_operation;

import java.util.ArrayList;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.User;

/**
 * An interface of operations on users profiles. An interface provides methods for operations on users profiles in an application source.
 * @author semenovich
 *
 */
public interface ProfileDAO {
	
	/**
	 * Returns user profile info.
	 * @param login - a user login.
	 * @return A user if a user exists. {@code null} if a user doesn't exist.
	 * @throws DAOException - if an error occurred during operation.
	 */
	User getUserInfo(String login) throws DAOException;
	
	/**
	 * Returns a list of user lots.
	 * @param login - a user login.
	 * @return A list of user lots. Empty list if such lots don't exist. {@code null} if a user doesn't exist.
	 * @throws DAOException - if an error occurred during operation.
	 */
	ArrayList<Lot> getUserLots(String login) throws DAOException;
	
	/**
	 * Returns a list of user win lots.
	 * @param login - a user login.
	 * @return A list of user win lots. Empty list if such lots don't exist. {@code null} if a user doesn't exist. 
	 * @throws DAOException - if an error occurred during operation.
	 */
	ArrayList<Lot> getUserWinLots(String login) throws DAOException;
	
	/**
	 * Returns a list of user auction participations.
	 * @param login - a user login.
	 * @return A list of user auction participations. Empty list if such auctions don't exist. {@code null} if a user doesn't exist. 
	 * @throws DAOException - if an error occurred during operation.
	 */
	ArrayList<Auction> getUserAuctionParticipation(String login) throws DAOException;
	
	/**
	 * Edits user profile info.
	 * @param user - user update info.
	 * @return {@code true} if user profile info has been edited. {@code false} if user profile info hasn't been edited.
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean editUserInfo (User user) throws DAOException;
	
	/**
	 * Uploads a user profile image.
	 * @param userLogin - a user login.
	 * @param imagePath - an image path.
	 * @return {@code true} if a user profile image has been uploaded. {@code false} if a user profile image hasn't been uploaded.
	 * @throws DAOException - if an error occurred during operation.
	 */
	boolean uploadUserImage(String userLogin, String imagePath) throws DAOException;
}
