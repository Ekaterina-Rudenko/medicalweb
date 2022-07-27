package by.epam.medicalweb.controller.command.impl;

import static by.epam.medicalweb.controller.command.PagePath.APPOINTMENTS_PAGE;
import static by.epam.medicalweb.controller.command.PagePath.ERROR_400;
import static by.epam.medicalweb.controller.command.RequestParameterName.PATIENT_ID;
import static by.epam.medicalweb.controller.command.RequestParameterName.USER_ROLE;
import static by.epam.medicalweb.controller.command.RequestParameterName.VISIT_LIST;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.controller.command.Router.RouterType;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.entity.User.UserRole;
import by.epam.medicalweb.model.entity.Visit;
import by.epam.medicalweb.model.service.VisitService;
import by.epam.medicalweb.model.service.impl.VisitServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class FindVisitsByPatientIdCommand implements Command {

  @Override
  public Router execute(HttpServletRequest request)
      throws ServletException, ConnectionPoolException {
    Router router = new Router();
    HttpSession session = request.getSession();
    VisitService visitService = VisitServiceImpl.getInstance();
    try {
      long patientId = (Long) session.getAttribute(PATIENT_ID);
      List<Visit> visitList = visitService.findVisitsByPatientId(patientId);
      session.setAttribute(VISIT_LIST, visitList);
      router.setPage(APPOINTMENTS_PAGE);
    } catch (ServiceException e) {
      e.printStackTrace();
    }
    return router;
  }
}
