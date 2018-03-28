package by.bsu.auction.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.bsu.auction.controller.command.ServletCommand;
import by.bsu.auction.controller.command.ServletDirector;
import by.bsu.auction.controller.command.ServletList;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;
		
	private static final String COMMAND_PARAMETER = "command";
	
	private ServletDirector servletDirector;
	
    public FrontController() {
        super();
        servletDirector = ServletDirector.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		changeCommand(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void changeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ServletList commandName = ServletList.valueOf(request.getParameter(COMMAND_PARAMETER));
        ServletCommand command = servletDirector.getCommand(commandName);
        command.execute(request, response);
    }

}
