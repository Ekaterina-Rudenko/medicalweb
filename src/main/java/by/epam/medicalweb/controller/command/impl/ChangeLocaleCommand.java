package by.epam.medicalweb.controller.command.impl;

import by.epam.medicalweb.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeLocaleCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private static final String LOCALE_ENGLISH = "en";
    private static final String LOCALE_RUSSIAN = "ru";
/*
    private static final String LOCALE_RUSSIAN = "ru_RU";
    private static final String LOCALE_ENGLISH = "en_US";
*/

    @Override
    public Router execute(HttpServletRequest request) {
        String locale = request.getParameter(RequestParameterName.LOCALE);
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.LOCALE, locale);
        if (session.getAttribute(SessionAttribute.CURRENT_PAGE) == null) {
            return new Router(PagePath.MAIN_PAGE, Router.RouterType.FORWARD);
        }

        return new Router((String) session.getAttribute(SessionAttribute.CURRENT_PAGE), Router.RouterType.FORWARD);
    }
}
