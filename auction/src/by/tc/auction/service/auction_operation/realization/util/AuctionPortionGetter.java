package by.tc.auction.service.auction_operation.realization.util;

import java.util.ArrayList;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.AuctionsInfo;

/**
 * A class is used to get a list of auctions portion from a list of auctions.
 * @author semenovich
 *
 */
public class AuctionPortionGetter {

	private static final int AUCTION_PORTION_QUANTITY = 10;

	private static final AuctionPortionGetter instance = new AuctionPortionGetter();
	
	private AuctionPortionGetter() {}
	
	/**
	 * Returns the instance of the AuctionPortionGetter.
	 * @return the instance of the AuctionPortionGetter.
	 */
	public static AuctionPortionGetter getInstance() {
		return instance;
	}
	
	/**
	 * Returns a list of 10(&le; if auctions in portion are less than 10) auctions from a list of auctions.
	 * @param auctions - a list of auctions.
	 * @param page - a page of portion.
	 * @return A list of 10(&le; if auctions in portion are less than 10) auctions. {@code null} if an auctions list is null
	 */
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
