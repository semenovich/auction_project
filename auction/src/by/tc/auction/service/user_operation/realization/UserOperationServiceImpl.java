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

public class UserOperationServiceImpl implements UserOperationService {

	private static final String ERROR_MESSAGE = "Invalid bet";
	
	private static final double MIN_BET_DIFFERENCE = 5;
	
	private UserOperationDAO userOperationDAO; 
	
	public UserOperationServiceImpl() {
		DAOFactory factory = DAOFactory.getInstance();
		userOperationDAO = factory.getUserOperationDAO();
	}

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

	@Override
	public boolean payForLot(Integer auctionId, Integer lotId) throws ServiceException {
		try {
			return userOperationDAO.payForLot(auctionId, lotId);
		}  catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

}
