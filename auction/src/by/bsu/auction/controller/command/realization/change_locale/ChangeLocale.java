package by.bsu.auction.controller.command.realization.change_locale;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.bsu.auction.controller.command.ServletCommand;

public class ChangeLocale implements ServletCommand {

	private static final String MAIN_PAGE = "index.jsp";
	private static final String LOCALE = "locale";
    private static final String LANGUAGE = "language";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute(LOCALE, request.getParameter(LOCALE));
        session.setAttribute(LANGUAGE, request.getParameter(LANGUAGE));

        response.sendRedirect(MAIN_PAGE);
    }
}
