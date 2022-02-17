package by.epam.medicalweb.controller.listener;

import by.epam.medicalweb.controller.command.PagePath;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.Specialization;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.service.SpecializationService;
import by.epam.medicalweb.model.service.impl.SpecializationServiceImpl;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.epam.medicalweb.controller.command.SessionAttribute.*;
@WebListener
public class SessionListener implements HttpSessionListener {
    private static Logger logger = LogManager.getLogger();
    private static final String DEFAULT_LOCALE = "en_US";
    private SpecializationService service = SpecializationServiceImpl.getInstance();

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(CURRENT_PAGE, PagePath.MAIN_PAGE);
        session.setAttribute(LOCALE, DEFAULT_LOCALE);
        session.setAttribute(USER_ROLE, User.UserRole.GUEST);
        List<Specialization> specializationList = null;
        try {
            specializationList = service.findAll();
        } catch (ServiceException|ConnectionPoolException e) {
            logger.log(Level.ERROR,"Specialization list is failed to be added to session", e);
        }
        session.setAttribute(SPECIALIZATION_LIST,specializationList );
    }
}
