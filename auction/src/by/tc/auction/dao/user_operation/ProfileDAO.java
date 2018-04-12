package by.tc.auction.dao.user_operation;

import java.util.ArrayList;

import by.tc.auction.dao.exception.DAOException;
import by.tc.auction.entity.Auction;
import by.tc.auction.entity.Lot;
import by.tc.auction.entity.User;

public interface ProfileDAO {
	User getUserInfo(String login) throws DAOException;
	ArrayList<Lot> getUserLots(String login) throws DAOException;
	ArrayList<Lot> getUserWinLots(String login) throws DAOException;
	ArrayList<Auction> getUserAuctionParticipation(String login) throws DAOException;
	boolean editUserInfo (User user) throws DAOException;
	boolean uploadUserImage(String userLogin, String imagePath) throws DAOException;
}
