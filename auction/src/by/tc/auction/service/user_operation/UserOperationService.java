package by.tc.auction.service.user_operation;

import java.sql.Timestamp;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Bet;
import by.tc.auction.service.exception.BetException;
import by.tc.auction.service.exception.ServiceException;

public interface UserOperationService {
	boolean placeBet(Auction auction, String userLogin, Bet bet, Timestamp betTime) throws ServiceException, BetException;
	boolean payForLot(Integer auctionId, Integer lotId) throws ServiceException;
}
