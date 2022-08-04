package by.epam.medicalweb.controller.command.impl;

import static by.epam.medicalweb.controller.command.PagePath.ERROR_500;
import static by.epam.medicalweb.controller.command.PagePath.SERVICES_PAGE;
import static by.epam.medicalweb.controller.command.RequestParameterName.NEW_SERVICE_PRICE;
import static by.epam.medicalweb.controller.command.RequestParameterName.SERVICE_ID;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.controller.command.Router.RouterType;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.service.MedicalServicesService;
import by.epam.medicalweb.model.service.impl.MedicalServicesServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class EditServiceCommand implements Command {

  @Override
  public Router execute(HttpServletRequest request) {
    Router router = new Router();
    MedicalServicesService medicalService = MedicalServicesServiceImpl.getInstance();
    long serviceId = Long.parseLong(request.getParameter(SERVICE_ID));
    String priceString = request.getParameter(NEW_SERVICE_PRICE);
    BigDecimal price = BigDecimal.valueOf(Double.parseDouble(priceString));
    try {
      if (medicalService.editMedicalService(serviceId, price)) {
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
