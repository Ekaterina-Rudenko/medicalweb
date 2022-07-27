package by.epam.medicalweb.controller.command.impl;

import static by.epam.medicalweb.controller.command.ErrorMessagesBundle.*;
import static by.epam.medicalweb.controller.command.PagePath.MAKE_APPOINTMENT_PAGE;
import static by.epam.medicalweb.controller.command.RequestParameterName.*;
import static by.epam.medicalweb.controller.command.SessionAttribute.*;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.service.VisitService;
import by.epam.medicalweb.model.service.impl.VisitServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindTimeSlotsByDoctorAndDateCommand implements Command {
  private static Logger logger = LogManager.getLogger();
  @Override
  public Router execute(HttpServletRequest request)
      throws ConnectionPoolException {
    Router router = new Router();
    HttpSession session = request.getSession();
    Map<String, String> data = new HashMap<>();
    data.put(DOCTOR_ID, request.getParameter(DOCTOR_ID));
    data.put(VISIT_DATE, request.getParameter(VISIT_DATE));
    data.put(SERVICE_ID, request.getParameter(SERVICE_ID));
    VisitService visitService = VisitServiceImpl.getInstance();
    try {
      List<Integer> slotList = visitService.findFreeSlots(data);
      session.setAttribute(SLOTS_LIST, slotList);
      if(!slotList.isEmpty()){
        session.setAttribute(NEW_VISIT_DOCTOR_ID, Long.parseLong(data.get(DOCTOR_ID)));
        session.setAttribute(NEW_VISIT_DATE, LocalDate.parse(data.get(VISIT_DATE)));
        session.setAttribute(NEW_VISIT_SERVICE_ID, Long.parseLong(data.get(SERVICE_ID)));
      } else {
        for(String key : data.keySet()){
          String value = data.get(key);
          switch (value){
            case EMPTY_DOCTOR -> request.setAttribute(EMPTY_DOCTOR, EMPTY_DOCTOR_MESSAGE);
            case EMPTY_VISIT_DATE -> request.setAttribute(EMPTY_VISIT_DATE, EMPTY_VISIT_DATE_MESSAGE);
            case EMPTY_SERVICE -> request.setAttribute(EMPTY_SERVICE, EMPTY_SERVICE_MESSAGE);
            case INVALID_VISIT_DATE -> request.setAttribute(INVALID_VISIT_DATE, INVALID_VISIT_DATE_MESSAGE);
            default -> request.setAttribute(SLOTS_LIST, NO_SLOTS_FOUND_MESSAGE);
          }
        }
      }
      router.setPage(MAKE_APPOINTMENT_PAGE);
    } catch (ServiceException e) {
      logger.log(Level.DEBUG, "Time slots failed to be found", e);
    }
    return router;
  }
}
