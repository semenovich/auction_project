package by.bsu.auction.service.admin_operation;

import by.bsu.auction.service.exception.ServiceException;

public interface AdminOperationService {
	boolean blockUser(String userLogin) throws ServiceException;
	boolean unblockUser(String userLogin) throws ServiceException;
	boolean blockLot(Integer lotId) throws ServiceException;
	boolean unblockLot(Integer lotId) throws ServiceException;
}
