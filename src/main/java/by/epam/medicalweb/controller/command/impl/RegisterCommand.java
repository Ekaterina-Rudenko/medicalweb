package by.epam.medicalweb.controller.command.impl;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.service.PatientService;
import by.epam.medicalweb.model.service.impl.PatientServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static by.epam.medicalweb.controller.command.ErrorMessagesBundle.*;
import static by.epam.medicalweb.controller.command.PagePath.LOG_IN_PAGE;
import static by.epam.medicalweb.controller.command.PagePath.REGISTRATION_PAGE;
import static by.epam.medicalweb.controller.command.RequestParameterName.*;
import static by.epam.medicalweb.controller.command.SessionAttribute.CURRENT_PAGE;

public class RegisterCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private PatientService patientService = PatientServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        Map<String, String> mapData = new HashMap<>();
        mapData.put(FIRST_NAME, request.getParameter(FIRST_NAME));
        mapData.put(MIDDLE_NAME, request.getParameter(MIDDLE_NAME));
        mapData.put(LAST_NAME, request.getParameter(LAST_NAME));
        mapData.put(LOGIN, request.getParameter(LOGIN));
        mapData.put(PASSWORD, request.getParameter(PASSWORD));
        mapData.put(EMAIL, request.getParameter(EMAIL));
        mapData.put(PHONE, request.getParameter(PHONE));
        mapData.put(GENDER, request.getParameter(GENDER));
        mapData.put(BIRTHDATE, request.getParameter(BIRTHDATE));
        mapData.put(REPEATED_PASSWORD, request.getParameter(REPEATED_PASSWORD));
        try {
            HttpSession session = request.getSession();
            User.UserRole role = (User.UserRole) session.getAttribute(USER_ROLE);
            if (patientService.registerPatient(mapData)) {
                router.setRouterType(Router.RouterType.REDIRECT);
                if(role == User.UserRole.ADMIN){
                    String currentPage = (String) session.getAttribute(CURRENT_PAGE);
                    router.setPage(currentPage);
                }else {
                    router.setPage(LOG_IN_PAGE);
                }
            } else {
                for (String key : mapData.keySet()) {
                    String currentValue = mapData.get(key);
                    switch (currentValue) {
                        case INVALID_FIRST_NAME -> request.setAttribute(INVALID_FIRST_NAME, INVALID_FIRST_NAME_MESSAGE);
                        case INVALID_MIDDLE_NAME -> request.setAttribute(INVALID_MIDDLE_NAME, INVALID_MIDDLE_NAME_MESSAGE);
                        case INVALID_LAST_NAME -> request.setAttribute(INVALID_LAST_NAME, INVALID_LAST_NAME_MESSAGE);
                        case INVALID_LOGIN -> request.setAttribute(INVALID_LOGIN, INVALID_LOGIN_MESSAGE);
                        case INVALID_PASSWORD -> request.setAttribute(INVALID_PASSWORD, INVALID_PASSWORD_MESSAGE);
                        case INVALID_EMAIL -> request.setAttribute(INVALID_EMAIL, INVALID_EMAIL_MESSAGE);
                        case INVALID_PHONE_NUMBER -> request.setAttribute(INVALID_PHONE_NUMBER, INVALID_PHONE_NUMBER_MESSAGE);
                        case INVALID_BIRTHDATE -> request.setAttribute(INVALID_BIRTHDATE, INVALID_BIRTHDATE_MESSAGE);
                        case NOT_UNIQUE_EMAIL -> request.setAttribute(INVALID_EMAIL, NOT_UNIQUE_EMAIL_MESSAGE);
                        case NOT_UNIQUE_LOGIN -> request.setAttribute(INVALID_LOGIN, NOT_UNIQUE_LOGIN_MESSAGE);
                        case INVALID_REPEATED_PASSWORD -> request.setAttribute(INVALID_REPEATED_PASSWORD, INVALID_REPEATED_PASSWORD_MESSAGE);
                    }
                }
                router.setPage(REGISTRATION_PAGE);
            }
        } catch (ServiceException|ConnectionPoolException e) {
            logger.log(Level.ERROR, "Failed to register new patient, exception in register command ", e);
        }
        return router;
    }
}
