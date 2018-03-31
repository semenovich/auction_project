package by.bsu.auction.service.user_operation.realization;

import java.util.ArrayList;

import by.bsu.auction.dao.DAOFactory;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.user_operation.ProfileDAO;
import by.bsu.auction.service.exception.ServiceException;
import by.bsu.auction.service.exception.UserInfoException;
import by.bsu.auction.service.user_operation.ProfileService;
import by.bsu.auction.service.user_operation.realization.validation.Validator;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionsInfo;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotsInfo;
import by.tc.auction.entity.User;

public class ProfileServiceImpl implements ProfileService {

	private static final String ERROR_MESSAGE = "Invalid editing user data";
	
	private ProfileDAO profileDAO;
	private PortionGetter portionGetter = PortionGetter.getInstance();
	
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
			return portionGetter.getLotsPortion(userLots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
	
	@Override
	public LotsInfo getUserWinLots(String login, int page) throws ServiceException {
		try {
			ArrayList<Lot> userLots = profileDAO.getUserWinLots(login);
			return portionGetter.getLotsPortion(userLots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public AuctionsInfo getUserAuctionParticipations(String login, int page) throws ServiceException {
		try {
			ArrayList<Auction> userAuctionParticipations = profileDAO.getUserAuctionParticipation(login);
			return portionGetter.getAuctionParcticipationsPortion(userAuctionParticipations, page);
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
}
