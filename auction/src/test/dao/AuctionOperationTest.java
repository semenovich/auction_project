package test.dao;

import org.junit.Assert;
import org.junit.Test;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.auction_operation.AuctionOperationDAO;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionType;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.LotType;

public class AuctionOperationTest {

	@Test
	public void testGetAuctionInfo() {
		AuctionOperationDAO auctionOperationDAO = DAOFactory.getInstance().getAuctionOperationDAO();
		
		try {
			Auction auction = auctionOperationDAO.getAuctionInfo(7);
			Assert.assertEquals(true, auction.getLastBetUser().equals("Admin") && 
					auction.getLot().getName().equals("InfoAuction") &&
					auction.getType().equals(AuctionType.ENGLISH) &&
					auction.getMinBet().getValue().equals(12.0) &&
					auction.getLastBet().getValue().equals(20.0));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testGetAuctions() {
		AuctionOperationDAO auctionOperationDAO = DAOFactory.getInstance().getAuctionOperationDAO();
	
		try {
			Assert.assertEquals(false, auctionOperationDAO.getAuctions(Locale.en).isEmpty());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAuctionsBySearching() {
		AuctionOperationDAO auctionOperationDAO = DAOFactory.getInstance().getAuctionOperationDAO();

		try {
			Assert.assertEquals(false, auctionOperationDAO.getAuctionsBySearching("Search", Locale.en).isEmpty());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAuctionsByLotType() {
		AuctionOperationDAO auctionOperationDAO = DAOFactory.getInstance().getAuctionOperationDAO();

		try {
			Assert.assertEquals(false, auctionOperationDAO.getAuctionsByLotType(LotType.CAR, Locale.en).isEmpty());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
