package by.bsu.auction.service.auction_operation.realization;

import java.util.ArrayList;

import by.bsu.auction.dao.DAOFactory;
import by.bsu.auction.dao.auction_operation.AuctionOperationDAO;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.entity.Auction;
import by.bsu.auction.entity.AuctionsInfo;
import by.bsu.auction.entity.Lot;
import by.bsu.auction.service.auction_operation.AuctionOperationService;
import by.bsu.auction.service.auction_operation.realization.validation.Validator;
import by.bsu.auction.service.exception.LotInfoException;
import by.bsu.auction.service.exception.ServiceException;

public class AuctionOperationServiceImpl implements AuctionOperationService {

	private static final int AUCTION_PORTION_QUANTITY = 10;
	private static final String ERROR_MESSAGE = "Invalid lot info";
	
	private AuctionOperationDAO auctionOperationDAO;
	
	public AuctionOperationServiceImpl() {
		DAOFactory factory = DAOFactory.getInstance();
		auctionOperationDAO = factory.getAuctionOperationDAO();
	}

	@Override
	public boolean createAuctionWithLot(Auction auction, Lot lot) throws ServiceException, LotInfoException {
		try {
			if (!Validator.validateLotInfo(lot)) {
				throw new LotInfoException(ERROR_MESSAGE);
			}
			return auctionOperationDAO.createAuctionWithLot(auction, lot);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean createAuctionFromExistingLot(Auction auction, Integer lotId) throws ServiceException {
		try {
			return auctionOperationDAO.createAuctionFromExistingLot(auction, lotId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Auction getAuctionInfo(Integer auctionId) throws ServiceException {
		try {
			return auctionOperationDAO.getAuctionInfo(auctionId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public AuctionsInfo getAuctions(int page) throws ServiceException {
		try {
			ArrayList<Auction> auctions = auctionOperationDAO.getAuctions();
			return getAuctionPortion(auctions, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public AuctionsInfo getAuctionsBySearching(String searchLine, int page) throws ServiceException {
		try {
			ArrayList<Auction> auctions = auctionOperationDAO.getAuctionsBySearching(searchLine);
			return getAuctionPortion(auctions, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	private AuctionsInfo getAuctionPortion(ArrayList<Auction> auctions, int page){
		if (auctions == null) {
			return null;
		}
		ArrayList<Auction> returnAuctions = new ArrayList<>();
		AuctionsInfo auctionsInfo = new AuctionsInfo();
		for (int i = (page - 1) * AUCTION_PORTION_QUANTITY; i < page * AUCTION_PORTION_QUANTITY && i < auctions.size() ; i++) {
			returnAuctions.add(auctions.get(i));
		}
		auctionsInfo.setAuctions(returnAuctions);
		auctionsInfo.setCurrentPage(page);
		auctionsInfo.setPages((int) Math.ceil(((double) auctions.size()) / AUCTION_PORTION_QUANTITY));
		return auctionsInfo;
	}
}
