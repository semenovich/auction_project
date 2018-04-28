package by.tc.auction.service.user_operation.realization.util;

import java.util.ArrayList;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionsInfo;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotsInfo;
import by.tc.auction.entity.User;
import by.tc.auction.entity.UsersInfo;

/**
 * A class is used to get a list of lots portion from a list of users, user lots and user auctions.
 * @author semenovich
 *
 */
public class PortionGetter {
	
	private static final int LOT_PORTION_QUANTITY = 10;
	private static final int AUCTION_PORTION_QUANTITY = 10;
	private static final int USERS_PORTION_QUANTITY = 10;
	
	private static final PortionGetter instance = new PortionGetter();
	
	/**
	 * Default constructor.
	 */
	private PortionGetter() {}
	
	/**
	 * Returns the instance of the PortionGetter.
	 * @return the instance of the PortionGetter.
	 */
	public static PortionGetter getInstance() {
		return instance;
	}
	
	/**
	 * Returns a list of 10(<= if users in portion are less than 10) users from a list of users.
	 * @param users - a list of users.
	 * @param page - a page of portion.
	 * @return A list of 10(<= if users in portion are less than 10) users. {@code null} if a users list is null.
	 */
	public UsersInfo getUsersPortion(ArrayList<User> users, int page){
		if (users == null) {
			return null;
		}
		ArrayList<User> returnUsers = new ArrayList<>();
		UsersInfo usersInfo = new UsersInfo();
		for (int i = (page - 1) * USERS_PORTION_QUANTITY; i < page * USERS_PORTION_QUANTITY && i < users.size() ; i++) {
			returnUsers.add(users.get(i));
		}
		usersInfo.setUsers(returnUsers);
		usersInfo.setCurrentPage(page);
		usersInfo.setPages((int) Math.ceil(((double) users.size()) / USERS_PORTION_QUANTITY));
		return usersInfo;
	}
	
	/**
	 * Returns a list of 10(<= if lots in portion are less than 10) lots from a list of lots.
	 * @param userLots - a list of lots.
	 * @param page - a page of portion.
	 * @return A list of 10(<= if lots in portion are less than 10) lots. {@code null} if a lots list is null.
	 */
	public LotsInfo getLotsPortion(ArrayList<Lot> userLots, int page){
		if (userLots == null) {
			return null;
		}
		ArrayList<Lot> returnLots = new ArrayList<>();
		LotsInfo lotsInfo = new LotsInfo();
		for (int i = (page - 1) * LOT_PORTION_QUANTITY; i < userLots.size() && i < page * LOT_PORTION_QUANTITY; i++) {
			returnLots.add(userLots.get(i));
		}
		lotsInfo.setLots(returnLots);
		lotsInfo.setCurrentPage(page);
		lotsInfo.setPages((int) Math.ceil(((double) userLots.size()) / LOT_PORTION_QUANTITY));
		return lotsInfo;
	}
	
	/**
	 * Returns a list of 10(<= if auctions in portion are less than 10) auctions from a list of auctions.
	 * @param userAuctionParticipations - a list of auctions.
	 * @param page - a page of portion.
	 * @return A list of 10(<= if auctions in portion are less than 10) auctions. {@code null} if an auctions list is null.
	 */
	public AuctionsInfo getAuctionParcticipationsPortion(ArrayList<Auction> userAuctionParticipations, int page){
		if (userAuctionParticipations == null) {
			return null;
		}
		ArrayList<Auction> returnAuctionParticipations = new ArrayList<>();
		AuctionsInfo auctionInfo = new AuctionsInfo();
		for (int i = (page - 1) * AUCTION_PORTION_QUANTITY; i < page * AUCTION_PORTION_QUANTITY && i < userAuctionParticipations.size() ; i++) {
			returnAuctionParticipations.add(userAuctionParticipations.get(i));
		}
		auctionInfo.setAuctions(returnAuctionParticipations);
		auctionInfo.setCurrentPage(page);
		auctionInfo.setPages((int) Math.ceil(((double) userAuctionParticipations.size()) / AUCTION_PORTION_QUANTITY));
		return auctionInfo;
	}
}
