package by.bsu.auction.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.bsu.auction.service.exception.ServiceException;
import by.bsu.auction.service.user_check_block_filter.UserBlockCheckerService;
import by.bsu.auction.service.user_check_block_filter.realization.UserBlockCheckerServiceImpl;

public class BlockFilter implements Filter {

	private static final String USER_LOGIN = "userLogin";
	private static final String USER_ROLE = "userRole";
	private static final String BLOCKED_PAGE = "blocked.jsp";
	private static final String ERROR_PAGE = "error.jsp";
	
	private UserBlockCheckerService service = new UserBlockCheckerServiceImpl();
	
    public BlockFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
		try {
			if (session.getAttribute(USER_LOGIN) != null && service.isBlocked((String)session.getAttribute(USER_LOGIN))) { 
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				session.setAttribute(USER_LOGIN, null);
		        session.setAttribute(USER_ROLE, null);
				httpResponse.sendRedirect(BLOCKED_PAGE);  
			}
		} catch (ServiceException e) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(ERROR_PAGE);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
