package by.epam.medicalweb.model.service;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.MedicalService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MedicalServicesService {
  List<MedicalService> findAllServices() throws ServiceException, ConnectionPoolException;
  List<MedicalService> findBySpecialization(long specId)
      throws ServiceException, ConnectionPoolException;
  long createService(Map<String, String> data) throws ServiceException, ConnectionPoolException;
  boolean editMedicalService(long serviceId, BigDecimal price) throws ServiceException, ConnectionPoolException;
}
