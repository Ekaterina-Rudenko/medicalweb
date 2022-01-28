package by.epam.medicalweb.controller.command;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request);
}
