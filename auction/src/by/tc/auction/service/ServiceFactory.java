package by.tc.auction.service;

import by.tc.auction.service.admin_operation.AdminOperationService;
import by.tc.auction.service.admin_operation.realization.AdminOperationServiceImpl;
import by.tc.auction.service.auction_operation.AuctionOperationService;
import by.tc.auction.service.auction_operation.realization.AuctionOperationServiceImpl;
import by.tc.auction.service.authentication.AuthService;
import by.tc.auction.service.authentication.realization.AuthServiceImpl;
import by.tc.auction.service.lot_operation.LotOperationService;
import by.tc.auction.service.lot_operation.realization.LotOperationServiceImpl;
import by.tc.auction.service.user_operation.ProfileService;
import by.tc.auction.service.user_operation.UserOperationService;
import by.tc.auction.service.user_operation.UsersService;
import by.tc.auction.service.user_operation.realization.ProfileServiceImpl;
import by.tc.auction.service.user_operation.realization.UserOperationServiceImpl;
import by.tc.auction.service.user_operation.realization.UsersServiceImpl;

public final class ServiceFactory {
	private final AuthService authServiceImpl = new AuthServiceImpl();
	private final ProfileService profileServiceImpl = new ProfileServiceImpl();
	private final LotOperationService lotOperationServiceImpl = new LotOperationServiceImpl();
	private final UsersService usersServiceImpl = new UsersServiceImpl();
	private final AuctionOperationService auctionOperationServiceImpl = new AuctionOperationServiceImpl();
	private final AdminOperationService adminOperationServiceImpl = new AdminOperationServiceImpl();
	private final UserOperationService userOperationServiceImpl = new UserOperationServiceImpl();
	
	private static final ServiceFactory instance = new ServiceFactory();
	
	
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
