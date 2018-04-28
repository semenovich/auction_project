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

/**
 * A class is used to provide methods for working with auctions on an application logic level and in a database.
 * @author semenovich
 *
 */
public class AuctionOperationServiceImpl implements AuctionOperationService {

	private static final String ERROR_MESSAGE = "Invalid lot info";
	
	private AuctionOperationDAO auctionOperationDAO;
	private AuctionPortionGetter auctionPortionGetter = AuctionPortionGetter.getInstance();
	
	/**
	 * Default constructor.
	 */
	public AuctionOperationServiceImpl() {
		DAOFactory factory = DAOFactory.getInstance();
		auctionOperationDAO = factory.getAuctionOperationDAO();
	}

	/**
	 * Creates an auction with a lot in a database if lot info is valid.
	 * @param auction - an auction which will be created in a database. Only the start time, the minimum bet, the type (and the end time for Online type) fields must be filled in.
	 * @param lot - a lot which will be created and used in auction in a database. All fields except the status must be filled in.
	 * @return {@code true} - if an auction and a lot have been created. {@code false} - if an auction and a lot haven't been created. 
	 * @throws ServiceException - if an error occurred during operation in a database. 
	 * @throws LotInfoException - if a lot has incorrect info.
	 */
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

	/**
	 * Creates an auction with an existing lot in a database.
	 * @param auction - an auction which will be created. Only the start time, the minimum bet, the type (and the end time for Online type) fields must be filled in.
	 * @param lotId - an ID of an existing lot.
	 * @return {@code true} - if an auction has been created. {@code false} - if an auction hasn't been created. 
	 * @throws ServiceException - if an error occurred during operation in a database. 
	 */
	@Override
	public boolean createAuctionWithExistingLot(Auction auction, Integer lotId) throws ServiceException {
		try {
			return auctionOperationDAO.createAuctionWithExistingLot(auction, lotId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns info of an auction from a database.
	 * @param auctionId - an ID of an auction.
	 * @return Auction - if an auction is exist. {@code null} - if an auction doesn't exist.
	 * @throws ServiceException - if an error occurred during operation in a database. 
	 */
	@Override
	public Auction getAuctionInfo(Integer auctionId) throws ServiceException {
		try {
			return auctionOperationDAO.getAuctionInfo(auctionId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a list of auctions portion from a database.
	 * @param locale - a locale of auctions.
	 * @param page - a page of an auctions list.
	 * @return Auctions list of 10(<= if auctions in portion are less than 10) auctions if auctions exist. Empty list if auctions don't exist.
	 * @throws ServiceException - if an error occurred during operation in a database. 
	 */
	@Override
	public AuctionsInfo getAuctions(Locale locale, int page) throws ServiceException {
		try {
			ArrayList<Auction> auctions = auctionOperationDAO.getAuctions(locale);
			return auctionPortionGetter.getAuctionPortion(auctions, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a list of auctions portion by matching a name of a lot from a database.
	 * @param searchLine - a search line which will be matched with a lot name.
	 * @param locale - a locale of auctions.
	 * @param page - a page of an auctions list.
	 * @return Auctions list of 10(<= if auctions in portion are less than 10) auctions if such auctions exist. Empty list if such auctions don't exist.
	 * @throws ServiceException - if an error occurred during operation in a database. 
	 */
	@Override
	public AuctionsInfo getAuctionsBySearching(String searchLine, Locale locale, int page) throws ServiceException {
		try {
			ArrayList<Auction> auctions = auctionOperationDAO.getAuctionsBySearching(searchLine, locale);
			return auctionPortionGetter.getAuctionPortion(auctions, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
	
	/**
	 * Returns a list of auctions portion by searching by the type of a lot from a database.
	 * @param lotType - a type of lot.
	 * @param locale - a locale of auctions.
	 * @param page - a page of an auctions list.
	 * @return Auctions list of 10(<= if auctions in portion are less than 10) auctions if such auctions exist. Empty list if such auctions don't exist.
	 * @throws ServiceException - if an error occurred during operation in a database.
	 */
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
