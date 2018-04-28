package by.tc.auction.service.lot_operation.realization.util;

import java.util.ArrayList;

import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotsInfo;

/**
 * A class is used to get a list of lots portion from a list of lots.
 * @author semenovich
 *
 */
public class LotPortionGetter {

	private static final int LOT_PORTION_QUANTITY = 10;
	
	private static final LotPortionGetter instance = new LotPortionGetter();
	
	/**
	 * Default constructor.
	 */
	private LotPortionGetter() {}
	
	/**
	 * Returns the instance of the LotPortionGetter.
	 * @return the instance of the LotPortionGetter.
	 */
	public static LotPortionGetter getInstance() {
		return instance;
	}
	
	/**
	 * Returns a list of 10(<= if lots in portion are less than 10) lots from a list of lots.
	 * @param lots - a list of lots.
	 * @param page - a page of portion.
	 * @return A list of 10(<= if lots in portion are less than 10) lots. {@code null} if a lots list is null.
	 */
	public LotsInfo getLotsPortion(ArrayList<Lot> lots, int page){
		if (lots == null) {
			return null;
		}
		ArrayList<Lot> returnLots = new ArrayList<>();
		LotsInfo lotsInfo = new LotsInfo();
		for (int i = (page - 1) * LOT_PORTION_QUANTITY; i < lots.size() && i < page * LOT_PORTION_QUANTITY; i++) {
			returnLots.add(lots.get(i));
		}
		lotsInfo.setLots(returnLots);
		lotsInfo.setCurrentPage(page);
		lotsInfo.setPages((int) Math.ceil(((double) lots.size()) / LOT_PORTION_QUANTITY));
		return lotsInfo;
	}
}
