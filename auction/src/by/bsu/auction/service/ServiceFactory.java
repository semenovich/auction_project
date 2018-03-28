package by.bsu.auction.service;

import by.bsu.auction.service.admin_operation.AdminOperationService;
import by.bsu.auction.service.admin_operation.realization.AdminOperationServiceImpl;
import by.bsu.auction.service.auction_operation.AuctionOperationService;
import by.bsu.auction.service.auction_operation.realization.AuctionOperationServiceImpl;
import by.bsu.auction.service.authentication.AuthService;
import by.bsu.auction.service.authentication.realization.AuthServiceImpl;
import by.bsu.auction.service.lot_operation.LotOperationService;
import by.bsu.auction.service.lot_operation.realization.LotOperationServiceImpl;
import by.bsu.auction.service.user_operation.ProfileService;
import by.bsu.auction.service.user_operation.UserOperationService;
import by.bsu.auction.service.user_operation.UsersService;
import by.bsu.auction.service.user_operation.realization.ProfileServiceImpl;
import by.bsu.auction.service.user_operation.realization.UserOperationServiceImpl;
import by.bsu.auction.service.user_operation.realization.UsersServiceImpl;

public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private final AuthService authServiceImpl = new AuthServiceImpl();
	private final ProfileService profileServiceImpl = new ProfileServiceImpl();
	private final LotOperationService lotOperationServiceImpl = new LotOperationServiceImpl();
	private final UsersService usersServiceImpl = new UsersServiceImpl();
	private final AuctionOperationService auctionOperationServiceImpl = new AuctionOperationServiceImpl();
	private final AdminOperationService adminOperationServiceImpl = new AdminOperationServiceImpl();
	private final UserOperationService userOperationServiceImpl = new UserOperationServiceImpl();
	
	public static ServiceFactory getInstance() {
		return instance;
	}
	
	public AuthService getAuthService() {
		return authServiceImpl;
	}
	
	public ProfileService getProfileService() {
		return profileServiceImpl;
	}
	
	public LotOperationService getLotOpeationService() {
		return lotOperationServiceImpl;
	}
	
	public UsersService getUsersService() {
		return usersServiceImpl;
	}
	
	public AuctionOperationService getAuctionOperationService() {
		return auctionOperationServiceImpl;
	}
	
	public AdminOperationService getAdminOperationService() {
		return adminOperationServiceImpl;
	}
	
	public UserOperationService getUserOperationService() {
		return userOperationServiceImpl;
	}
}
