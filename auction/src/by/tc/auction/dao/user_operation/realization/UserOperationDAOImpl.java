package by.tc.auction.dao.user_operation.realization;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.tc.auction.dao.connection_pool.ConnectionPool;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.exception.ConnectionPoolException;
import by.tc.auction.dao.user_operation.UserOperationDAO;
import by.tc.auction.dao.user_operation.realization.util.PaymentChecker;
import by.tc.auction.dao.user_operation.realization.util.PaymentProcessor;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Bet;

public class UserOperationDAOImpl implements UserOperationDAO {

	private final PaymentProcessor paymentProcessor = new PaymentProcessor();
	private final PaymentChecker paymentChecker = new PaymentChecker();
	
	private static final Logger logger = Logger.getLogger(UserOperationDAOImpl.class);

	
	@Override
	public boolean placeBet(Auction auction, String userLogin, Bet bet, Timestamp betTime) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			connection.setAutoCommit(false);
			if (paymentProcessor.placeBet(connection, auction, userLogin, bet, betTime)) {
				if (!paymentChecker.isParictipationExist(connection, auction, userLogin)) {
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
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in UserOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean payForLot(Integer auctionId, Integer lotId) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return paymentProcessor.payForLot(connection, auctionId, lotId);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in UserOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Double getAuctionCurrentBet(Auction auction) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return paymentChecker.getAuctionCurrentBet(connection, auction.getId());
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in UserOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

}
