package dao;

import org.junit.Assert;
import org.junit.Test;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.user_check_block_filter.UserBlockCheckerDAO;
import by.tc.auction.dao.user_check_block_filter.realization.UserBlockCheckerDAOImpl;

public class UserBlockCheckerTest {

	@Test
	public void testIsBlocked() {
		UserBlockCheckerDAO userBlockChecker = new UserBlockCheckerDAOImpl();
		
		String blockedLogin = "BlockedUser";
		
		try {
			Assert.assertEquals(true, userBlockChecker.isBlocked(blockedLogin));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
