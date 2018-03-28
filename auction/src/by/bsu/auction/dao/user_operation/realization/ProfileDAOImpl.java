package by.bsu.auction.dao.user_operation.realization;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.bsu.auction.dao.connection_pool.ConnectionPool;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.exception.DBConnectionException;
import by.bsu.auction.dao.user_operation.ProfileDAO;
import by.bsu.auction.dao.user_operation.realization.util.UserProcessor;
import by.bsu.auction.entity.Auction;
import by.bsu.auction.entity.Lot;
import by.bsu.auction.entity.User;

public class ProfileDAOImpl implements ProfileDAO {

	private UserProcessor userProcessor = new UserProcessor();
	private static final Logger logger = Logger.getLogger(ProfileDAOImpl.class);

	@Override
	public User getUserInfo(String login) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			User user = null;
			if (userProcessor.isUserExist(connection, login)) {
				user = userProcessor.getPersonalUserInfo(connection, login);
			}
			return user;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in ProfileDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ArrayList<Lot> getUserLots(String login) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			ArrayList<Lot> lots = null;
			if (userProcessor.isUserExist(connection, login)) {
				lots = userProcessor.getUserLots(connection, login);
			}
			return lots;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in ProfileDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
	
	@Override
	public ArrayList<Lot> getUserWinLots(String login) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			ArrayList<Lot> lots = null;
			if (userProcessor.isUserExist(connection, login)) {
				lots = userProcessor.getUserWinLots(connection, login);
			}
			return lots;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in ProfileDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ArrayList<Auction> getUserAuctionParticipation(String login) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			ArrayList<Auction> auctions = null;
			if (userProcessor.isUserExist(connection, login)) {
				auctions = userProcessor.getUserParticipations(connection, login);
			}
			return auctions;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in ProfileDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean editUserInfo(User user) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (userProcessor.isUserExist(connection, user.getLogin())) {
				return userProcessor.edit(connection, user);
			}
			return false;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in ProfileDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
}
