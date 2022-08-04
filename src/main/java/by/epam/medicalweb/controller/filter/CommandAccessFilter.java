package by.epam.medicalweb.controller.filter;


import static by.epam.medicalweb.controller.command.RequestParameterName.COMMAND;
import static by.epam.medicalweb.controller.command.SessionAttribute.USER;

import by.epam.medicalweb.controller.command.CommandType;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.entity.User.UserRole;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter(urlPatterns = {"/*"})
public class CommandAccessFilter implements Filter {
  private static Logger logger = LogManager.getLogger();

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    HttpSession session = request.getSession();
    String commandStr = request.getParameter(COMMAND);
    logger.log(Level.DEBUG, "Access check. Command is " + commandStr);
    try {
      CommandType commandType = CommandType.valueOf(commandStr.toUpperCase());
      EnumSet<UserRole> allowedRoles = commandType.getAllowedRoles();
      Optional<Object> optionalUser = Optional.ofNullable(session.getAttribute(USER));
      if (optionalUser.isPresent()) {
        User user = (User) optionalUser.get();
        logger.log(Level.DEBUG, "User role is " + user.getRole());
        if (allowedRoles.contains(user.getRole())) {
          filterChain.doFilter(request, response);
        } else {
          response.sendError(403);
        }
      } else {
        if(allowedRoles.contains(UserRole.GUEST)){
          filterChain.doFilter(request, response);
        } else {
          response.sendError(403);
        }
      }
    } catch (IllegalArgumentException | NullPointerException e) {
      logger.log(Level.DEBUG, "Command is absent");
      filterChain.doFilter(request, response);
    }
  }
}
