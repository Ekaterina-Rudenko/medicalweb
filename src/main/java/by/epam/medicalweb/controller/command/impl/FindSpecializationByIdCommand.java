package by.epam.medicalweb.controller.command.impl;

import static by.epam.medicalweb.controller.command.RequestParameterName.SPECIALIZATION_ID;
import static by.epam.medicalweb.controller.command.RequestParameterName.SPECIALIZATION_NAME;
import static by.epam.medicalweb.controller.command.SessionAttribute.CURRENT_PAGE;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.Specialization;
import by.epam.medicalweb.model.service.SpecializationService;
import by.epam.medicalweb.model.service.impl.SpecializationServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindSpecializationByIdCommand implements Command {
  private static Logger logger = LogManager.getLogger();

  @Override
  public Router execute(HttpServletRequest request) {
    Router router = new Router();
    HttpSession session = request.getSession();
    String currentPage = (String) session.getAttribute(CURRENT_PAGE);
    String specializationId = request.getParameter(SPECIALIZATION_ID);
    SpecializationService specializationService = SpecializationServiceImpl.getInstance();
    Specialization specialization;
    try {
      specialization = specializationService.findSpecializationById(Long.parseLong(specializationId));
      request.setAttribute(SPECIALIZATION_NAME, specialization.getName());
      router.setPage(currentPage);
    } catch (ServiceException| ConnectionPoolException e) {
      logger.log(Level.DEBUG, "Specializations failed to be found", e);
    }
    return router;
  }

}
