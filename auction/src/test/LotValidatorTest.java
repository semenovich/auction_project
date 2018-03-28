package test;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Test;

import by.bsu.auction.entity.Lot;
import by.bsu.auction.entity.LotType;
import by.bsu.auction.service.lot_operation.realization.validation.Validator;


public class LotValidatorTest {

	@Test
	public void testValidateLotInfo() {
		Lot lot = new Lot();
		lot.setName("name");
		lot.setDescription("description");
		lot.setAdded(new Date(0L));
		lot.setOwner("owner");
		lot.setQuantity(5);
		lot.setType(LotType.ART);
		
		Lot lot2 = new Lot();
		lot2.setName("");
		lot2.setDescription("description");
		lot2.setQuantity(5);
		lot2.setType(LotType.ART);
		

		Assert.assertEquals(true, Validator.validateLotInfo(lot));
		Assert.assertEquals(false, Validator.validateLotInfo(lot2));
	}
}
