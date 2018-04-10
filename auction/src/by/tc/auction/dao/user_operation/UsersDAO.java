package by.tc.auction.dao.user_operation;

import java.util.ArrayList;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.User;

public interface UsersDAO {
	ArrayList<User> getUsers() throws DAOException;
	ArrayList<User> getUsersByNameSearching(String searchLine) throws DAOException;
}
