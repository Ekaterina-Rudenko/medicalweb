package by.epam.medicalweb.controller.command.impl;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.PagePath;
import by.epam.medicalweb.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

import static by.epam.medicalweb.controller.command.RequestParameterName.WRONG_COMMAND;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        request.setAttribute(WRONG_COMMAND, true);
        router.setPage(PagePath.ERROR_400);
        return null;
    }
}