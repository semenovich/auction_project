package by.tc.auction.dao.auction_operation.realization;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.tc.auction.dao.auction_operation.AuctionOperationDAO;
import by.tc.auction.dao.auction_operation.realization.util.AuctionChecker;
import by.tc.auction.dao.auction_operation.realization.util.AuctionInfoGetter;
import by.tc.auction.dao.auction_operation.realization.util.AuctionProcessor;
import by.tc.auction.dao.auction_operation.realization.util.AuctionSearcher;
import by.tc.auction.dao.connection_pool.ConnectionPool;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.exception.ConnectionPoolException;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Locale;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;

public class AuctionOperationDAOImpl implements AuctionOperationDAO {

	private final AuctionProcessor auctionProcessor = new AuctionProcessor();
	private final AuctionChecker auctionChecker = new AuctionChecker();
	private final AuctionInfoGetter auctionInfoGetter = new AuctionInfoGetter();
	private final AuctionSearcher auctionSearcher = new AuctionSearcher();
	
	private static final Logger logger = Logger.getLogger(AuctionOperationDAOImpl.class);

	@Override
	public boolean createAuctionWithLot(Auction auction, Lot lot) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return auctionProcessor.createAuctionWithLot(connection, auction, lot);
		} catch (SQLException | ConnectionPoolException e) {
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
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in AuctionOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public Auction getAuctionInfo(Integer auctionId) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (auctionChecker.isAuctionExist(connection, auctionId)) {
				return auctionInfoGetter.getAuctionInfo(connection, auctionId);
			}
			return null;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in AuctionOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ArrayList<Auction> getAuctions(Locale locale) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return auctionInfoGetter.getAuctions(connection, locale);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in AuctionOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ArrayList<Auction> getAuctionsBySearching(String searchLine, Locale locale) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return auctionSearcher.getAuctionsBySearching(connection, searchLine, locale);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in AuctionOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public ArrayList<Auction> getAuctionsByLotType(LotType lotType, Locale locale) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return auctionSearcher.getAuctionsByLotType(connection, lotType, locale);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in AuctionOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

}
