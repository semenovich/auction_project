package by.bsu.auction.dao.user_operation.realization;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.bsu.auction.dao.connection_pool.ConnectionPool;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.exception.DBConnectionException;
import by.bsu.auction.dao.user_operation.UserOperationDAO;
import by.bsu.auction.dao.user_operation.realization.util.PaymentProcessor;
import by.bsu.auction.entity.Auction;
import by.bsu.auction.entity.Bet;

public class UserOperationDAOImpl implements UserOperationDAO {

	private PaymentProcessor paymentProcessor = new PaymentProcessor();
	private static final Logger logger = Logger.getLogger(UserOperationDAOImpl.class);

	private static final String GET_AUCTION_CURRENT_BET = "SELECT a_current_price AS auctionCurrentBet FROM auction.auctions WHERE a_id=?";
	private static final String AUCTION_CURRENT_BET = "auctionCurrentBet";
	
	@Override
	public boolean placeBet(Auction auction, String userLogin, Bet bet, Date betTime) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			connection.setAutoCommit(false);
			if (paymentProcessor.placeBet(connection, auction, userLogin, bet, betTime)) {
				if (!paymentProcessor.isParictipationExist(connection, auction, userLogin)) {
					paymentProcessor.createParticipation(connection, auction, userLogin);
				}
				connection.commit();
				connection.setAutoCommit(true);
				return true;
			}
			else {
				connection.setAutoCommit(true);
				return false;
			}
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in UserOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean payForLot(Integer auctionId, Integer lotId) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return paymentProcessor.payForLot(connection, auctionId, lotId);
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in UserOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Double getAuctionCurrentBet(Auction auction) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(GET_AUCTION_CURRENT_BET);
			preparedStatement.setInt(1, auction.getId());
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				return result.getDouble(AUCTION_CURRENT_BET);
			}
			return 0D;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in UserOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

}
