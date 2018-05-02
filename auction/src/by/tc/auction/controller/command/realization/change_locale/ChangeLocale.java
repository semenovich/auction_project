package by.tc.auction.controller.command.realization.change_locale;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.tc.auction.controller.command.ServletCommand;

/**
 * A class is used to provide the user changing locale method to a controller.
 * @author semenovich
 *
 */
public class ChangeLocale implements ServletCommand {

	private static final String MAIN_PAGE = "index.jsp";
	private static final String LOCALE = "locale";

	/**
	 * Changes a user locale in a session.
	 * <br> The method expects a "locale" (a user locale) parameter. 
	 */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute(LOCALE, request.getParameter(LOCALE));

        response.sendRedirect(MAIN_PAGE);
    }
}
