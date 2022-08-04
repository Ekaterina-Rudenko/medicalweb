package by.epam.medicalweb.controller.command.impl;

import static by.epam.medicalweb.controller.command.PagePath.ERROR_500;
import static by.epam.medicalweb.controller.command.PagePath.SERVICES_PAGE;
import static by.epam.medicalweb.controller.command.RequestParameterName.SPECIALIZATION_ID;
import static by.epam.medicalweb.model.dao.ColumnName.SERVICE_NAME;
import static by.epam.medicalweb.model.dao.ColumnName.SERVICE_PRICE;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.controller.command.Router.RouterType;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.service.MedicalServicesService;
import by.epam.medicalweb.model.service.impl.MedicalServicesServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class AddServiceCommand implements Command {

  @Override
  public Router execute(HttpServletRequest request) {
    Router router = new Router();
    MedicalServicesService medicalService = MedicalServicesServiceImpl.getInstance();
    Map<String, String> data = new HashMap<>();
    data.put(SERVICE_NAME, request.getParameter(SERVICE_NAME));
    data.put(SERVICE_PRICE, request.getParameter(SERVICE_PRICE));
    data.put(SPECIALIZATION_ID, request.getParameter(SPECIALIZATION_ID));
    try {
      long newServiceId = medicalService.createService(data);
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
