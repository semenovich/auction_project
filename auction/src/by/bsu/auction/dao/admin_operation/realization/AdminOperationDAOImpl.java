package by.bsu.auction.dao.admin_operation.realization;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.bsu.auction.dao.admin_operation.AdminOperationDAO;
import by.bsu.auction.dao.admin_operation.realization.util.AdminBlocker;
import by.bsu.auction.dao.admin_operation.realization.util.ExistenceChecker;
import by.bsu.auction.dao.authentication.realization.AuthDAOImpl;
import by.bsu.auction.dao.connection_pool.ConnectionPool;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.exception.DBConnectionException;

public class AdminOperationDAOImpl implements AdminOperationDAO {

	private AdminBlocker adminBlocker = new AdminBlocker();
	private ExistenceChecker checker = new ExistenceChecker();
	private static final Logger logger = Logger.getLogger(AuthDAOImpl.class);
	
	public AdminOperationDAOImpl() {}

	@Override
	public boolean blockUser(String userLogin) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (checker.isUserExist(connection, userLogin)) {
				return adminBlocker.blockUser(connection, userLogin);
			}
			return false;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in AdminOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean unblockUser(String userLogin) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (checker.isUserExist(connection, userLogin)) {
				return adminBlocker.unblockUser(connection, userLogin);
			}
			return false;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in AdminOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean blockLot(Integer lotId) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (checker.isLotExist(connection, lotId)) {
				return adminBlocker.blockLot(connection, lotId);
			}
			return false;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in AdminOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
	
	@Override
	public boolean unblockLot(Integer lotId) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (checker.isLotExist(connection, lotId)) {
				return adminBlocker.unblockLot(connection, lotId);
			}
			return false;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in AdminOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}
}
