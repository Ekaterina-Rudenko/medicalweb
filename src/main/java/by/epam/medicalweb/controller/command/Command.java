package by.epam.medicalweb.controller.command;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface Command {
    Router execute(HttpServletRequest request)
        throws ServletException, ConnectionPoolException, ServiceException;
}
