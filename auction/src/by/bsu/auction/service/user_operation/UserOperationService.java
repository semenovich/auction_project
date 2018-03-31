package by.bsu.auction.service.user_operation;

import java.sql.Date;

import by.bsu.auction.service.exception.BetException;
import by.bsu.auction.service.exception.ServiceException;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Bet;

public interface UserOperationService {
	boolean placeBet(Auction auction, String userLogin, Bet bet, Date betTime) throws ServiceException, BetException;
	boolean payForLot(Integer auctionId, Integer lotId) throws ServiceException;
}
