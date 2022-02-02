package by.epam.medicalweb.controller.command.impl;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.Doctor;
import by.epam.medicalweb.model.entity.Patient;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.service.DoctorService;
import by.epam.medicalweb.model.service.PatientService;
import by.epam.medicalweb.model.service.UserService;
import by.epam.medicalweb.model.service.impl.DoctorServiceImpl;
import by.epam.medicalweb.model.service.impl.PatientServiceImpl;
import by.epam.medicalweb.model.service.impl.UserServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static by.epam.medicalweb.controller.command.ErrorMessagesBundle.*;
import static by.epam.medicalweb.controller.command.PagePath.LOG_IN_PAGE;
import static by.epam.medicalweb.controller.command.PagePath.MAIN_PAGE;
import static by.epam.medicalweb.controller.command.RequestAttributeName.*;
import static by.epam.medicalweb.controller.command.RequestParameterName.*;
import static by.epam.medicalweb.controller.command.SessionAttribute.*;
import static by.epam.medicalweb.controller.command.SessionAttribute.USER_ROLE;


public class LogInCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        Router router = new Router();
        UserService userService = UserServiceImpl.getInstance();
        PatientService patientService = PatientServiceImpl.getInstance();
        DoctorService doctorService = DoctorServiceImpl.getInstance();
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        logger.log(Level.DEBUG, "login: " + login + "password: " + password);
        try {
            Optional<User> optionalUser = userService.logIn(login, password);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (user.getState() != User.UserState.BLOCKED) {
                    switch (user.getRole()) {
                        case ADMIN -> {
                            session.setAttribute(USER, user);
                            session.setAttribute(USER_ROLE, user.getRole());
                            router.setPage(MAIN_PAGE);
                            //todo
                        }
                        case PATIENT -> {
                            Patient patient = patientService.findPatientInfoById(user.getUserId());
                            session.setAttribute(USER, user);
                            session.setAttribute(USER_ROLE, user.getRole() );
                            session.setAttribute(PATIENT_GENDER, patient.getGender().getGenderString());
                            session.setAttribute(PATIENT_BIRTHDATE, patient.getBirthDate());
                            session.setAttribute(PATIENT_BALANCE, patient.getBalance());
                            router.setPage(MAIN_PAGE);
                        }
                        case DOCTOR -> {
                            Doctor doctor = doctorService.findDoctorInfoById(user.getUserId());
                            session.setAttribute(USER, user);
                            session.setAttribute(USER_ROLE, user.getRole());
                            session.setAttribute(DOCTOR_CATEGORY, doctor.getCategory());
                            session.setAttribute(DOCTOR_PHOTO, doctor.getPhotoPath());
                            session.setAttribute(DOCTOR_SPECIALIZATION, doctor.getSpecializationId());
                            router.setPage(MAIN_PAGE);
                        }
                        default -> {
                            request.setAttribute(USER_ROLE_UNDEFINED, USER_ROLE_UNDEFINED_MESSAGE);
                            router.setPage(LOG_IN_PAGE);
                        }
                    }
                } else {
                    request.setAttribute(USER_STATUS_BLOCKED, USER_BLOCKED_MESSAGE);
                    router.setPage(LOG_IN_PAGE);
                }
            } else {
                logger.log(Level.DEBUG, "Incorrect login ir password, logIn command: ");
                request.setAttribute(INCORRECT_LOGIN_OR_PASS, INCORRECT_LOGIN_OR_PASSWORD_MESSAGE);
                router.setPage(LOG_IN_PAGE);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return router;
    }
}
