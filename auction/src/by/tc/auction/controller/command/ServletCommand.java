package by.tc.auction.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServletCommand {
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
