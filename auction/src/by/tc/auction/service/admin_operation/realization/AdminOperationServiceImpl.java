package by.tc.auction.service.admin_operation.realization;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.admin_operation.AdminOperationDAO;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.service.admin_operation.AdminOperationService;
import by.tc.auction.service.exception.ServiceException;

/**
 * A class is used to provide methods for an administrator working on an application logic level and in a database.
 * @author semenovich
 *
 */
public class AdminOperationServiceImpl implements AdminOperationService {

	private AdminOperationDAO adminOperationDAO;
	
	/**
	 * Default constructor.
	 */
	public AdminOperationServiceImpl() {
		DAOFactory factory = DAOFactory.getInstance();
		adminOperationDAO = factory.getAdminOperationDAO();
	}

	/**
	 * Blocks a user in a database.
	 * @param userLogin - a user login.
	 * @return {@code true} - if a user has been blocked. {@code false} - if a user hasn't been blocked.
	 * @throws ServiceException - if an error occurred during operation in a database. 
	 */
	@Override
	public boolean blockUser(String userLogin) throws ServiceException {
		try {
			return adminOperationDAO.blockUser(userLogin);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Unblocks a user by a login in a database.
	 * @param userLogin - a user login.
	 * @return {@code true} - if a user has been unblocked. {@code false} - if a user hasn't been unblocked.
	 * @throws ServiceException - if an error occurred during operation in a database. 
	 *
	 */
	@Override
	public boolean unblockUser(String userLogin) throws ServiceException {
		try {
			return adminOperationDAO.unblockUser(userLogin);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Blocks a lot by an ID in a database.
	 * @param lotId - a lot ID.
	 * @return {@code true} - if a lot has been blocked. {@code false} - if a lot hasn't been blocked.
	 * @throws ServiceException - if an error occurred during operation in a database.
	 */
	@Override
	public boolean blockLot(Integer lotId) throws ServiceException {
		try {
			return adminOperationDAO.blockLot(lotId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Unblocks a lot by an ID in a database.
	 * In a method the same DAO method should be used.
	 * @param lotId - a lot ID.
	 * @return {@code true} - if a lot has been unblocked. {@code false} - if a lot hasn't been unblocked.
	 * @throws ServiceException - if an error occurred during operation in a database.
	 */
	@Override
	public boolean unblockLot(Integer lotId) throws ServiceException {
		try {
			return adminOperationDAO.unblockLot(lotId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

}
