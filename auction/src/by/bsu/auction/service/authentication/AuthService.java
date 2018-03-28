package by.bsu.auction.service.authentication;

import by.bsu.auction.entity.User;
import by.bsu.auction.service.exception.ServiceException;
import by.bsu.auction.service.exception.UserInfoException;

public interface AuthService {
	User register(User user) throws ServiceException, UserInfoException;
	User login(String login, String password) throws ServiceException, UserInfoException;
}
