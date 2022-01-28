package by.epam.medicalweb.controller.listener;

import by.epam.medicalweb.controller.command.PagePath;
import by.epam.medicalweb.model.entity.User;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import static by.epam.medicalweb.controller.command.SessionAttribute.*;
@WebListener
public class SessionListener implements HttpSessionListener {
    private static final String DEFAULT_LOCALE = "en_US";

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(CURRENT_PAGE, PagePath.MAIN_PAGE);
        session.setAttribute(LOCALE, DEFAULT_LOCALE);
        session.setAttribute(USER_ROLE, User.UserRole.GUEST.getRoleValue());
    }
}
