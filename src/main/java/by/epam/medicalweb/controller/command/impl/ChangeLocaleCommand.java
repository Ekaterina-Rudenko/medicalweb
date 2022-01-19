package by.epam.medicalweb.controller.command.impl;

import by.epam.medicalweb.controller.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.medicalweb.controller.SessionAttribute.LANGUAGE;

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
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        String locale = request.getParameter(RequestParameterName.LOCALE);
        logger.log(Level.DEBUG, "Locale parameter is " + locale);
        if (locale != null && locale.matches(LOCALE_ENGLISH + "|" + LOCALE_RUSSIAN)) {
            session.setAttribute(SessionAttribute.LOCALE, locale);
        } else {
            logger.log(Level.DEBUG, "Wrong locale parameter: " + locale);
        }

        router.setPage(currentPage);
        return router;



       /* switch (language) {
            case LANGUAGE_ENGLISH -> session.setAttribute(SessionAttribute.LOCALE, LOCALE_ENGLISH);
            case LANGUAGE_RUSSIAN -> session.setAttribute(SessionAttribute.LOCALE, LOCALE_RUSSIAN);
        }
        session.setAttribute(LANGUAGE, language );
        return new Router((String) session.getAttribute(SessionAttribute.CURRENT_PAGE), Router.RouterType.FORWARD);*/
    }
}
