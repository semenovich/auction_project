package by.tc.auction.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * An interface provides method to execute controller commands.
 * @author semenovich
 *
 */
public interface ServletCommand {
	/**
	 * Executes a command.
	 * @param request - a request with the command required parameters
	 * @param response - a response of a command.
	 * @throws ServletException - if an error occurred during operation.
	 * @throws IOException - if an IO error occurred during operation.
	 */
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
