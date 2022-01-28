package by.epam.medicalweb.controller.command.impl;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class FindUserCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return null;
    }
}
