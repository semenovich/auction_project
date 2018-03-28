package by.bsu.auction.service.user_operation.realization;

import java.util.ArrayList;

import by.bsu.auction.dao.DAOFactory;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.user_operation.ProfileDAO;
import by.bsu.auction.entity.Auction;
import by.bsu.auction.entity.AuctionsInfo;
import by.bsu.auction.entity.Lot;
import by.bsu.auction.entity.LotsInfo;
import by.bsu.auction.entity.User;
import by.bsu.auction.service.exception.ServiceException;
import by.bsu.auction.service.exception.UserInfoException;
import by.bsu.auction.service.user_operation.ProfileService;
import by.bsu.auction.service.user_operation.realization.validation.Validator;

public class ProfileServiceImpl implements ProfileService {

	private ProfileDAO profileDAO;
	
	private static final int LOT_PORTION_QUANTITY = 10;
	private static final int AUCTION_PORTION_QUANTITY = 10;

	private static final String ERROR_MESSAGE = "Invalid editing user data";
	
	public ProfileServiceImpl() {
		DAOFactory factory = DAOFactory.getInstance();
		profileDAO = factory.getProfileDAO();
	}

	@Override
	public User getUserInfo(String login) throws ServiceException {
		try {
			return profileDAO.getUserInfo(login);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public LotsInfo getUserLots(String login, int page) throws ServiceException {
		try {
			ArrayList<Lot> userLots = profileDAO.getUserLots(login);
			return getLotsPortion(userLots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
	
	@Override
	public LotsInfo getUserWinLots(String login, int page) throws ServiceException {
		try {
			ArrayList<Lot> userLots = profileDAO.getUserWinLots(login);
			return getLotsPortion(userLots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public AuctionsInfo getUserAuctionParticipations(String login, int page) throws ServiceException {
		try {
			ArrayList<Auction> userAuctionParticipations = profileDAO.getUserAuctionParticipation(login);
			return getAuctionParcticipationsPortion(userAuctionParticipations, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
	
	@Override
	public boolean editUserInfo(User user) throws ServiceException, UserInfoException {
		if (!Validator.validateUserEditing(user)) {
			throw new UserInfoException(ERROR_MESSAGE);
		}
		try {
			return profileDAO.editUserInfo(user);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
	
	private LotsInfo getLotsPortion(ArrayList<Lot> userLots, int page){
		if (userLots == null) {
			return null;
		}
		ArrayList<Lot> returnLots = new ArrayList<>();
		LotsInfo lotsInfo = new LotsInfo();
		for (int i = (page - 1) * LOT_PORTION_QUANTITY; i < userLots.size() && i < page * LOT_PORTION_QUANTITY; i++) {
			returnLots.add(userLots.get(i));
		}
		lotsInfo.setLots(returnLots);
		lotsInfo.setCurrentPage(page);
		lotsInfo.setPages((int) Math.ceil(((double) userLots.size()) / LOT_PORTION_QUANTITY));
		return lotsInfo;
	}
	
	private AuctionsInfo getAuctionParcticipationsPortion(ArrayList<Auction> userAuctionParticipations, int page){
		if (userAuctionParticipations == null) {
			return null;
		}
		ArrayList<Auction> returnAuctionParticipations = new ArrayList<>();
		AuctionsInfo auctionInfo = new AuctionsInfo();
		for (int i = (page - 1) * AUCTION_PORTION_QUANTITY; i < page * AUCTION_PORTION_QUANTITY && i < userAuctionParticipations.size() ; i++) {
			returnAuctionParticipations.add(userAuctionParticipations.get(i));
		}
		auctionInfo.setAuctions(returnAuctionParticipations);
		auctionInfo.setCurrentPage(page);
		auctionInfo.setPages((int) Math.ceil(((double) userAuctionParticipations.size()) / AUCTION_PORTION_QUANTITY));
		return auctionInfo;
	}
}
