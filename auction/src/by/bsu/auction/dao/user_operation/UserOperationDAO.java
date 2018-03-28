package by.bsu.auction.dao.user_operation;

import java.sql.Date;

import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.entity.Auction;
import by.bsu.auction.entity.Bet;

public interface UserOperationDAO {
	boolean placeBet(Auction auction, String userLogin, Bet bet, Date betTime) throws DAOException;
	boolean payForLot(Integer auctionId, Integer lotId) throws DAOException;
	Double getAuctionCurrentBet(Auction auction) throws DAOException;
}
