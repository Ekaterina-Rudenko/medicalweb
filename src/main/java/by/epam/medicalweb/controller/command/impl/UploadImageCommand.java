package by.epam.medicalweb.controller.command.impl;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.model.service.DoctorService;
import by.epam.medicalweb.model.service.impl.DoctorServiceImpl;
import by.epam.medicalweb.util.ImageUploader;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;

import static by.epam.medicalweb.model.dao.ColumnName.USER_ID;


public class UploadImageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private final DoctorService doctorService = DoctorServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws IOException, ServletException {
        Router router = new Router();
        Part part = request.getPart("IMAGE_PATH");
        String imagePath = ImageUploader.uploadImage(part);
        String userId = request.getParameter(USER_ID);
        //todo

        return router;
    }
}
