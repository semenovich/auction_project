package by.tc.auction.service.user_operation.realization.validation;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Bet;

/**
 * A class is used to validate an auction bet.
 * @author semenovich
 *
 */
public class UserBetValidator {
	
	private UserBetValidator() {}
	
	/**
	 * Validates an auction bet (by checking MIN and current auction bet). 
	 * A bet must be greater than MIN bet (and than auction current bet by {@code minBetDifference}).
	 * @param auction - an auction in which bet will be placed.
	 * @param userBet - a bet.
	 * @param minBetDifference - a necessary difference between a current auction bet and a user bet. 
	 * @return {@code true} - if a bet is correct. {@code false} - if a bet is incorrect.
	 */
	public static boolean validate(Auction auction, Bet userBet, double minBetDifference) {
		if (userBet.getValue() < auction.getMinBet().getValue()) {
			return false;
		}
		if (auction.getLastBet().getValue() != 0 && userBet.getValue() < auction.getLastBet().getValue() + minBetDifference) {
			return false;
		}
		return true;
	}
}
