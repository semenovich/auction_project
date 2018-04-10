package by.tc.auction.dao.user_operation;

import java.sql.Date;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Bet;

public interface UserOperationDAO {
	boolean placeBet(Auction auction, String userLogin, Bet bet, Date betTime) throws DAOException;
	boolean payForLot(Integer auctionId, Integer lotId) throws DAOException;
	Double getAuctionCurrentBet(Auction auction) throws DAOException;
}
