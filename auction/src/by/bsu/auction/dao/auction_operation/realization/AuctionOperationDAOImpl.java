package by.bsu.auction.dao.auction_operation.realization;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.bsu.auction.dao.auction_operation.AuctionOperationDAO;
import by.bsu.auction.dao.auction_operation.realization.util.AuctionChecker;
import by.bsu.auction.dao.auction_operation.realization.util.AuctionProcessor;
import by.bsu.auction.dao.connection_pool.ConnectionPool;
import by.bsu.auction.dao.exception.DAOException;
import by.bsu.auction.dao.exception.DBConnectionException;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Lot;

public class AuctionOperationDAOImpl implements AuctionOperationDAO {

	AuctionProcessor auctionProcessor = new AuctionProcessor();
	AuctionChecker auctionChecker = new AuctionChecker();
	
	private static final Logger logger = Logger.getLogger(AuctionOperationDAOImpl.class);

	@Override
	public boolean createAuctionWithLot(Auction auction, Lot lot) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return auctionProcessor.createAuctionWithLot(connection, auction, lot);
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in AuctionOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public boolean createAuctionFromExistingLot(Auction auction, Integer lotId) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (auctionChecker.isLotExist(connection, lotId) && !auctionChecker.isLotUsed(connection, lotId)) {
				auctionProcessor.createAuctionFromExistingLot(connection, auction, lotId);
				return true;
			}
			return false;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in AuctionOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Auction getAuctionInfo(Integer auctionId) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (auctionChecker.isAuctionExist(connection, auctionId)) {
				return auctionProcessor.getAuctionInfo(connection, auctionId);
			}
			return null;
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in AuctionOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ArrayList<Auction> getAuctions() throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return auctionProcessor.getAuctions(connection);
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in AuctionOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ArrayList<Auction> getAuctionsBySearching(String searchLine) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return auctionProcessor.getAuctionsBySearching(connection, searchLine);
		} catch (SQLException | DBConnectionException e) {
			logger.error("Error in AuctionOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

}
