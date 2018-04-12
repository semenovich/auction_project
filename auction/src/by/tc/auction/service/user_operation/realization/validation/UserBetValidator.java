package by.tc.auction.service.user_operation.realization.validation;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Bet;

public class UserBetValidator {
	
	private UserBetValidator() {}
	
	public static boolean validate(Auction auction, Bet userBet, double minBetDifference) {
		if (userBet.getValue() < auction.getMinBet().getValue() || userBet.getValue() < auction.getLastBet().getValue() + minBetDifference) {
			return false;
		}
		return true;
	}
}
