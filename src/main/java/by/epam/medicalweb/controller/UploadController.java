package by.epam.medicalweb.controller;

import by.epam.medicalweb.controller.command.RequestParameterName;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/uploadController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 5, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5)
public class UploadController extends HttpServlet {
    private static final String IMAGE_TYPE = "image/jpeg";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getParameter(RequestParameterName.IMAGE_PATH);
        byte[] imageBytes = Files.readAllBytes(Paths.get(path));
        response.setContentType(IMAGE_TYPE);
        response.setContentLength(imageBytes.length);
        response.getOutputStream().write(imageBytes);
    }
}
