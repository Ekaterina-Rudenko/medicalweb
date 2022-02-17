package by.epam.medicalweb.controller.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface Command {
    Router execute(HttpServletRequest request) throws IOException, ServletException;
}
