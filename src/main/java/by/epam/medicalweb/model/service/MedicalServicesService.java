package by.epam.medicalweb.model.service;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.MedicalService;
import java.util.List;

public interface MedicalServicesService {
  List<MedicalService> findAllServices() throws ServiceException, ConnectionPoolException;
  List<MedicalService> findBySpecialization(long specId)
      throws ServiceException, ConnectionPoolException;

}
