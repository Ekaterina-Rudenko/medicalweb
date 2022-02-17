package by.epam.medicalweb.controller.filter;

import by.epam.medicalweb.controller.command.SessionAttribute;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static by.epam.medicalweb.controller.command.RequestParameterName.COMMAND;
import static by.epam.medicalweb.controller.command.SessionAttribute.CURRENT_PAGE;

@WebFilter(urlPatterns = {"/*"})
public class CurrentPageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        Logger logger = LogManager.getLogger();
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String currentPage = httpRequest.getRequestURL().toString();
        logger.log(Level.DEBUG, "currentPage URL: " + currentPage);

        if (currentPage.contains("jsp/")) {
            int index = currentPage.indexOf("jsp/");
            currentPage = currentPage.substring(index);
            logger.log(Level.DEBUG, "currentPage with jsp: " + currentPage);
            httpRequest.getSession().setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        }
        else if (currentPage.contains("controller") && !httpRequest.getParameterMap().isEmpty()
                && httpRequest.getQueryString() != null &&
                !httpRequest.getQueryString().contains("command=change_locale")) {
            logger.log(Level.DEBUG, "getParameterMap: " + httpRequest.getParameterMap());
            int index = currentPage.indexOf("controller");
            currentPage = currentPage.substring(index) + "?" + httpRequest.getQueryString();
            logger.log(Level.DEBUG, "currentPage with Controller: " + currentPage);
            httpRequest.getSession().setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        }
        filterChain.doFilter(httpRequest, servletResponse);
    }
}
