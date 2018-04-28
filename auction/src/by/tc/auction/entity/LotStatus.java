package by.tc.auction.entity;

/**
 * A list of a lot status.
 * <br> {@code BLOCKED} - a lot is blocked.
 * <br> {@code SOLED} - a lot is soled.
 * <br> {@code CONFIRMING} - a lot is in confirming.
 * <br> {@code ACTIVE} - a lot is in active (used in an auction).
 * <br> {@code READY} - a lot is ready to use in an auction.
 * @author semenovich
 *
 */
public enum LotStatus {
	BLOCKED,
	SOLED,
	CONFIRMING,
	ACTIVE,
	READY;
}
