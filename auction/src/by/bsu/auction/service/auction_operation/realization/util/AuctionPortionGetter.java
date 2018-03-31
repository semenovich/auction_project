package by.bsu.auction.service.auction_operation.realization.util;

import java.util.ArrayList;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionsInfo;

public class AuctionPortionGetter {

	private static final int AUCTION_PORTION_QUANTITY = 10;

	private static final AuctionPortionGetter instance = new AuctionPortionGetter();
	
	private AuctionPortionGetter() {}
	
	public static AuctionPortionGetter getInstance() {
		return instance;
	}
	
	public AuctionsInfo getAuctionPortion(ArrayList<Auction> auctions, int page){
		if (auctions == null) {
			return null;
		}
		ArrayList<Auction> returnAuctions = new ArrayList<>();
		AuctionsInfo auctionsInfo = new AuctionsInfo();
		for (int i = (page - 1) * AUCTION_PORTION_QUANTITY; i < page * AUCTION_PORTION_QUANTITY && i < auctions.size() ; i++) {
			returnAuctions.add(auctions.get(i));
		}
		auctionsInfo.setAuctions(returnAuctions);
		auctionsInfo.setCurrentPage(page);
		auctionsInfo.setPages((int) Math.ceil(((double) auctions.size()) / AUCTION_PORTION_QUANTITY));
		return auctionsInfo;
	}
}
