package dao;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import by.tc.auction.dao.DAOFactory;
import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.dao.user_operation.UsersDAO;
import by.tc.auction.entity.User;

public class UsersTest {

	@Test
	public void testGetUsers() {
		UsersDAO usersDAO = DAOFactory.getInstance().getUsersDAO();
		
		try {
			ArrayList<User> users = usersDAO.getUsers();
			Assert.assertEquals(false, users.isEmpty());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetUsersByNameSearching() {
		UsersDAO usersDAO = DAOFactory.getInstance().getUsersDAO();
		
		String searchLine = "Search";
		
		try {
			ArrayList<User> users = usersDAO.getUsersBySearching(searchLine);
			Assert.assertEquals(false, users.isEmpty());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

}
