package by.tc.auction.service.authentication;

import by.tc.auction.entity.User;
import by.tc.auction.service.exception.ServiceException;
import by.tc.auction.service.exception.UserInfoException;

public interface AuthService {
	User register(User user) throws ServiceException, UserInfoException;
	User login(String login, String password) throws ServiceException, UserInfoException;
}
