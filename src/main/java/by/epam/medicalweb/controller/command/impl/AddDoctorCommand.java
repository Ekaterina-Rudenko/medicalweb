package by.epam.medicalweb.controller.command.impl;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.service.DoctorService;
import by.epam.medicalweb.model.service.SpecializationService;
import by.epam.medicalweb.model.service.impl.DoctorServiceImpl;
import by.epam.medicalweb.model.service.impl.SpecializationServiceImpl;
import by.epam.medicalweb.util.ImageUploader;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static by.epam.medicalweb.controller.command.ErrorMessagesBundle.*;
import static by.epam.medicalweb.controller.command.PagePath.MAIN_PAGE;
import static by.epam.medicalweb.controller.command.RequestParameterName.*;
import static by.epam.medicalweb.controller.command.RequestParameterName.REPEATED_PASSWORD;

public class AddDoctorCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private DoctorService doctorService = DoctorServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request){
        Router router = new Router();
        Map<String, String> mapData = new HashMap<>();
        mapData.put(FIRST_NAME, request.getParameter(FIRST_NAME));
        mapData.put(MIDDLE_NAME, request.getParameter(MIDDLE_NAME));
        mapData.put(LAST_NAME, request.getParameter(LAST_NAME));
        mapData.put(LOGIN, request.getParameter(LOGIN));
        mapData.put(PASSWORD, request.getParameter(PASSWORD));
        mapData.put(EMAIL, request.getParameter(EMAIL));
        mapData.put(PHONE, request.getParameter(PHONE));
        mapData.put(CATEGORY, request.getParameter(CATEGORY));
        mapData.put(IMAGE_PATH, request.getParameter(IMAGE_PATH));
        mapData.put(SPECIALIZATION_ID, request.getParameter(SPECIALIZATION_ID));
        mapData.put(REPEATED_PASSWORD, request.getParameter(REPEATED_PASSWORD));

        try {
            Part part = request.getPart(IMAGE_PATH);
            String imagePath = ImageUploader.uploadImage(part);
            mapData.put(IMAGE_PATH, imagePath);
            if (doctorService.addDoctor(mapData)) {
                router.setPage(MAIN_PAGE);
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
                        case NOT_UNIQUE_EMAIL -> request.setAttribute(INVALID_EMAIL, NOT_UNIQUE_EMAIL_MESSAGE);
                        case NOT_UNIQUE_LOGIN -> request.setAttribute(INVALID_LOGIN, NOT_UNIQUE_LOGIN_MESSAGE);
                        case INVALID_REPEATED_PASSWORD -> request.setAttribute(INVALID_REPEATED_PASSWORD, INVALID_REPEATED_PASSWORD_MESSAGE);
                        case INVALID_IMAGE_PATH -> request.setAttribute(INVALID_IMAGE_PATH, INVALID_IMAGE_PATH_MESSAGE);
                    }
                }
            }
        } catch (ServiceException|ConnectionPoolException e) {
            logger.log(Level.ERROR, "Failed to register new doctor, exception in add doctor command ", e);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        return router;
    }
}
