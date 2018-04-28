package by.tc.auction.service.auction_operation.realization.validation;

import by.tc.auction.entity.Lot;

/**
 * Class is used to validate auction lot info.
 * @author semenovich
 *
 */
public class LotInfoValidator {

	private LotInfoValidator() {}
	
	/**
	 * Validates auction lot info.
	 * <br> A lot name must be not empty.
	 * <br> A lot description must be not empty.
	 * <br> A lot quantity must be more than 0.
	 * @param lot - a lot. Only the name, the description and the quantity field must be filled in.
	 * @return {@code true} - if lot info is correct. {@code false} - if lot info is incorrect or a lot is {@code null}.
	 */
	public static boolean validate(Lot lot) {
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
