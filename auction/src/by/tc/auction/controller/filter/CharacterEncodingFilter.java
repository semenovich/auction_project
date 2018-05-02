package by.tc.auction.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * A class is used to set a request and a response encoding, when a user does a request to a server.  
 */
public class CharacterEncodingFilter implements Filter {
    
	private static final String PARAMETER_ENCODING = "encoding";

    private String encoding;

    /**
     * Initializes the encoding parameter value from the configuration.
     */
    @Override
    public void init(FilterConfig config){
        encoding = config.getInitParameter(PARAMETER_ENCODING);
    }

    /**
     * Sets a request and a response encoding.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);

        chain.doFilter(request, response);
    }
}
