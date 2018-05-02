package by.tc.auction.controller.command.realization.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.tc.auction.controller.command.ServletCommand;

/**
 * A class is used to provide the user logout method to a controller.
 * @author semenovich
 *
 */
public class Logout implements ServletCommand {
	
	 private static final String USER_LOGIN = "userLogin";
	 private static final String USER_ROLE = "userRole";

	 private static final String MAIN_PAGE = "index.jsp";

	 /**
	  * Default constructor.
	  */
	 public Logout() {}
	 
	 /**
	  * Do a user logout from an application.
	  * Remove user data ("userLogin" and "userRole") from a session. 
	  */
	 @Override
	 public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession(false);
	        session.setAttribute(USER_LOGIN, null);
	        session.setAttribute(USER_ROLE, null);
	        response.sendRedirect(MAIN_PAGE);
	    }

}
