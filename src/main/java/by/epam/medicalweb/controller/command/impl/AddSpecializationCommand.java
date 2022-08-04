package by.epam.medicalweb.controller.command.impl;

import static by.epam.medicalweb.controller.command.PagePath.ERROR_500;
import static by.epam.medicalweb.controller.command.PagePath.SERVICES_PAGE;
import static by.epam.medicalweb.controller.command.RequestParameterName.SPECIALIZATION_NAME;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.controller.command.Router.RouterType;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.service.SpecializationService;
import by.epam.medicalweb.model.service.impl.SpecializationServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

public class AddSpecializationCommand implements Command {

  @Override
  public Router execute(HttpServletRequest request)
      throws ServletException, ConnectionPoolException {
    Router router = new Router();
    SpecializationService specializationService = SpecializationServiceImpl.getInstance();
    String specName = request.getParameter(SPECIALIZATION_NAME);
    try {
      long newServiceId = specializationService.createSpecialization(specName);
      if (newServiceId > 0) {
        router.setPage(SERVICES_PAGE);
      } else {
        router.setRouterType(RouterType.REDIRECT);
        router.setPage(ERROR_500);
      }
    } catch (ServiceException | ConnectionPoolException e) {
      e.printStackTrace();
    }
    return router;
  }
}
