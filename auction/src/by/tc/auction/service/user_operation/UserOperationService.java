package by.tc.auction.service.user_operation;

import java.sql.Date;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Bet;
import by.tc.auction.service.exception.BetException;
import by.tc.auction.service.exception.ServiceException;

public interface UserOperationService {
	boolean placeBet(Auction auction, String userLogin, Bet bet, Date betTime) throws ServiceException, BetException;
	boolean payForLot(Integer auctionId, Integer lotId) throws ServiceException;
}
