package by.tc.auction.service.lot_operation.realization.util;

import java.util.ArrayList;

import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotsInfo;

public class LotPortionGetter {

	private static final int LOT_PORTION_QUANTITY = 10;
	
	private static final LotPortionGetter instance = new LotPortionGetter();
	
	private LotPortionGetter() {}
	
	public static LotPortionGetter getInstance() {
		return instance;
	}
	
	public LotsInfo getLotsPortion(ArrayList<Lot> userLots, int page){
		if (userLots == null) {
			return null;
		}
		ArrayList<Lot> returnLots = new ArrayList<>();
		LotsInfo lotsInfo = new LotsInfo();
		for (int i = (page - 1) * LOT_PORTION_QUANTITY; i < userLots.size() && i < page * LOT_PORTION_QUANTITY; i++) {
			returnLots.add(userLots.get(i));
		}
		lotsInfo.setLots(returnLots);
		lotsInfo.setCurrentPage(page);
		lotsInfo.setPages((int) Math.ceil(((double) userLots.size()) / LOT_PORTION_QUANTITY));
		return lotsInfo;
	}
}
