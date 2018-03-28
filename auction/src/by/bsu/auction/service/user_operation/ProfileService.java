package by.bsu.auction.service.user_operation;

import by.bsu.auction.entity.AuctionsInfo;
import by.bsu.auction.entity.LotsInfo;
import by.bsu.auction.entity.User;
import by.bsu.auction.service.exception.ServiceException;
import by.bsu.auction.service.exception.UserInfoException;

public interface ProfileService {
	User getUserInfo(String login) throws ServiceException;
	LotsInfo getUserLots(String login, int page) throws ServiceException;
	LotsInfo getUserWinLots(String login, int page) throws ServiceException;
	AuctionsInfo getUserAuctionParticipations(String login, int page) throws ServiceException;
	boolean editUserInfo (User user) throws ServiceException, UserInfoException;
}
