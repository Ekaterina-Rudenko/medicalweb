package by.epam.medicalweb.controller.command.impl;

import static by.epam.medicalweb.controller.command.PagePath.APPOINTMENTS_PAGE;
import static by.epam.medicalweb.controller.command.RequestParameterName.ACTION_RESULT;
import static by.epam.medicalweb.model.dao.ColumnName.VISIT_ID;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.service.VisitService;
import by.epam.medicalweb.model.service.impl.VisitServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CancelVisitCommand implements Command {

  private static Logger logger = LogManager.getLogger();

  @Override
  public Router execute(HttpServletRequest request) {
    Router router = new Router();
    StringBuilder paramPagePath = new StringBuilder();
    long visitId = Long.parseLong(request.getParameter(VISIT_ID));
    VisitService visitService = VisitServiceImpl.getInstance();
    try {
        boolean actionResult = visitService.cancelVisit(visitId);
        paramPagePath.append(APPOINTMENTS_PAGE).append("?").append(ACTION_RESULT).append("=")
            .append(actionResult);
        router.setPage(paramPagePath.toString());
  } catch (ServiceException | ConnectionPoolException e) {
      logger.log(Level.ERROR, "Visit is failed to be deleted", e);
    }
    return router;
  }
}
