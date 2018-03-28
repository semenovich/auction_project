package by.bsu.auction.service.user_check_block_filter;

import by.bsu.auction.service.exception.ServiceException;

public interface UserBlockCheckerService {
	boolean isBlocked(String login) throws ServiceException;
}
