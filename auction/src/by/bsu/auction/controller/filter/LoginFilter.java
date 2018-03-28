package by.bsu.auction.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	private static final String USER_LOGIN = "userLogin";
	private static final String MAIN_PAGE = "index.jsp";
	
    public LoginFilter() {}

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
