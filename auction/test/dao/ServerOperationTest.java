package dao;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.auction_operation.AuctionOperationDAO;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.server_operation.ServerOperationDAO;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionStatus;

public class ServerOperationTest {

	@Test
	public void testGetActiveAuctions() {
		ServerOperationDAO serverOperationDAO = DAOFactory.getInstance().getServerOperationDAO();
		
		try {
			Assert.assertEquals(false, serverOperationDAO.getActiveAuctions().isEmpty());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCompleteAuctions() {
		ServerOperationDAO serverOperationDAO = DAOFactory.getInstance().getServerOperationDAO();
		AuctionOperationDAO auctionOperationDAO = DAOFactory.getInstance().getAuctionOperationDAO();
		
		String lastBetLogin = "Admin";
		
		ArrayList<Auction> completedAuctions = new ArrayList<Auction>();
		Auction endedAuction = new Auction();
		endedAuction.setId(5);
		endedAuction.setLastBetUser(lastBetLogin);
		completedAuctions.add(endedAuction);
		
		try {
			serverOperationDAO.completeAuctions(completedAuctions);
			endedAuction = auctionOperationDAO.getAuctionInfo(endedAuction.getId());
			
			Assert.assertEquals(true, endedAuction.getLastBetUser().equals(lastBetLogin) &&
					endedAuction.getStatus().equals(AuctionStatus.PENDING_PAYMENT));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
