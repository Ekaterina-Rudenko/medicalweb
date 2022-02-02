package by.epam.medicalweb.controller.command.impl;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.epam.medicalweb.controller.command.PagePath.MAIN_PAGE;

public class LogOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        session.invalidate();
        router.setRouterType(Router.RouterType.REDIRECT);
        router.setPage(MAIN_PAGE);
        return router;
    }
}
