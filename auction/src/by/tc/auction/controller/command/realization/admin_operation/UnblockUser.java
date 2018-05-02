package by.tc.auction.controller.command.realization.admin_operation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.tc.auction.controller.command.ServletCommand;
import by.tc.auction.entity.UserRole;
import by.tc.auction.service.ServiceFactory;
import by.tc.auction.service.admin_operation.AdminOperationService;
import by.tc.auction.service.exception.ServiceException;

/**
 * A class is used to provide the unblock a user method to a controller.
 * @author semenovich
 *
 */
public class UnblockUser implements ServletCommand {

	private static final Logger logger = Logger.getLogger(UnblockUser.class);
	
	private static final String USER_LOGIN = "userLogin";
	
	private static final String USER_ROLE = "userRole";
	
	private static final String SUCCESSFUL_PAGE = "FrontController?command=GET_USER_INFO&userLogin=";
	private static final String ERROR_PAGE = "error.jsp";	
	private static final String ACCESS_DENIED_PAGE = "access-denied.jsp";

	private AdminOperationService service;
	
	/**
	 * Default constructor. 
	 */
	public UnblockUser() {
		ServiceFactory factory = ServiceFactory.getInstance();
		service = factory.getAdminOperationService();
	}

	/**
	 * Unblocks a user in an application.
	 * <br> The method expects "userLogin" and "userRole" parameters with the values.
	 * <br> 
	 * <br> In the event of an error, a redirect to the error page occurs.
	 * <br> If a user doesn't have administrator privileges, a redirect to the access denied page occurs.
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (request.getSession().getAttribute(USER_ROLE) != UserRole.ADMIN) {
				response.sendRedirect(ACCESS_DENIED_PAGE);
				return;
			}
			String userLogin = request.getParameter(USER_LOGIN);
			if (service.unblockUser(userLogin)) {
				response.sendRedirect(SUCCESSFUL_PAGE + userLogin);	
			}
			else {
				response.sendRedirect(ERROR_PAGE);
			}
		} catch (ServiceException e) {
			logger.error("Error in UnblockUser", e);
			response.sendRedirect(ERROR_PAGE);
		}
	}

}
