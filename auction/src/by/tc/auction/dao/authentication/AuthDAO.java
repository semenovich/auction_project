package by.tc.auction.dao.authentication;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.User;

public interface AuthDAO {
	boolean register(User user) throws DAOException;
	User login(String login, String password) throws DAOException;
}
