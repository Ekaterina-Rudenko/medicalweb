package by.epam.medicalweb.controller.command.impl;

import by.epam.medicalweb.controller.Command;
import by.epam.medicalweb.controller.Router;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.Specialization;
import by.epam.medicalweb.model.service.SpecializationServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static by.epam.medicalweb.controller.PagePath.SERVICES_PAGE;
import static by.epam.medicalweb.controller.RequestAttributeName.SPECIALIZATION_LIST;

public class ShowAllSpecializationsCommand implements Command {
    private static final SpecializationServiceImpl service = SpecializationServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        try {
            List<Specialization> specializationList = service.findAll();
            request.setAttribute(SPECIALIZATION_LIST, specializationList);
            router.setPage(SERVICES_PAGE);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return router;
    }
}
