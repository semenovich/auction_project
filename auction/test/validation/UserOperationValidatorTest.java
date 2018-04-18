package validation;

import org.junit.Assert;
import org.junit.Test;

import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Bet;
import by.tc.auction.entity.User;
import by.tc.auction.service.user_operation.realization.validation.UserBetValidator;
import by.tc.auction.service.user_operation.realization.validation.UserEditingValidator;

public class UserOperationValidatorTest {

	@Test
	public void testValidateUserEditing() {
		User user = new User();
		user.setLogin("login");
		user.setSurname("surname");
		user.setName("name");
		user.setEmail("q@q.ru");
		user.setCountry("Belarus");
		user.setPassportId("passportId");
		user.setPassportIssuedBy("passportIssuedBy");
		user.setPassword("password");
		
		User user2 = new User();
		user2.setLogin("login");
		user2.setSurname("");
		user2.setName("name");
		user2.setEmail("q@q.ru");
		user2.setCountry("Belarus");
		user2.setPassportId("passportId");
		user2.setPassportIssuedBy("passportIssuedBy");
		user2.setPassword("password");
		
		Assert.assertEquals(false, UserEditingValidator.validate(user2));
	}

	@Test
	public void testValidateUserBet() {
		double betDifference = 1;
		Bet bet = new Bet();
		Bet minBet = new Bet();
		Bet currentBet = new Bet();
		Auction auction = new Auction();
		bet.setValue(14D);
		minBet.setValue(13D);
		currentBet.setValue(0D);
		auction.setLastBet(currentBet);
		auction.setMinBet(minBet);
		
		Assert.assertEquals(true, UserBetValidator.validate(auction, bet, betDifference));

		Bet newCurrentBet = new Bet();
		newCurrentBet.setValue(15D);
		auction.setLastBet(newCurrentBet);
		
		Assert.assertEquals(false, UserBetValidator.validate(auction, bet, betDifference));
	}

}
