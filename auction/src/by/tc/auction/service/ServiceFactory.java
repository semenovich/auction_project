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

/**
 * The factory of Service layer which returns objects for working with the application logic.
 * After working with the application logic work starts at the DAO layer.
 * @author semenovich
 *
 */
public final class ServiceFactory {
	private final AuthService authServiceImpl = new AuthServiceImpl();
	private final ProfileService profileServiceImpl = new ProfileServiceImpl();
	private final LotOperationService lotOperationServiceImpl = new LotOperationServiceImpl();
	private final UsersService usersServiceImpl = new UsersServiceImpl();
	private final AuctionOperationService auctionOperationServiceImpl = new AuctionOperationServiceImpl();
	private final AdminOperationService adminOperationServiceImpl = new AdminOperationServiceImpl();
	private final UserOperationService userOperationServiceImpl = new UserOperationServiceImpl();
	
	private static final ServiceFactory instance = new ServiceFactory();
	
	/**
	 * Returns instance of the Service factory.
	 * @return instance of the Service factory.
	 */
	public static ServiceFactory getInstance() {
		return instance;
	}
	
	/**
	 * Returns a Service object for working with an authentication.
	 * @return a Service object for working with an authentication.
	 */
	public AuthService getAuthService() {
		return authServiceImpl;
	}
	
	/**
	 * Returns a Service object for working with a profile.
	 * @return a Service object for working with a profile.
	 */
	public ProfileService getProfileService() {
		return profileServiceImpl;
	}
	
	/**
	 * Returns a Service object for working with a lot.
	 * @return a Service object for working with a lot.
	 */
	public LotOperationService getLotOpeationService() {
		return lotOperationServiceImpl;
	}
	
	/**
	 * Returns a Service object for working with users.
	 * @return a Service object for working with users.
	 */
	public UsersService getUsersService() {
		return usersServiceImpl;
	}
	
	/**
	 * Returns a Service object for working with an auction.
	 * @return a Service object for working with an auction.
	 */
	public AuctionOperationService getAuctionOperationService() {
		return auctionOperationServiceImpl;
	}
	
	/**
	 * Returns a Service object for an administrator working.
	 * @return a Service object for an administrator working.
	 */
	public AdminOperationService getAdminOperationService() {
		return adminOperationServiceImpl;
	}
	
	/**
	 * Returns a Service object for a server working.
	 * @return a Service object for a server working.
	 */
	public UserOperationService getUserOperationService() {
		return userOperationServiceImpl;
	}
}
