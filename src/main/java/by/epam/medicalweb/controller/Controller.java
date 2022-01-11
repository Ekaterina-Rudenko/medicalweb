package by.epam.medicalweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static by.epam.medicalweb.controller.ParameterName.COMMAND;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    static Logger logger = LogManager.getLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.log(Level.DEBUG, "Get: " + request.getMethod());
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.log(Level.DEBUG, "Post: " + request.getMethod());
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND);
        Command command = CommandFactory.defineCommandType(commandName);
        Router router = command.execute(request);
        String page = router.getPage();
        switch (router.getRouterType()) {
            case FORWARD:
                logger.log(Level.DEBUG, "Type is Forward");
                request.getRequestDispatcher(page).forward(request, response);
                break;
            case REDIRECT:
                logger.log(Level.DEBUG, "Type is Redirect");
                response.sendRedirect(page);
                break;
            default:
                logger.log(Level.ERROR, "Incorrect router type ");
                logger.log(Level.DEBUG, "Forward to error page ");
                request.getRequestDispatcher(PagePath.ERROR_500).forward(request, response);
                break;
        }
    }
}