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

/**
 * A class is used to provide methods for working with auctions in a database.
 * @author semenovich
 *
 */
public class AuctionOperationDAOImpl implements AuctionOperationDAO {

	private final AuctionProcessor auctionProcessor = new AuctionProcessor();
	private final AuctionChecker auctionChecker = new AuctionChecker();
	private final AuctionInfoGetter auctionInfoGetter = new AuctionInfoGetter();
	private final AuctionSearcher auctionSearcher = new AuctionSearcher();
	
	private static final Logger logger = Logger.getLogger(AuctionOperationDAOImpl.class);

	/**
	 * Creates an auction with a lot in a database.
	 * @param auction - an auction which will be created in a database. Only the start time, the minimum bet, the type (and the end time for Online type) fields must be filled in.
	 * @param lot - a lot which will be created and used in auction in a database. All fields except the status must be filled in.
	 * @return {@code true}. 
	 * @throws DAOException - if an error occurred during operation with (in) a database or not all fields are filled.
	 */
	@Override
	public boolean createAuctionWithLot(Auction auction, Lot lot) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			auctionProcessor.createAuctionWithLot(connection, auction, lot);
			return true;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in AuctionOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Creates an auction with an existing lot in a database.
	 * @param auction - an auction which will be created in a database. Only the start time, the minimum bet, the type (and the end time for Online type) fields must be filled in.
	 * @param lotId - an ID of an existing lot.
	 * @return {@code true} - if an auction has been created. {@code false} - if a lot doesn't exist (or a lot is used in another auction) and an auction hasn't been created. 
	 * @throws DAOException - if an error occurred during operation with (in) a database or not all fields are filled.
	 */
	@Override
	public boolean createAuctionWithExistingLot(Auction auction, Integer lotId) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			if (auctionChecker.isLotExist(connection, lotId) && !auctionChecker.isLotUsed(connection, lotId)) {
				auctionProcessor.createAuctionWithExistingLot(connection, auction, lotId);
				return true;
			}
			return false;
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in AuctionOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns info of an auction from a database.
	 * @param auctionId - an ID of an auction.
	 * @return Auction - if an auction is exist in a database. {@code null} - if an auction doesn't exist.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
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

	/**
	 * Returns a list of auctions from a database. 
	 * @param locale - a locale of auctions.
	 * @return Auction list of auctions if auctions exist in a database. Empty list if auctions don't exist in a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public ArrayList<Auction> getAuctions(Locale locale) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return auctionInfoGetter.getAuctions(connection, locale);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in AuctionOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a list of auctions by matching a name of a lot from a database.
	 * @param searchLine - a search line which will be matched with a lot name.
	 * @param locale - a locale of auctions.
	 * @return Auction list of auctions if such auctions exist in a database. Empty list if such auctions don't exist in a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
	@Override
	public ArrayList<Auction> getAuctionsBySearching(String searchLine, Locale locale) throws DAOException {
		try(Connection connection = ConnectionPool.getInstance().getConnection()){
			return auctionSearcher.getAuctionsBySearching(connection, searchLine, locale);
		} catch (SQLException | ConnectionPoolException e) {
			logger.error("Error in AuctionOperationDAOImpl", e);
			throw new DAOException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * Returns a list of auctions by searching by the type of a lot from a database.
	 * @param lotType - a type of lot.
	 * @param locale - a locale of auctions.
	 * @return Auction list of auctions if such auctions exist in a database. Empty list if such auctions don't exist in a database.
	 * @throws DAOException - if an error occurred during operation with (in) a database.
	 */
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
