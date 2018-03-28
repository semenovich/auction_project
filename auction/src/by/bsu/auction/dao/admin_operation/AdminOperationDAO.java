package by.bsu.auction.dao.admin_operation;

import by.bsu.auction.dao.exception.DAOException;

public interface AdminOperationDAO {
	boolean blockUser(String userLogin) throws DAOException;
	boolean unblockUser(String userLogin) throws DAOException;
	boolean blockLot(Integer lotId) throws DAOException;
	boolean unblockLot(Integer lotId) throws DAOException;
}
