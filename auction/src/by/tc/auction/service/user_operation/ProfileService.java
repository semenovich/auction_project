package by.tc.auction.service.user_operation;

import by.tc.auction.entity.AuctionsInfo;
import by.tc.auction.entity.LotsInfo;
import by.tc.auction.entity.User;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.exception.UserInfoException;

/**
 * An interface of operations on users profiles. An interface provides methods for operations on users profiles in an application logic and in a source.
 * @author semenovich
 *
 */
public interface ProfileService {
	
	/**
	 * Returns user profile info.
	 * In the method the same DAO method should be used.
	 * @param login - a user login.
	 * @return A user if a user exists. {@code null} if a user doesn't exist.
	 * @throws ServiceException - if an error occurred during operation.
	 */
	User getUserInfo(String login) throws ServiceException;
	
	/**
	 * Returns a list of user lots portion.
	 * In the method the same DAO method should be used.
	 * @param login - a user login.
	 * @param page - a page of a lots list.
	 * @return A list of user lots portion. Empty list if such lots don't exist. {@code null} if a user doesn't exist.
	 * @throws ServiceException - if an error occurred during operation.
	 */
	LotsInfo getUserLots(String login, int page) throws ServiceException;
	
	/**
	 * Returns a list of user win lots portion.
	 * In the method the same DAO method should be used.
	 * @param login - a user login.
	 * @param page - a page of a lots list.
	 * @return A list of user win lots portion. Empty list if such lots don't exist. {@code null} if a user doesn't exist. 
	 * @throws ServiceException - if an error occurred during operation.
	 */
	LotsInfo getUserWinLots(String login, int page) throws ServiceException;
	
	/**
	 * Returns a list of user auction participations portion.
	 * In the method the same DAO method should be used.
	 * @param login - a user login.
	 * @param page - a page of an auctions list.
	 * @return A list of user auction participations. Empty list if such auctions don't exist. {@code null} if a user doesn't exist. 
	 * @throws ServiceException - if an error occurred during operation.
	 */
	AuctionsInfo getUserAuctionParticipations(String login, int page) throws ServiceException;
	
	/**
	 * Edits user profile info.
	 * In the method the same DAO method should be used.
	 * @param user - user updated info.
	 * @return {@code true} if user profile info has been edited. {@code false} if user profile info hasn't been edited.
	 * @throws ServiceException - if an error occurred during operation.
	 * @throws UserInfoException - if user info is incorrect.
	 */
	boolean editUserInfo (User user) throws ServiceException, UserInfoException;
	
	/**
	 * Uploads a user profile image.
	 * In the method the same DAO method should be used.
	 * @param userLogin - a user login.
	 * @param imagePath - an image path.
	 * @return {@code true} if a user profile image has been uploaded. {@code false} if a user profile image hasn't been uploaded.
	 * @throws ServiceException - if an error occurred during operation.
	 */
	boolean uploadUserImage (String userLogin, String imagePath) throws ServiceException;
}
