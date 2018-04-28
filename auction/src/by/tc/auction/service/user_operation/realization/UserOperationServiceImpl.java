package by.tc.auction.service.user_operation.realization;

import java.sql.Timestamp;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.user_operation.UserOperationDAO;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Bet;
import by.tc.auction.service.exception.BetException;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.user_operation.UserOperationService;
import by.tc.auction.service.user_operation.realization.validation.UserBetValidator;

/**
 * A class is used to provide methods for users operations on an application logic level and in a database.
 * @author semenovich
 *
 */
public class UserOperationServiceImpl implements UserOperationService {

	private static final String ERROR_MESSAGE = "Invalid bet";
	
	private static final double MIN_BET_DIFFERENCE = 5;
	
	private UserOperationDAO userOperationDAO; 
	
	/**
	 * Default constructor.
	 */
	public UserOperationServiceImpl() {
		DAOFactory factory = DAOFactory.getInstance();
		userOperationDAO = factory.getUserOperationDAO();
	}

	/**
	 * Places a bet in a database if a bet is correct.
	 * A bet must be greater than MIN bet (and than a current auction bet by 5$).
	 * @param auction - an auction in which bet will be placed.
	 * @param userLogin - a login of user which has placed bet.
	 * @param bet - a bet.
	 * @param betTime - time when bet was placed.
	 * @return {@code true} - if a bet has been placed. {@code false} - if a bet hasn't been placed.
	 * @throws ServiceException - if an error occurred during operation in a database.
	 * @throws BetException - if a bet is incorrect.
	 */
	@Override
	public boolean placeBet(Auction auction, String userLogin, Bet bet, Timestamp betTime) throws ServiceException, BetException {
		try {
			Bet auctionCurrentBet = userOperationDAO.getAuctionCurrentBet(auction);
			auction.setLastBet(auctionCurrentBet);
			if (!UserBetValidator.validate(auction, bet, MIN_BET_DIFFERENCE)) {
				throw new BetException(ERROR_MESSAGE);
			}
			return userOperationDAO.placeBet(auction, userLogin, bet, betTime);
		}  catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Pays for a lot in a database.
	 * @param auctionId - an ID of an auction for which lot a user pays.
	 * @param lotId - an ID of lot for which a user pays.
	 * @return {@code true} - if successful. {@code false} - if not successful.
	 * @throws ServiceException - if an error occurred during operation in a database.
	 */
	@Override
	public boolean payForLot(Integer auctionId, Integer lotId) throws ServiceException {
		try {
			return userOperationDAO.payForLot(auctionId, lotId);
		}  catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

}
