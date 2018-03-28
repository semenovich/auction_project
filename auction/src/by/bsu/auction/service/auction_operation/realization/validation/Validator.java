package by.bsu.auction.service.auction_operation.realization.validation;

import by.bsu.auction.entity.Lot;

public class Validator {

	private Validator() {}
	
	public static boolean validateLotInfo(Lot lot) {
		if (lot == null) {
			return false;
		}
		if (lot.getName().isEmpty()) {
			return false;
		}
		if (lot.getDescription().isEmpty()) {
			return false;
		}
		if (lot.getQuantity() <= 0) {
			return false;
		}
		return true;
	}

}
