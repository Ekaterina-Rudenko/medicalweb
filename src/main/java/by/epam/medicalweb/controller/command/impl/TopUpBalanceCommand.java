package by.epam.medicalweb.controller.command.impl;

import static by.epam.medicalweb.controller.command.PagePath.PATIENT_PROFILE_PAGE;
import static by.epam.medicalweb.controller.command.PagePath.TOP_UP_BALANCE_PAGE;
import static by.epam.medicalweb.controller.command.SessionAttribute.USER;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.controller.command.Router.RouterType;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.service.UserService;
import by.epam.medicalweb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class TopUpBalanceCommand implements Command {

  @Override
  public Router execute(HttpServletRequest request) {
    Router router = new Router();
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute(USER);
    long userId = user.getUserId();
    String money = request.getParameter(MONEY);
    UserService userService = UserServiceImpl.getInstance();
    boolean isUpdated = userService.topUpBalance(userId, money);
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
