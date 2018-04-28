package by.tc.auction.dao;

import by.tc.auction.dao.admin_operation.AdminOperationDAO;
import by.tc.auction.dao.admin_operation.realization.AdminOperationDAOImpl;
import by.tc.auction.dao.auction_operation.AuctionOperationDAO;
import by.tc.auction.dao.auction_operation.realization.AuctionOperationDAOImpl;
import by.tc.auction.dao.authentication.AuthDAO;
import by.tc.auction.dao.authentication.realization.AuthDAOImpl;
import by.tc.auction.dao.lot_operation.LotOperationDAO;
import by.tc.auction.dao.lot_operation.realization.LotOperationDAOImpl;
import by.tc.auction.dao.server_operation.ServerOperationDAO;
import by.tc.auction.dao.server_operation.realization.ServerOperationDAOImpl;
import by.tc.auction.dao.user_operation.ProfileDAO;
import by.tc.auction.dao.user_operation.UserOperationDAO;
import by.tc.auction.dao.user_operation.UsersDAO;
import by.tc.auction.dao.user_operation.realization.ProfileDAOImpl;
import by.tc.auction.dao.user_operation.realization.UserOperationDAOImpl;
import by.tc.auction.dao.user_operation.realization.UsersDAOImpl;

/**
 * The factory of DAO layer which returns objects for working with the database.
 * @author semenovich
 *
 */

public final class DAOFactory {
	
	private final AuthDAO authDAOImpl = new AuthDAOImpl();
	private final ProfileDAO profileDAOImpl = new ProfileDAOImpl();
	private final LotOperationDAO lotOperationDAOImpl = new LotOperationDAOImpl();
	private final UsersDAO usersDAOImpl = new UsersDAOImpl();
	private final AuctionOperationDAO auctionOperationDAOImpl = new AuctionOperationDAOImpl();
	private final AdminOperationDAO adminOperationDAOImpl = new AdminOperationDAOImpl();
	private final UserOperationDAO userOperationDAOImpl = new UserOperationDAOImpl();
	private final ServerOperationDAO serverOperationDAOImpl = new ServerOperationDAOImpl();
	
	private static final DAOFactory instance = new DAOFactory();
	
	private DAOFactory() {}

	/**
	 * Returns instance of the DAOFactory.
	 * @return instance of the DAOFactory.
	 */
	public static DAOFactory getInstance() {
		return instance;
	}
	
	/**
	 * Returns a DAO object for working with an authentication.
	 * @return a DAO object for working with an authentication.
	 */
	public AuthDAO getAuthDAO() {
		return authDAOImpl;
	}
	
	/**
	 * Returns a DAO object for working with a profile.
	 * @return a DAO object for working with a profile.
	 */
	public ProfileDAO getProfileDAO() {
		return profileDAOImpl;
	}
	
	/**
	 * Returns a DAO object for working with a lot.
	 * @return a DAO object for working with a lot.
	 */
	public LotOperationDAO getLotOperationDAO() {
		return lotOperationDAOImpl;
	}
	
	/**
	 * Returns a DAO object for working with users.
	 * @return a DAO object for working with users.
	 */
	public UsersDAO getUsersDAO() {
		return usersDAOImpl;
	}
	
	/**
	 * Returns a DAO object for working with an auction.
	 * @return a DAO object for working with an auction.
	 */
	public AuctionOperationDAO getAuctionOperationDAO() {
		return auctionOperationDAOImpl;
	}
	
	/**
	 * Returns a DAO object for an administrator working.
	 * @return a DAO object for an administrator working.
	 */
	public AdminOperationDAO getAdminOperationDAO() {
		return adminOperationDAOImpl;
	}
	
	/**
	 * Returns a DAO object for working with a user.
	 * @return a DAO object for working with a user.
	 */
	public UserOperationDAO getUserOperationDAO() {
		return userOperationDAOImpl;
	}
	
	/**
	 * Returns a DAO object for a server working.
	 * @return a DAO object for a working.
	 */
	public ServerOperationDAO getServerOperationDAO() {
		return serverOperationDAOImpl;
	}
}
