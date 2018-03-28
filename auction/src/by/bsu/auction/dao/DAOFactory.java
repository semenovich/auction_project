package by.bsu.auction.dao;

import by.bsu.auction.dao.admin_operation.AdminOperationDAO;
import by.bsu.auction.dao.admin_operation.realization.AdminOperationDAOImpl;
import by.bsu.auction.dao.auction_operation.AuctionOperationDAO;
import by.bsu.auction.dao.auction_operation.realization.AuctionOperationDAOImpl;
import by.bsu.auction.dao.authentication.AuthDAO;
import by.bsu.auction.dao.authentication.realization.AuthDAOImpl;
import by.bsu.auction.dao.lot_operation.LotOperationDAO;
import by.bsu.auction.dao.lot_operation.realization.LotOperationDAOImpl;
import by.bsu.auction.dao.server_operation.ServerOperationDAO;
import by.bsu.auction.dao.server_operation.realization.ServerOperationDAOImpl;
import by.bsu.auction.dao.user_operation.ProfileDAO;
import by.bsu.auction.dao.user_operation.UserOperationDAO;
import by.bsu.auction.dao.user_operation.UsersDAO;
import by.bsu.auction.dao.user_operation.realization.ProfileDAOImpl;
import by.bsu.auction.dao.user_operation.realization.UserOperationDAOImpl;
import by.bsu.auction.dao.user_operation.realization.UsersDAOImpl;

public final class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	
	private final AuthDAO authDAOImpl = new AuthDAOImpl();
	private final ProfileDAO profileDAOImpl = new ProfileDAOImpl();
	private final LotOperationDAO lotOperationDAOImpl = new LotOperationDAOImpl();
	private final UsersDAO usersDAOImpl = new UsersDAOImpl();
	private final AuctionOperationDAO auctionOperationDAOImpl = new AuctionOperationDAOImpl();
	private final AdminOperationDAO adminOperationDAOImpl = new AdminOperationDAOImpl();
	private final UserOperationDAO userOperationDAOImpl = new UserOperationDAOImpl();
	private final ServerOperationDAO serverOperationDAOImpl = new ServerOperationDAOImpl();
	
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
