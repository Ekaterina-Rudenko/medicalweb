package by.epam.medicalweb.controller.command.impl;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.service.DoctorService;
import by.epam.medicalweb.model.service.impl.DoctorServiceImpl;
import by.epam.medicalweb.util.ImageUploader;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;

import static by.epam.medicalweb.controller.command.PagePath.ERROR_500;
import static by.epam.medicalweb.controller.command.SessionAttribute.CURRENT_PAGE;
import static by.epam.medicalweb.model.dao.ColumnName.USER_ID;


public class UploadImageCommand implements Command {
    private static Logger logger = LogManager.getLogger();
    private final DoctorService doctorService = DoctorServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException {
        Router router = new Router();
        Part part = null;
        try {
            part = request.getPart("IMAGE_PATH");
            String imagePath = ImageUploader.uploadImage(part);
            String userIdString = request.getParameter(USER_ID);
            long doctorId = Long.parseLong(userIdString);
            if(!doctorService.updateDoctorPhoto(imagePath, doctorId)){
                logger.log(Level.ERROR, "Photo for doctor wasn't updated");
                router.setPage(ERROR_500);
                return router;
            }
        } catch (IOException | ServiceException| ConnectionPoolException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        String current_page = (String) session.getAttribute(CURRENT_PAGE);
        logger.log(Level.INFO,"Photo uploaded successfully, current page: " + current_page);
        router.setPage(current_page);

        return router;
    }
}
