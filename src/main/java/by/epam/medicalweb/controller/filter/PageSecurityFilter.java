package by.epam.medicalweb.controller.filter;

import static by.epam.medicalweb.controller.command.PagePath.*;
import static by.epam.medicalweb.controller.command.SessionAttribute.USER_ROLE;
import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;

import by.epam.medicalweb.model.entity.User.UserRole;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter(urlPatterns = {"*.jsp"})
public class PageSecurityFilter implements Filter {
  private static final Logger logger = LogManager.getLogger();
  private static final String START_URI = "/index.jsp";
  private Set<String> guestPages;
  private Set<String> patientPages;
  private Set<String> doctorPages;
  private Set<String> adminPages;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    guestPages = Set.of(ABOUT_US_PAGE, INDEX_PAGE, HEADER_PAGE, ERROR_400, ERROR_500, DOCTORS_PAGE, MAIN_PAGE,
        LOG_IN_PAGE, REGISTRATION_PAGE, SERVICES_PAGE, SUCCESSFUL_REGISTRATION);
    patientPages = Set.of(ABOUT_US_PAGE,INDEX_PAGE, HEADER_PAGE, ERROR_400, ERROR_500, DOCTORS_PAGE, MAIN_PAGE,
        SERVICES_PAGE, LOG_IN_PAGE, SUCCESSFUL_REGISTRATION,
        MAKE_APPOINTMENT_PAGE,
        APPOINTMENTS_PAGE,
        PATIENT_PROFILE_PAGE,
        PROFILE_SETTINGS_PAGE,
        RECOMMENDATIONS_PAGE,
        TOP_UP_BALANCE_PAGE);
    doctorPages = Set.of(ABOUT_US_PAGE,INDEX_PAGE, HEADER_PAGE, ERROR_400, ERROR_500, DOCTORS_PAGE, MAIN_PAGE,
        LOG_IN_PAGE, REGISTRATION_PAGE, SERVICES_PAGE, SUCCESSFUL_REGISTRATION,
        DOCTOR_PROFILE_PAGE,
        MAKE_RECOMMENDATION_PAGE,
        VISITS_PAGE);
    adminPages = Set.of(ABOUT_US_PAGE,INDEX_PAGE, HEADER_PAGE, ERROR_400, ERROR_500, DOCTORS_PAGE, MAIN_PAGE,
        LOG_IN_PAGE, REGISTRATION_PAGE, SERVICES_PAGE, SUCCESSFUL_REGISTRATION,
        ADMINISTRATION_PAGE,
        ADMIN_PROFILE_PAGE,
        USERS_PAGE,
        ADD_DOCTOR_PAGE,
        ADD_SERVICE_PAGE,
        ADD_SPECIALIZATION_PAGE,
        EDIT_APPOINTMENT_PAGE,
        EDIT_DOCTOR_PAGE,
        EDIT_SERVICE_PAGE,
        USER_ADDED_SUCCESSFULLY);
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    String requestUri = request.getServletPath();
    HttpSession session = request.getSession();

    boolean isGuestPage = guestPages.stream().anyMatch(requestUri::contains);
    boolean isPatientPage = patientPages.stream().anyMatch(requestUri::contains);
    boolean isAdminPage = adminPages.stream().anyMatch(requestUri::contains);
    boolean isDoctorPage = doctorPages.stream().anyMatch(requestUri::contains);

    UserRole userRole = (UserRole) session.getAttribute(USER_ROLE);
    logger.log(Level.DEBUG, userRole);
    if (userRole == UserRole.GUEST && isGuestPage) {
      filterChain.doFilter(request, response);
    } else if (userRole == UserRole.PATIENT && isPatientPage) {
      filterChain.doFilter(request, response);
    } else if (userRole == UserRole.DOCTOR && isDoctorPage) {
      filterChain.doFilter(request, response);
    } else if (userRole == UserRole.ADMIN && isAdminPage) {
      filterChain.doFilter(request, response);
    } else {
      response.sendError(SC_FORBIDDEN);
    }
  }
}
