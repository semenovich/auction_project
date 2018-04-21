package by.tc.auction.service.server_operation.realization;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.server_operation.ServerOperationDAO;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionType;

public class ServerOperationJobService implements Job {

	private static final Logger logger = Logger.getLogger(ServerOperationJobService.class);
	private static final long AMOUNT_OF_TIME_TO_CLOSE_ENGLISH_AUCTION_TYPE = 600000L;
	
	private ServerOperationDAO serverOperationDAO;
	
	public ServerOperationJobService() {
		DAOFactory factory = DAOFactory.getInstance();
		serverOperationDAO = factory.getServerOperationDAO();
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			ArrayList<Auction> auctions = serverOperationDAO.getActiveAuctions();
			ArrayList<Auction> endedAuctions = getEndedAuctions(auctions);
			serverOperationDAO.completeAuctions(endedAuctions);
		} catch (DAOException e) {
			logger.error("Error in ServerOperationJobService", e);
		}
	}

	private ArrayList<Auction> getEndedAuctions(ArrayList<Auction> auctions){
		ArrayList<Auction> endedAuctions = new ArrayList<>();
		Timestamp currentTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
		for (Auction auction : auctions) {
			if (auction.getType() == AuctionType.ONLINE) {
				if (auction.getEndTime().getTime() < currentTime.getTime()) {
					endedAuctions.add(auction);
				}
			}
			if (auction.getType() == AuctionType.ENGLISH) {
				if (auction.getLastBetTime() != null && (currentTime.getTime() - auction.getLastBetTime().getTime()) > AMOUNT_OF_TIME_TO_CLOSE_ENGLISH_AUCTION_TYPE) {
					endedAuctions.add(auction);
				}
			} 
		}
		return endedAuctions;
	}
}
