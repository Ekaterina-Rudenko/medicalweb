package by.epam.medicalweb.controller.command.impl;

import static by.epam.medicalweb.controller.command.ErrorMessagesBundle.INCORRECT_OLD_PASSWORD_MESSAGE;
import static by.epam.medicalweb.controller.command.ErrorMessagesBundle.INVALID_NEW_PASSWORD_MESSAGE;
import static by.epam.medicalweb.controller.command.ErrorMessagesBundle.PASSWORD_MISMATCH_MESSAGE;
import static by.epam.medicalweb.controller.command.PagePath.LOG_IN_PAGE;
import static by.epam.medicalweb.controller.command.PagePath.PROFILE_SETTING_PAGE;
import static by.epam.medicalweb.controller.command.RequestParameterName.INCORRECT_OLD_PASSWORD;
import static by.epam.medicalweb.controller.command.RequestParameterName.INVALID_NEW_PASSWORD;
import static by.epam.medicalweb.controller.command.RequestParameterName.NEW_PASSWORD;
import static by.epam.medicalweb.controller.command.RequestParameterName.NEW_PASSWORD_CONFIRM;
import static by.epam.medicalweb.controller.command.RequestParameterName.OLD_PASSWORD;
import static by.epam.medicalweb.controller.command.RequestParameterName.PASSWORD_CHANGE_RESULT;
import static by.epam.medicalweb.controller.command.RequestParameterName.PASSWORD_MISMATCH;
import static by.epam.medicalweb.controller.command.SessionAttribute.USER;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.service.UserService;
import by.epam.medicalweb.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class ChangePasswordCommand implements Command {

  @Override
  public Router execute(HttpServletRequest request) {
    Router router = new Router();
    UserService userService = UserServiceImpl.getInstance();
    HttpSession session = request.getSession();
    User user =  (User) session.getAttribute(USER);
    long userId = user.getUserId();
    Map<String, String> data = new HashMap<>();
    data.put(OLD_PASSWORD, request.getParameter(OLD_PASSWORD));
    data.put(NEW_PASSWORD, request.getParameter(NEW_PASSWORD));
    data.put(NEW_PASSWORD_CONFIRM, request.getParameter(NEW_PASSWORD_CONFIRM));
    try {
      boolean passChangeResult = userService.changePassword(userId, data);
      if (passChangeResult) {
        request.setAttribute(PASSWORD_CHANGE_RESULT, passChangeResult);
        router.setPage(LOG_IN_PAGE);
      } else {
        for (String key : data.keySet()) {
          String value = data.get(key);
            switch (value) {
              case INCORRECT_OLD_PASSWORD -> request.setAttribute(INCORRECT_OLD_PASSWORD,
                  INCORRECT_OLD_PASSWORD_MESSAGE);
              case INVALID_NEW_PASSWORD -> request.setAttribute(INVALID_NEW_PASSWORD,
                  INVALID_NEW_PASSWORD_MESSAGE);
              case PASSWORD_MISMATCH -> request.setAttribute(PASSWORD_MISMATCH,
                  PASSWORD_MISMATCH_MESSAGE);
          }
        }
        router.setPage(PROFILE_SETTING_PAGE);
      }
    } catch (ServiceException e) {
      e.printStackTrace();
    }
    return router;
  }
}
