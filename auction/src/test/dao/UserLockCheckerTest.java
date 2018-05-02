package test.dao;

import org.junit.Assert;
import org.junit.Test;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.user_check_lock_filter.UserLockCheckerDAO;
import by.tc.auction.dao.user_check_lock_filter.realization.UserLockCheckerDAOImpl;

public class UserLockCheckerTest {

	@Test
	public void testIsBlocked() {
		UserLockCheckerDAO userBlockChecker = new UserLockCheckerDAOImpl();
		
		String blockedLogin = "BlockedUser";
		
		try {
			Assert.assertEquals(true, userBlockChecker.isBlocked(blockedLogin));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
