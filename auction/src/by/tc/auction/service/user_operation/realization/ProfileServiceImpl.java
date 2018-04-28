package by.tc.auction.service.user_operation.realization;

import java.util.ArrayList;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.user_operation.ProfileDAO;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionsInfo;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotsInfo;
import by.tc.auction.entity.User;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.exception.UserInfoException;
import by.tc.auction.service.user_operation.ProfileService;
import by.tc.auction.service.user_operation.realization.util.PortionGetter;
import by.tc.auction.service.user_operation.realization.validation.UserEditingValidator;

/**
 * A class is used to provide methods for working with users profiles on an application logic level and in a database.
 * @author semenovich
 *
 */
public class ProfileServiceImpl implements ProfileService {

	private static final String ERROR_MESSAGE = "Invalid editing user data";
	
	private ProfileDAO profileDAO;
	private PortionGetter portionGetter = PortionGetter.getInstance();
	
	/**
	 * Default constructor.
	 */
	public ProfileServiceImpl() {
		DAOFactory factory = DAOFactory.getInstance();
		profileDAO = factory.getProfileDAO();
	}

	/**
	 * Returns user profile info from a database.
	 * @param login - a user login.
	 * @return A user if a user exists. {@code null} if a user doesn't exist.
	 * @throws ServiceException - if an error occurred during operation in a database.
	 */
	@Override
	public User getUserInfo(String login) throws ServiceException {
		try {
			return profileDAO.getUserInfo(login);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a list of user lots portion from a database.
	 * @param login - a user login.
	 * @param page - a page of a lots list.
	 * @return A list of user lots 10(<= if lots in portion are less than 10). Empty list if such lots don't exist. {@code null} if a user doesn't exist.
	 * @throws ServiceException - if an error occurred during operation in a database.
	 */
	@Override
	public LotsInfo getUserLots(String login, int page) throws ServiceException {
		try {
			ArrayList<Lot> userLots = profileDAO.getUserLots(login);
			return portionGetter.getLotsPortion(userLots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
	
	/**
	 * Returns a list of user win lots portion from a database.
	 * @param login - a user login.
	 * @param page - a page of a lots list.
	 * @return A list of user win lots 10(<= if lots in portion are less than 10). Empty list if such lots don't exist. {@code null} if a user doesn't exist. 
	 * @throws ServiceException - if an error occurred during operation in a database.
	 */
	@Override
	public LotsInfo getUserWinLots(String login, int page) throws ServiceException {
		try {
			ArrayList<Lot> userLots = profileDAO.getUserWinLots(login);
			return portionGetter.getLotsPortion(userLots, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a list of user auction participations portion from a database.
	 * @param login - a user login.
	 * @param page - a page of an auctions list.
	 * @return A list of user auction participations 10(<= if auctions in portion are less than 10). Empty list if such auctions don't exist. {@code null} if a user doesn't exist. 
	 * @throws ServiceException - if an error occurred during operation in a database.
	 */
	@Override
	public AuctionsInfo getUserAuctionParticipations(String login, int page) throws ServiceException {
		try {
			ArrayList<Auction> userAuctionParticipations = profileDAO.getUserAuctionParticipation(login);
			return portionGetter.getAuctionParcticipationsPortion(userAuctionParticipations, page);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
	
	/**
	 * Edits user profile info in a database if profile info is correct.
	 * <br> An email must be "example@adress.com".
	 * <br> Phone must be like "1234567890".
	 * <br> All fields (surname, name, phone, email, passport ID, passport issued by, login) must be not empty.
	 * @param user - user updated info. Only surname, name, phone, email, passport ID, passport issued by, login fields must be filled in.
	 * @return {@code true} if user profile info has been edited. {@code false} if user profile info hasn't been edited.
	 * @throws ServiceException - if an error occurred during operation.
	 * @throws UserInfoException - if user info is incorrect.
	 */
	@Override
	public boolean editUserInfo(User user) throws ServiceException, UserInfoException {
		if (!UserEditingValidator.validate(user)) {
			throw new UserInfoException(ERROR_MESSAGE);
		}
		try {
			return profileDAO.editUserInfo(user);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Uploads a user profile image in a database.
	 * @param userLogin - a user login.
	 * @param imagePath - an image path.
	 * @return {@code true} if a user profile image has been uploaded. {@code false} if a user profile image hasn't been uploaded.
	 * @throws ServiceException - if an error occurred during operation in a database.
	 */
	@Override
	public boolean uploadUserImage(String userLogin, String imagePath) throws ServiceException {
		try {
			return profileDAO.uploadUserImage(userLogin, imagePath);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}
}
