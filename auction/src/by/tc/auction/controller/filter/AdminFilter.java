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

import by.tc.auction.entity.UserRole;

/**
 * A class is used to check if the user has the administrator role.
 * @author semenovich
 *
 */
public class AdminFilter implements Filter {

	private static final String USER_ROLE = "userRole";
	
	private static final String ACCESS_DENIED_PAGE = "access-denied,jsp";
	
	/**
	 * Check if the user has the administrator role.
	 * <br> The method expects a "userRole" parameter (from a session) with a value.
	 * <br>
	 * <br> If the user hasn't the administrator role, a redirect to the access denied page occurs. 
	 */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
		if (!UserRole.ADMIN.equals((UserRole) session.getAttribute(USER_ROLE))) {      
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(ACCESS_DENIED_PAGE);  
	    }		
		chain.doFilter(request, response);
	}
}
