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

public class ProfileDAOImpl implements ProfileDAO {

	private final UserInfoGetter userInfoGetter = new UserInfoGetter();
	private final UserProcessor userProcessor = new UserProcessor();
	private final UserChecker userChecker = new UserChecker();
	
	private static final Logger logger = Logger.getLogger(ProfileDAOImpl.class);

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
