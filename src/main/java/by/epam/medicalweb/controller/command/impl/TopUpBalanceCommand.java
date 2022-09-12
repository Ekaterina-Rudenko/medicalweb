package by.epam.medicalweb.controller.command.impl;

import static by.epam.medicalweb.controller.command.ErrorMessagesBundle.INVALID_SUM_MESSAGE;
import static by.epam.medicalweb.controller.command.PagePath.PATIENT_PROFILE_PAGE;
import static by.epam.medicalweb.controller.command.PagePath.TOP_UP_BALANCE_PAGE;
import static by.epam.medicalweb.controller.command.RequestParameterName.INVALID_SUM;
import static by.epam.medicalweb.controller.command.RequestParameterName.MONEY;
import static by.epam.medicalweb.controller.command.SessionAttribute.USER;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.controller.command.Router.RouterType;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.service.PatientService;
import by.epam.medicalweb.model.service.impl.PatientServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class TopUpBalanceCommand implements Command {

  @Override
  public Router execute(HttpServletRequest request)
      throws ServiceException, ConnectionPoolException {
    Router router = new Router();
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute(USER);
    long userId = user.getUserId();
    String money = request.getParameter(MONEY);
    PatientService patientService = PatientServiceImpl.getInstance();
    boolean isUpdated = patientService.topUpBalance(userId, money);
    if (isUpdated) {
      router.setRouterType(RouterType.REDIRECT);
      router.setPage(PATIENT_PROFILE_PAGE);
    } else {
      request.setAttribute(INVALID_SUM, INVALID_SUM_MESSAGE);
      router.setPage(TOP_UP_BALANCE_PAGE);
    }
    return router;
  }
}
