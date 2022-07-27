package by.epam.medicalweb.controller.command.impl;

import static by.epam.medicalweb.controller.command.ErrorMessagesBundle.EMPTY_DOCTOR_LIST_MESSAGE;
import static by.epam.medicalweb.controller.command.ErrorMessagesBundle.EMPTY_SERVICE_LIST_MESSAGE;
import static by.epam.medicalweb.controller.command.PagePath.MAKE_APPOINTMENT_PAGE;
import static by.epam.medicalweb.controller.command.RequestParameterName.EMPTY_DOCTOR;
import static by.epam.medicalweb.controller.command.RequestParameterName.EMPTY_SERVICE;
import static by.epam.medicalweb.controller.command.RequestParameterName.NEW_VISIT_SPECIALIZATION_ID;
import static by.epam.medicalweb.controller.command.RequestParameterName.SPECIALIZATION_ID;
import static by.epam.medicalweb.controller.command.SessionAttribute.DOCTOR_LIST;
import static by.epam.medicalweb.controller.command.SessionAttribute.SERVICE_LIST;

import by.epam.medicalweb.controller.command.Command;
import by.epam.medicalweb.controller.command.Router;
import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.Doctor;
import by.epam.medicalweb.model.entity.MedicalService;
import by.epam.medicalweb.model.service.DoctorService;
import by.epam.medicalweb.model.service.MedicalServicesService;
import by.epam.medicalweb.model.service.impl.DoctorServiceImpl;
import by.epam.medicalweb.model.service.impl.MedicalServicesServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;


public class FindDoctorsBySpecializationCommand implements Command {

  @Override
  public Router execute(HttpServletRequest request)
      throws ServletException, ConnectionPoolException {
    Router router = new Router();
    HttpSession session = request.getSession();
    DoctorService doctorService = DoctorServiceImpl.getInstance();
    MedicalServicesService medicalService = MedicalServicesServiceImpl.getInstance();
    try{
      String specialization = request.getParameter(SPECIALIZATION_ID);
      long specId = Long.parseLong(specialization);
      List<Doctor> doctorList = doctorService.findDoctorsBySpecializationId(specId);
      session.setAttribute(DOCTOR_LIST, doctorList);
      session.setAttribute(NEW_VISIT_SPECIALIZATION_ID, specId);

      if(doctorList.isEmpty()){
        request.setAttribute(EMPTY_DOCTOR, EMPTY_DOCTOR_LIST_MESSAGE);
      }

      List<MedicalService> serviceList = medicalService.findBySpecialization(specId);
      session.setAttribute(SERVICE_LIST, serviceList);

      if(doctorList.isEmpty()){
        request.setAttribute(EMPTY_SERVICE, EMPTY_SERVICE_LIST_MESSAGE);
      }

      router.setPage(MAKE_APPOINTMENT_PAGE);
    } catch (ServiceException e) {
      e.printStackTrace();
    }
    return router;
  }
}
