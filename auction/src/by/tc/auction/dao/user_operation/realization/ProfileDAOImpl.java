package by.tc.auction.dao.user_operation.realization;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.tc.auction.dao.connection_pool.ConnectionPool;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.exception.ConnectionPoolException;
import by.tc.auction.dao.user_operation.ProfileDAO;
import by.tc.auction.dao.user_operation.realization.util.UserChecker;
import by.tc.auction.dao.user_operation.realization.util.UserInfoGetter;
import by.tc.auction.dao.user_operation.realization.util.UserProcessor;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.User;


/**
 * A class is used to provide methods for working with users profiles in a database.
 * @author semenovich
 *
 */
public class ProfileDAOImpl implements ProfileDAO {

	private final UserInfoGetter userInfoGetter = new UserInfoGetter();
	private final UserProcessor userProcessor = new UserProcessor();
	private final UserChecker userChecker = new UserChecker();
	
	private static final Logger logger = Logger.getLogger(ProfileDAOImpl.class);

	/**
	 * Default constructor.
	 */
	public ProfileDAOImpl() {}
	
	/**
	 * Returns user profile info from a database.
	 * @param login - a user login.
	 * @return A user if a user exists in a database. {@code null} if a user doesn't exist in a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public User getUserInfo(String login) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			User user = null;
			if (userChecker.isUserExist(connection, login)) {
				user = userInfoGetter.getPersonalUserInfo(connection, login);
			}
			return user;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in ProfileDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a list of user lots from a database.
	 * @param login - a user login.
	 * @return A list of user lots in a database. Empty list if such lots don't exist in a database. {@code null} if a user doesn't exist in a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public ArrayList<Lot> getUserLots(String login) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			ArrayList<Lot> lots = null;
			if (userChecker.isUserExist(connection, login)) {
				lots = userInfoGetter.getUserLots(connection, login);
			}
			return lots;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in ProfileDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
	
	/**
	 * Returns a list of user win lots from a database.
	 * @param login - a user login.
	 * @return A list of user win lots in a database. Empty list if such lots don't exist in a database. {@code null} if a user doesn't exist in a database. 
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public ArrayList<Lot> getUserWinLots(String login) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			ArrayList<Lot> lots = null;
			if (userChecker.isUserExist(connection, login)) {
				lots = userInfoGetter.getUserWinLots(connection, login);
			}
			return lots;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in ProfileDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a list of user auction participations from a database.
	 * @param login - a user login.
	 * @return A list of user auction participations in a database. Empty list if such auctions don't exist in a database. {@code null} if a user doesn't exist in a database. 
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public ArrayList<Auction> getUserAuctionParticipation(String login) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			ArrayList<Auction> auctions = null;
			if (userChecker.isUserExist(connection, login)) {
				auctions = userInfoGetter.getUserParticipations(connection, login);
			}
			return auctions;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in ProfileDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Edits user profile info in a database.
	 * @param user - user update info. Only surname, name, phone, email, passport ID, passport issued by, login fields must be filled in.
	 * @return {@code true} if user profile info has been edited in a database. {@code false} if user profile info doesn't exist and hasn't been edited in a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public boolean editUserInfo(User user) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (userChecker.isUserExist(connection, user.getLogin())) {
				return userProcessor.edit(connection, user);
			}
			return false;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in ProfileDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Uploads a user profile image in a database.
	 * @param userLogin - a user login.
	 * @param imagePath - an image path.
	 * @return {@code true} if a user profile image has been uploaded in a database. {@code false} if a user profile image hasn't been uploaded in a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public boolean uploadUserImage(String userLogin, String imagePath) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (userChecker.isUserExist(connection, userLogin)) {
				return userProcessor.uploadUserImage(connection, userLogin, imagePath);
			}
			else {
				return false;
			}
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in ProfileDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
}
