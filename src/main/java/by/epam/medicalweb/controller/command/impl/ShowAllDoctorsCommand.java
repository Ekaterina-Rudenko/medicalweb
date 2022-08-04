package by.epam.medicalweb.controller.command.impl;

import static by.epam.medicalweb.controller.command.PagePath.DOCTORS_PAGE;
import static by.epam.medicalweb.controller.command.SessionAttribute.DOCTOR_LIST;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.model.entity.Doctor;
import by.epam.medicalweb.model.service.DoctorService;
import by.epam.medicalweb.model.service.impl.DoctorServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowAllDoctorsCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request){
        Router router = new Router();
        DoctorService doctorService = DoctorServiceImpl.getInstance();
        List<Doctor> doctorList = new ArrayList<>();
        try {
            doctorList = doctorService.findAllDoctors();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        request.setAttribute(DOCTOR_LIST, doctorList);
        router.setPage(DOCTORS_PAGE);
        return router;
    }
}
