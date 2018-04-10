package by.tc.auction.service.user_check_block_filter;

import by.tc.auction.service.exception.ServiceException;

public interface UserBlockCheckerService {
	boolean isBlocked(String login) throws ServiceException;
}
