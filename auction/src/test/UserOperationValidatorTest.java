package test;

import org.junit.Assert;
import org.junit.Test;

import by.bsu.auction.entity.Auction;
import by.bsu.auction.entity.Bet;
import by.bsu.auction.entity.User;
import by.bsu.auction.service.user_operation.realization.validation.Validator;

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
		
		Assert.assertEquals(false, Validator.validateUserEditing(user2));
	}

	@Test
	public void testValidateUserBet() {
		Bet bet = new Bet();
		Bet minBet = new Bet();
		Bet currentBet = new Bet();
		Auction auction = new Auction();
		bet.setValue(14D);
		minBet.setValue(13D);
		currentBet.setValue(0D);
		auction.setCurrentBet(currentBet);
		auction.setMinBet(minBet);
		
		Assert.assertEquals(true, Validator.validateUserBet(auction, bet));

		Bet newCurrentBet = new Bet();
		newCurrentBet.setValue(15D);
		auction.setCurrentBet(newCurrentBet);
		
		Assert.assertEquals(false, Validator.validateUserBet(auction, bet));
	}

}
