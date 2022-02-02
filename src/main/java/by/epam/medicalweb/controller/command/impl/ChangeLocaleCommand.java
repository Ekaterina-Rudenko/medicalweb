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
        Router router = new Router();
        String locale = request.getParameter(RequestParameterName.LOCALE);
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        session.setAttribute(SessionAttribute.LOCALE, locale);

        if (currentPage == null) {
            router.setPage(PagePath.MAIN_PAGE);
            router.setRouterType(Router.RouterType.FORWARD);
            return router;
        }
        router.setPage(currentPage);
        router.setRouterType(Router.RouterType.FORWARD);
        return router;
    }
}
