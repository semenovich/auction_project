package by.tc.auction.entity;

/**
 * A list of an auction status.
 * <br> {@code PENDING_PAYMENT} - an auction is waiting payment for an acution lot.
 * <br> {@code COMPLETED} - an auction is completed.
 * <br> {@code ACTIVE} - an auction is in active.
 * @author semenovich
 *
 */
public enum AuctionStatus {
	PENDING_PAYMENT, 
	COMPLETED, 
	ACTIVE;
}
