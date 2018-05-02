package test.validation;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Test;

import by.tc.auction.entity.Lot;
import by.tc.auction.entity.LotType;
import by.tc.auction.service.lot_operation.realization.validation.LotInfoValidator;


public class LotValidatorTest {

	@Test
	public void testValidateLotInfo() {
		Lot lot = new Lot();
		lot.setName("name");
		lot.setDescription("description");
		lot.setAdded(new Timestamp(0L));
		lot.setOwner("owner");
		lot.setQuantity(5);
		lot.setType(LotType.ART);
		
		Lot lot2 = new Lot();
		lot2.setName("");
		lot2.setDescription("description");
		lot2.setQuantity(5);
		lot2.setType(LotType.ART);
		

		Assert.assertEquals(true, LotInfoValidator.validate(lot));
		Assert.assertEquals(false, LotInfoValidator.validate(lot2));
	}
}
