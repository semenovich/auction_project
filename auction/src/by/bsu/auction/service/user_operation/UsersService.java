package by.bsu.auction.service.user_operation;

import by.bsu.auction.service.exception.ServiceException;
import by.tc.auction.entity.UsersInfo;

public interface UsersService {
	UsersInfo getUsers(int page) throws ServiceException;
	UsersInfo getUsersByNameSearch(String searchLine, int page) throws ServiceException;
}
