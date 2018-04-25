package by.tc.auction.service.auction_operation.realization;

import java.util.ArrayList;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.auction_operation.AuctionOperationDAO;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionsInfo;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;
import by.tc.auction.service.auction_operation.AuctionOperationService;
import by.tc.auction.service.auction_operation.realization.util.AuctionPortionGetter;
import by.tc.auction.service.auction_operation.realization.validation.LotInfoValidator;
import by.tc.auction.service.exception.LotInfoException;
import by.tc.auction.service.exception.ServiceException;

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
		if (!LotInfoValidator.validate(lot)) {
			throw new LotInfoException(ERROR_MESSAGE);
		}
		try {
			return auctionOperationDAO.createAuctionWithLot(auction, lot);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean createAuctionWithExistingLot(Auction auction, Integer lotId) throws ServiceException {
		try {
			return auctionOperationDAO.createAuctionWithExistingLot(auction, lotId);
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
	public AuctionsInfo getAuctions(Locale locale, int page) throws ServiceException {
		try {
			ArrayList<Auction> auctions = auctionOperationDAO.getAuctions(locale);
			return auctionPortionGetter.getAuctionPortion(auctions, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public AuctionsInfo getAuctionsBySearching(String searchLine, Locale locale, int page) throws ServiceException {
		try {
			ArrayList<Auction> auctions = auctionOperationDAO.getAuctionsBySearching(searchLine, locale);
			return auctionPortionGetter.getAuctionPortion(auctions, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
	
	@Override
	public AuctionsInfo getAuctionsByLotType(LotType lotType, Locale locale, int page) throws ServiceException {
		try {
			ArrayList<Auction> auctions = auctionOperationDAO.getAuctionsByLotType(lotType, locale);
			return auctionPortionGetter.getAuctionPortion(auctions, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
}
