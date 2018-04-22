package dao;

import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.auction_operation.AuctionOperationDAO;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.lot_operation.LotOperationDAO;
import by.tc.auction.dao.user_operation.UserOperationDAO;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionStatus;
import by.tc.auction.entity.Bet;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotStatus;

public class UserOperationTest {

	@Test
	public void testPlaceBet() {
		UserOperationDAO userOperationDAO = DAOFactory.getInstance().getUserOperationDAO();
		AuctionOperationDAO auctionOperationDAO = DAOFactory.getInstance().getAuctionOperationDAO();
		
		Auction auction = new Auction();
		auction.setId(1);
		String userLogin = "Admin";
		Bet bet = new Bet();
		bet.setValue(new Double(17.0));
		Timestamp betTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
		
		try {
			userOperationDAO.placeBet(auction, userLogin, bet, betTime);
			auction = auctionOperationDAO.getAuctionInfo(auction.getId());
			
			
			Assert.assertEquals(true, auction.getLastBet().equals(bet) && 
					auction.getLastBetUser().equals(userLogin));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testPayForLot() {
		UserOperationDAO userOperationDAO = DAOFactory.getInstance().getUserOperationDAO();
		AuctionOperationDAO auctionOperationDAO = DAOFactory.getInstance().getAuctionOperationDAO();
		LotOperationDAO lotOperationDAO = DAOFactory.getInstance().getLotOperationDAO();
		
		Integer auctionId = 2;
		Integer lotId = 4;
		
		try {
			userOperationDAO.payForLot(auctionId, lotId);
			Auction auction = auctionOperationDAO.getAuctionInfo(auctionId);
			Lot lot = lotOperationDAO.getLotInfo(lotId);
			
			Assert.assertEquals(true, auction.getStatus().equals(AuctionStatus.COMPLETED) &&
					lot.getStatus().equals(LotStatus.SOLED));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAuctionCurrentBet() {
		UserOperationDAO userOperationDAO = DAOFactory.getInstance().getUserOperationDAO();
		AuctionOperationDAO auctionOperationDAO = DAOFactory.getInstance().getAuctionOperationDAO();
		
		Auction auction = new Auction();
		auction.setId(3);
		
		try {
			Assert.assertEquals(true, userOperationDAO.getAuctionCurrentBet(auction)
					.equals(auctionOperationDAO.getAuctionInfo(auction.getId()).getLastBet().getValue()));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
