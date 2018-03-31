package by.bsu.auction.service.server_operation.realization;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import by.bsu.auction.dao.DAOFactory;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.server_operation.ServerOperationDAO;
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
		Date currentTime = new Date(Calendar.getInstance().getTimeInMillis());
		for (int i = 0; i < auctions.size(); i++) {
			if (auctions.get(i).getType() == AuctionType.ONLINE) {
				if (auctions.get(i).getEndTime().getTime() < currentTime.getTime()) {
					endedAuctions.add(auctions.get(i));
				}
			}
			if (auctions.get(i).getType() == AuctionType.ENGLISH) {
				if (auctions.get(i).getLastBetTime().getTime() < currentTime.getTime() + AMOUNT_OF_TIME_TO_CLOSE_ENGLISH_AUCTION_TYPE) {
					endedAuctions.add(auctions.get(i));
				}
			} 
		}
		return endedAuctions;
	}
}
