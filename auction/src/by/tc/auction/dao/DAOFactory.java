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

	public static DAOFactory getInstance() {
		return instance;
	}
	
	public AuthDAO getAuthDAO() {
		return authDAOImpl;
	}
	
	public ProfileDAO getProfileDAO() {
		return profileDAOImpl;
	}
	
	public LotOperationDAO getLotOperationDAO() {
		return lotOperationDAOImpl;
	}
	
	public UsersDAO getUsersDAO() {
		return usersDAOImpl;
	}
	
	public AuctionOperationDAO getAuctionOperationDAO() {
		return auctionOperationDAOImpl;
	}
	
	public AdminOperationDAO getAdminOperationDAO() {
		return adminOperationDAOImpl;
	}
	
	public UserOperationDAO getUserOperationDAO() {
		return userOperationDAOImpl;
	}
	
	public ServerOperationDAO getServerOperationDAO() {
		return serverOperationDAOImpl;
	}
}
