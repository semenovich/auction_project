package by.bsu.auction.dao.user_check_block_filter;

import by.bsu.auction.dao.exception.DAOException;

public interface UserBlockCheckerDAO {
	boolean isBlocked(String login) throws DAOException;
}
