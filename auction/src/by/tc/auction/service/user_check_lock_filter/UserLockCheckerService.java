package by.tc.auction.service.user_check_lock_filter;

import by.tc.auction.service.exception.ServiceException;

public interface UserLockCheckerService {
	boolean isBlocked(String login) throws ServiceException;
}
