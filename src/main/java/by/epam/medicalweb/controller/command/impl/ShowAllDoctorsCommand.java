package by.epam.medicalweb.controller.command.impl;

import by.epam.medicalweb.controller.Command;
import by.epam.medicalweb.controller.Router;
import jakarta.servlet.http.HttpServletRequest;

public class ShowAllDoctorsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}
