package by.epam.medicalweb.controller.command.impl;

import static by.epam.medicalweb.controller.command.PagePath.APPOINTMENTS_PAGE;
import static by.epam.medicalweb.controller.command.PagePath.MAKE_APPOINTMENT_PAGE;
import static by.epam.medicalweb.controller.command.RequestParameterName.*;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.controller.command.Router.RouterType;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.Visit;
import by.epam.medicalweb.model.service.VisitService;
import by.epam.medicalweb.model.service.impl.VisitServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateVisitCommand implements Command {

  private static Logger logger = LogManager.getLogger();
  private VisitService visitService = VisitServiceImpl.getInstance();

  @Override
  public Router execute(HttpServletRequest request) {
    Router router = new Router();
    HttpSession session = request.getSession();
    Map<String, String> mapData = new HashMap<>();
    logger.log(Level.DEBUG, session.getAttribute(NEW_VISIT_SPECIALIZATION_ID));
    logger.log(Level.DEBUG, session.getAttribute(NEW_VISIT_DOCTOR_ID));
    mapData.put(SPECIALIZATION_ID, session.getAttribute(NEW_VISIT_SPECIALIZATION_ID).toString());
    mapData.put(DOCTOR_ID, session.getAttribute(NEW_VISIT_DOCTOR_ID).toString());
    mapData.put(SERVICE_ID, session.getAttribute(NEW_VISIT_SERVICE_ID).toString());
    mapData.put(VISIT_DATE, session.getAttribute(NEW_VISIT_DATE).toString());
    mapData.put(VISIT_TIME, request.getParameter(VISIT_TIME));
    mapData.put(TYPE_PAYMENT, request.getParameter(TYPE_PAYMENT));
    mapData.put(PATIENT_ID, session.getAttribute(PATIENT_ID).toString());
    try {
      Optional<Visit> visit = visitService.createVisit(mapData);
      if (visit.isPresent()) {
        logger.log(Level.DEBUG, "Visit was created successfully");
        router.setRouterType(RouterType.REDIRECT);
        router.setPage(APPOINTMENTS_PAGE);
      } else {
        router.setPage(MAKE_APPOINTMENT_PAGE);
      }
    } catch (ServiceException | ConnectionPoolException e) {
      logger.log(Level.DEBUG, "Visit failed to be created", e);
    }
    return router;
  }
}
