package by.epam.medicalweb.controller.command.impl;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.Specialization;
import by.epam.medicalweb.model.service.SpecializationService;
import by.epam.medicalweb.model.service.impl.SpecializationServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static by.epam.medicalweb.controller.command.PagePath.SERVICES_PAGE;
import static by.epam.medicalweb.controller.command.SessionAttribute.SPECIALIZATION_LIST;

public class ShowAllSpecializationsCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        SpecializationService service = SpecializationServiceImpl.getInstance();
        try {
            List<Specialization> specializationList = service.findAll();
            session.setAttribute(SPECIALIZATION_LIST, specializationList);
            router.setPage(SERVICES_PAGE);
        } catch (ServiceException | ConnectionPoolException e) {
            e.printStackTrace();
        }
        return router;
    }
}
