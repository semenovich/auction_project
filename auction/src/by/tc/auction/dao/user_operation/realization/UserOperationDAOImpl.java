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

/**
 * A class is used to provide methods for users operations in a database.
 * @author semenovich
 *
 */
public class UserOperationDAOImpl implements UserOperationDAO {

	private final PaymentProcessor paymentProcessor = new PaymentProcessor();
	private final PaymentChecker paymentChecker = new PaymentChecker();
	
	private static final Logger logger = Logger.getLogger(UserOperationDAOImpl.class);

	/**
	 * Default constructor.
	 */
	public UserOperationDAOImpl() {}
	
	/**
	 * Places bet in a database.
	 * @param auction - an auction in which bet will be placed. Only an ID filed must be filled in.
	 * @param userLogin - a login of user which has placed bet.
	 * @param bet - a bet.
	 * @param betTime - time when bet was placed.
	 * @return {@code true} - if a bet has been placed. {@code false} - if a bet hasn't been placed.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public boolean placeBet(Auction auction, String userLogin, Bet bet, Timestamp betTime) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			connection.setAutoCommit(false);
			if (!paymentChecker.isParictipationExist(connection, auction, userLogin)) {
				paymentProcessor.createParticipation(connection, auction, userLogin);
			}
			if (paymentProcessor.placeBet(connection, auction, userLogin, bet, betTime)) {
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

	/**
	 * Pays for lot in a database.
	 * @param auctionId - an ID of an auction for which lot a user pays.
	 * @param lotId - an ID of lot for which a user pays.
	 * @return {@code true} - if successful. {@code false} - if not successful.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public boolean payForLot(Integer auctionId, Integer lotId) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return paymentProcessor.payForLot(connection, auctionId, lotId);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in UserOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns an auction current bet in from a database.
	 * @param auction - an auction. Only ID filed must be filled in.
	 * @return An auction current bet. 
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public Bet getAuctionCurrentBet(Auction auction) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return paymentChecker.getAuctionCurrentBet(connection, auction.getId());
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in UserOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
}
