package by.tc.auction.entity;

/**
 * A list of an auction type.
 * <br> {@code ONLINE} - auction seller to increase the initial price. The seller exposes the product specified in advance to the online auction as a lot, indicating the initial price and the auction period. The initial price itself is the minimum price for which the seller is potentially willing to sell the exposed lot. The winner of the bidding will be the participant whose bid will be the last and highest at the time of the end of the auction by lot.
 * <br> {@code ENGLISH} - type of auction, based on the establishment of a minimum price as a starting price, basic for further trades, during which the requested price incrementally increases and rates are known to all participants. The final price is formed in the course of bargaining from the last maximum price offered by one of the buyers who becomes the winner.
 * @author semenovich
 *
 */
public enum AuctionType {
	ONLINE,
	ENGLISH;
}
