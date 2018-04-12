package by.tc.auction.service.user_operation;

import by.tc.auction.entity.AuctionsInfo;
import by.tc.auction.entity.LotsInfo;
import by.tc.auction.entity.User;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.exception.UserInfoException;

public interface ProfileService {
	User getUserInfo(String login) throws ServiceException;
	LotsInfo getUserLots(String login, int page) throws ServiceException;
	LotsInfo getUserWinLots(String login, int page) throws ServiceException;
	AuctionsInfo getUserAuctionParticipations(String login, int page) throws ServiceException;
	boolean editUserInfo (User user) throws ServiceException, UserInfoException;
	boolean uploadUserImage (String userLogin, String imagePath) throws ServiceException;
}
