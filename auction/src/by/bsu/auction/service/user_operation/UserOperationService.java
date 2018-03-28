package by.bsu.auction.service.user_operation;

import java.sql.Date;

import by.bsu.auction.entity.Auction;
import by.bsu.auction.entity.Bet;
import by.bsu.auction.service.exception.BetException;
import by.bsu.auction.service.exception.ServiceException;

public interface UserOperationService {
	boolean placeBet(Auction auction, String userLogin, Bet bet, Date betTime) throws ServiceException, BetException;
	boolean payForLot(Integer auctionId, Integer lotId) throws ServiceException;
}
