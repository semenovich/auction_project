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
 * A class is used to check if the user is authenticated on to the application.
 * @author semenovich
 *
 */
public class AuthFilter implements Filter {

	private static final String USER_LOGIN = "userLogin";
	private static final String REGISTER_PAGE = "register.jsp";
	private static final String LOGIN_PAGE = "login.jsp";

	/**
	 * Check if the user is authenticated on to the application.
	 * <br> The method expects a "userLogin" parameter (from a session) with a value.
	 * <br> 
	 * <br> If the user isn't authenticated on and doesn't go on a login or a registration page, the redirect to the login page occurs.  
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        
        String uriPath = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        
        if (session == null || session.getAttribute(USER_LOGIN) == null) {          
            HttpServletResponse httpResponse = (HttpServletResponse) response;  

            if (LOGIN_PAGE.equals(uriPath) ||  REGISTER_PAGE.equals(uriPath)) {
                chain.doFilter(request, response);  
            }            
            httpResponse.sendRedirect(LOGIN_PAGE + "?destination=" + uriPath);
        } 
		chain.doFilter(request, response);
	}
}
