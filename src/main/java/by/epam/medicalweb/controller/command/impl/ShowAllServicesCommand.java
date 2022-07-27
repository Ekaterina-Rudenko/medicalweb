package by.epam.medicalweb.controller.command.impl;

import static by.epam.medicalweb.controller.command.PagePath.SERVICES_PAGE;
import static by.epam.medicalweb.controller.command.SessionAttribute.SERVICE_LIST;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.MedicalService;
import by.epam.medicalweb.model.service.MedicalServicesService;
import by.epam.medicalweb.model.service.impl.MedicalServicesServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class ShowAllServicesCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {

        Router router = new Router();
        HttpSession session = request.getSession();
        MedicalServicesService medicalServicesService = MedicalServicesServiceImpl.getInstance();
        try{
            List<MedicalService> services = medicalServicesService.findAllServices();
            session.setAttribute(SERVICE_LIST, services);
            router.setPage(SERVICES_PAGE);
        } catch (ServiceException| ConnectionPoolException e) {
            e.printStackTrace();
        }
        return router;

    }
}
