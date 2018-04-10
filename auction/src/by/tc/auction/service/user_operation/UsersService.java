package by.tc.auction.service.user_operation;

import by.tc.auction.entity.UsersInfo;
import by.tc.auction.service.exception.ServiceException;

public interface UsersService {
	UsersInfo getUsers(int page) throws ServiceException;
	UsersInfo getUsersByNameSearch(String searchLine, int page) throws ServiceException;
}
