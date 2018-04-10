package by.tc.auction.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.tc.auction.controller.command.ServletCommand;
import by.tc.auction.controller.command.ServletDirector;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 7976819909277626395L;

	private static final String COMMAND_PARAMETER = "command";
	
	private static final String LOCALE = "locale";
	private static final String DEFAULT_LOCALE = "en"; 
	
	private static final String ERROR_PAGE = "error.jsp";
	
	private static final Logger logger = Logger.getLogger(FrontController.class);
	
	private ServletDirector servletDirector;
	
    public FrontController() {
        super();
        servletDirector = ServletDirector.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{   
			HttpSession session = request.getSession(true);
			if (session.getAttribute(LOCALE) == null) {
				session.setAttribute(LOCALE, DEFAULT_LOCALE);
			}
			changeCommand(request, response);
		} catch (Exception e) {
	    	logger.error("Error in FrontController", e);
	    	response.sendRedirect(ERROR_PAGE);
	    	return;
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void changeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String commandName = request.getParameter(COMMAND_PARAMETER);
	    ServletCommand command = servletDirector.getCommand(commandName);
	    if (command == null) {
	      	response.sendRedirect(ERROR_PAGE);
	      	return;
	    }
	    command.execute(request, response);
    }

}
