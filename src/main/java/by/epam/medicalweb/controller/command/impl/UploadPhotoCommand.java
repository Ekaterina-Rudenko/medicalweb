package by.epam.medicalweb.controller.command.impl;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.service.DoctorService;
import by.epam.medicalweb.model.service.impl.DoctorServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static by.epam.medicalweb.controller.command.PagePath.ERROR_500;
import static by.epam.medicalweb.controller.command.RequestParameterName.*;
import static by.epam.medicalweb.controller.command.SessionAttribute.CURRENT_PAGE;

public class UploadPhotoCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private static final String ABSOLUTE_PATH = "C:/Ekaterina/medical_resource";
    private final DoctorService service = DoctorServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        try (InputStream inputStream = request.getPart(DOCTOR_PHOTO).getInputStream()) {
            String submittedFileName = request.getPart(DOCTOR_PHOTO).getSubmittedFileName();
            String path = ABSOLUTE_PATH + submittedFileName;
            Path imagePath = new File(path).toPath();
            long bytes = Files.copy(
                    inputStream,
                    imagePath,
                    StandardCopyOption.REPLACE_EXISTING);
            logger.log(Level.INFO, "Upload result is successful " + bytes + " " + path);
            String doctorId = request.getParameter(USER_ID);
            Long id = Long.parseLong(doctorId);
            if (!service.updateDoctorPhoto(path, id)) {
                logger.log(Level.ERROR, "Update doctor photo is failed");
                router.setPage(ERROR_500);
            }
        } catch (ServletException|ServiceException|IOException|ConnectionPoolException e) {
            logger.log(Level.ERROR,"Update doctor photo is failed" );
            return new Router(ERROR_500, Router.RouterType.FORWARD);
        }
        HttpSession session = request.getSession();
        String current_page = (String) session.getAttribute(CURRENT_PAGE);
        logger.log(Level.INFO, "Upload photo current page is " + current_page);
        router.setPage(current_page);
        return router;
    }
}

