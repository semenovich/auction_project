package by.bsu.auction.service.auction_operation.realization;

import java.util.ArrayList;

import by.bsu.auction.dao.DAOFactory;
import by.bsu.auction.dao.auction_operation.AuctionOperationDAO;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.service.auction_operation.AuctionOperationService;
import by.bsu.auction.service.auction_operation.realization.util.AuctionPortionGetter;
import by.bsu.auction.service.auction_operation.realization.validation.Validator;
import by.bsu.auction.service.exception.LotInfoException;
import by.bsu.auction.service.exception.ServiceException;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionsInfo;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;

public class AuctionOperationServiceImpl implements AuctionOperationService {

	private static final String ERROR_MESSAGE = "Invalid lot info";
	
	private AuctionOperationDAO auctionOperationDAO;
	private AuctionPortionGetter auctionPortionGetter = AuctionPortionGetter.getInstance();
	
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
			return auctionPortionGetter.getAuctionPortion(auctions, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public AuctionsInfo getAuctionsBySearching(String searchLine, int page) throws ServiceException {
		try {
			ArrayList<Auction> auctions = auctionOperationDAO.getAuctionsBySearching(searchLine);
			return auctionPortionGetter.getAuctionPortion(auctions, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
	
	@Override
	public AuctionsInfo getAuctionsByLotType(LotType lotType, int page) throws ServiceException {
		try {
			ArrayList<Auction> auctions = auctionOperationDAO.getAuctionsByLotType(lotType);
			return auctionPortionGetter.getAuctionPortion(auctions, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
}
