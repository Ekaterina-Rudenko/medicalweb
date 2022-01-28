package by.epam.medicalweb.controller;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.CommandType;
import by.epam.medicalweb.controller.command.Router;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/uploadController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 16, maxFileSize = 1024 * 1024 * 16, maxRequestSize = 1024 * 1024 * 10 * 10)
public class UploadController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Command command = CommandType.UPLOAD_PHOTO.getCommand();
        Router router = command.execute(request);
        request.getRequestDispatcher(router.getPage()).forward(request, response);
    }
}
