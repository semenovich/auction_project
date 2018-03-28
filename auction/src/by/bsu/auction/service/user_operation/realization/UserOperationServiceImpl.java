package by.bsu.auction.service.user_operation.realization;

import java.sql.Date;

import by.bsu.auction.dao.DAOFactory;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.user_operation.UserOperationDAO;
import by.bsu.auction.entity.Auction;
import by.bsu.auction.entity.Bet;
import by.bsu.auction.service.exception.BetException;
import by.bsu.auction.service.exception.ServiceException;
import by.bsu.auction.service.user_operation.UserOperationService;
import by.bsu.auction.service.user_operation.realization.validation.Validator;

public class UserOperationServiceImpl implements UserOperationService {

	private static final String ERROR_MESSAGE = "Invalid bet";
	
	private UserOperationDAO userOperationDAO; 
	
	public UserOperationServiceImpl() {
		DAOFactory factory = DAOFactory.getInstance();
		userOperationDAO = factory.getUserOperationDAO();
	}

	@Override
	public boolean placeBet(Auction auction, String userLogin, Bet bet, Date betTime) throws ServiceException, BetException {
		try {
			Bet auctionCurrentBet = new Bet();
			auctionCurrentBet.setValue(userOperationDAO.getAuctionCurrentBet(auction));
			auction.setCurrentBet(auctionCurrentBet);
			if (!Validator.validateUserBet(auction, auctionCurrentBet)) {
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
