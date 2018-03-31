package by.bsu.auction.service.user_operation.realization;

import java.util.ArrayList;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionsInfo;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotsInfo;
import by.tc.auction.entity.User;
import by.tc.auction.entity.UsersInfo;

public class PortionGetter {
	
	private static final int LOT_PORTION_QUANTITY = 10;
	private static final int AUCTION_PORTION_QUANTITY = 10;
	private static final int USERS_PORTION_QUANTITY = 10;
	
	private static final PortionGetter instance = new PortionGetter();
	
	private PortionGetter() {}
	
	public static PortionGetter getInstance() {
		return instance;
	}
	
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
