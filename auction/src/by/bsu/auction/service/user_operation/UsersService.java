package by.bsu.auction.service.user_operation;

import by.bsu.auction.entity.UsersInfo;
import by.bsu.auction.service.exception.ServiceException;

public interface UsersService {
	UsersInfo getUsers(int page) throws ServiceException;
	UsersInfo getUsersByNameSearch(String searchLine, int page) throws ServiceException;
}
