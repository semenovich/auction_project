package by.tc.auction.dao.user_check_block_filter;

import by.tc.auction.dao.exception.DAOException;

public interface UserBlockCheckerDAO {
	boolean isBlocked(String login) throws DAOException;
}
