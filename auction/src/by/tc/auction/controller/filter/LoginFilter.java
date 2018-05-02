package by.tc.auction.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * A class is used to check is user logged on to the application.
 * <br> Used only for login and registration pages.
 * @author semenovich
 *
 */
public class LoginFilter implements Filter {

	private static final String USER_LOGIN = "userLogin";
	private static final String MAIN_PAGE = "index.jsp";
	
    public LoginFilter() {}

    /**
     * Checks is user logged on to the application.
     * <br> Used only for login and registration pages.
     * <br> The method expects a "userLogin" parameter if a user logged on.
     * <br>
     * <br> If the user is logged on, a redirect to the main page occurs.
     */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
		if (session.getAttribute(USER_LOGIN) != null) {      
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(MAIN_PAGE);  
	    }
		chain.doFilter(request, response);
	}
}
