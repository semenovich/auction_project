package by.tc.auction.service.admin_operation.realization;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.admin_operation.AdminOperationDAO;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.service.admin_operation.AdminOperationService;
import by.tc.auction.service.exception.ServiceException;

public class AdminOperationServiceImpl implements AdminOperationService {

	private AdminOperationDAO adminOperationDAO;
	
	public AdminOperationServiceImpl() {
		DAOFactory factory = DAOFactory.getInstance();
		adminOperationDAO = factory.getAdminOperationDAO();
	}

	@Override
	public boolean blockUser(String userLogin) throws ServiceException {
		try {
			return adminOperationDAO.blockUser(userLogin);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean unblockUser(String userLogin) throws ServiceException {
		try {
			return adminOperationDAO.unblockUser(userLogin);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean blockLot(Integer lotId) throws ServiceException {
		try {
			return adminOperationDAO.blockLot(lotId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean unblockLot(Integer lotId) throws ServiceException {
		try {
			return adminOperationDAO.blockLot(lotId);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

}
