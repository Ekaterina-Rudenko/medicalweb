package by.epam.medicalweb.model.service;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.Visit;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VisitService {

  List<Visit> findAllVisits() throws ConnectionPoolException, ServiceException;

  Optional<Visit> findVisitById(long visitId) throws ServiceException, ConnectionPoolException;

  Optional<Visit> createVisit(Map<String, String> data) throws ServiceException, ConnectionPoolException;

  List<Integer> findFreeSlots(Map<String, String> data)
      throws ServiceException, ConnectionPoolException;

  boolean cancelVisit(long visitId) throws ServiceException, ConnectionPoolException;

  List<Visit> findVisitsByPatientId(long patientId) throws ConnectionPoolException, ServiceException;


}
