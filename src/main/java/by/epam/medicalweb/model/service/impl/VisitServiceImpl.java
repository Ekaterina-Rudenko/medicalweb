package by.epam.medicalweb.model.service.impl;

import static by.epam.medicalweb.controller.command.RequestParameterName.*;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.EntityTransaction;
import by.epam.medicalweb.model.dao.impl.VisitDaoImpl;
import by.epam.medicalweb.model.entity.Doctor;
import by.epam.medicalweb.model.entity.MedicalService;
import by.epam.medicalweb.model.entity.Patient;
import by.epam.medicalweb.model.entity.Specialization;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.entity.Visit;
import by.epam.medicalweb.model.entity.Visit.TypePayment;
import by.epam.medicalweb.model.entity.Visit.VisitState;
import by.epam.medicalweb.model.service.VisitService;
import by.epam.medicalweb.util.impl.VisitValidator;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VisitServiceImpl implements VisitService {

  private static Logger logger = LogManager.getLogger();
  private static VisitServiceImpl instance;

  private VisitServiceImpl() {
  }

  public static VisitService getInstance() {
    if (instance == null) {
      instance = new VisitServiceImpl();
    }
    return instance;
  }

  @Override
  public List<Visit> findAllVisits() throws ConnectionPoolException, ServiceException {
    EntityTransaction entityTransaction = new EntityTransaction();
    AbstractDao<Visit> visitDao = new VisitDaoImpl();
    entityTransaction.beginTransaction(visitDao);
    List<Visit> visitList = new ArrayList<>();
    try {
      visitList = visitDao.findAll();
    } catch (DaoException e) {
      logger.log(Level.DEBUG, "Visits failed to be found");
      throw new ServiceException("Visits failed to be found", e);
    } finally {
      entityTransaction.endTransaction();
    }
    return visitList;
  }

  @Override
  public List<Visit> findVisitsByPatientId(long patientId) throws ConnectionPoolException, ServiceException {
    EntityTransaction entityTransaction = new EntityTransaction();
    VisitDaoImpl visitDao = new VisitDaoImpl();
    entityTransaction.beginQuery(visitDao);
    List<Visit> visitList = new ArrayList<>();
    try {
      visitList = visitDao.findVisitsByPatientId(patientId);
    } catch (DaoException e) {
      logger.log(Level.DEBUG, "Visits failed to be found");
      throw new ServiceException("Visits failed to be found", e);
    } finally {
      entityTransaction.endQuery();
    }
    return visitList;
  }

  @Override
  public Optional<Visit> findVisitById(long visitId)
      throws ServiceException, ConnectionPoolException {
    EntityTransaction entityTransaction = new EntityTransaction();
    AbstractDao<Visit> visitDao = new VisitDaoImpl();
    entityTransaction.beginTransaction(visitDao);
    Optional<Visit> optionalVisit;
    try {
      optionalVisit = visitDao.findEntityById(visitId);
    } catch (DaoException e) {
      logger.log(Level.DEBUG, "Visit failed to be found", e);
      throw new ServiceException("Visit failed to be found", e);
    } finally {
      entityTransaction.endTransaction();
    }
    return optionalVisit;
  }

  @Override
  public Optional<Visit> createVisit(Map<String, String> data)
      throws ServiceException, ConnectionPoolException {
    EntityTransaction entityTransaction = new EntityTransaction();
    AbstractDao<Visit> visitDao = new VisitDaoImpl();
    entityTransaction.beginTransaction(visitDao);
    try {
      if (!VisitValidator.checkVisitData(data)) {
        logger.log(Level.DEBUG, "Visit input data is not valid");
        return Optional.empty();
      }
      String specialization = data.get(SPECIALIZATION_ID);
      String doctor = data.get(DOCTOR_ID);
      String service = data.get(SERVICE_ID);
      String visitDateString = data.get(VISIT_DATE);
      String visitTimeString = data.get(VISIT_TIME);
      String typePaymentString = data.get(TYPE_PAYMENT);
      String patient = data.get(PATIENT_ID);

      long specId = Long.parseLong(specialization);
      long doctorId = Long.parseLong(doctor);
      long serviceId = Long.parseLong(service);
      LocalDate visitDate = LocalDate.parse(visitDateString);
      int visitTime = Integer.parseInt(visitTimeString);
      TypePayment typePayment = TypePayment.valueOf(typePaymentString.toUpperCase());
      long patientId = Long.parseLong(patient);

      Visit newVisit = new Visit.VisitBuilder()
          .setSpecialization(new Specialization.Builder().setSpecializationId(specId).build())
          .setDoctor(new Doctor.DoctorBuilder().setUserId(doctorId).build())
          .setMedicalService(new MedicalService.ServiceBuilder().setServiceId(serviceId).build())
          .setLocalDate(visitDate)
          .setTime(visitTime)
          .setTypeOfPayment(typePayment)
          .setVisitState(VisitState.NEW)
          .setPatient(new Patient.PatientBuilder().setUserId(patientId).build())
          .build();

      long visitId = visitDao.create(newVisit);
      if(visitId != 0){
        newVisit.setVisitId(visitId);
        logger.log(Level.DEBUG, "Visit id: " + visitId + " was created.");
        return Optional.of(newVisit);
      } else{
        logger.log(Level.DEBUG, "Visit wasn't created.");
        return Optional.empty();
      }
    } catch (DaoException | SQLException e) {
      logger.log(Level.DEBUG, "Visit failed to be created", e);
      throw new ServiceException("Visit failed to be created", e);
    } finally {
      entityTransaction.endTransaction();
    }
  }

  @Override
  public List<Integer> findFreeSlots(Map<String, String> data)
      throws ServiceException, ConnectionPoolException {
    EntityTransaction entityTransaction = new EntityTransaction();
    VisitDaoImpl visitDao = new VisitDaoImpl();
    entityTransaction.beginTransaction(visitDao);
    List<Integer> slotList = new ArrayList<>();
    try {
      String dateString = data.get(VISIT_DATE);
      String doctorIdString = data.get(DOCTOR_ID);
      String serviceIdString = data.get(SERVICE_ID);

      if (doctorIdString == null) {
        data.put(DOCTOR_ID, EMPTY_DOCTOR);
        return slotList;
      }
      if (dateString == null) {
        data.put(VISIT_DATE, EMPTY_VISIT_DATE);
        return slotList;
      }
      if (serviceIdString == null) {
        data.put(SERVICE_ID, EMPTY_SERVICE);
        return slotList;
      }

      LocalDate visitDate = LocalDate.parse(dateString);
      if(!checkVisitDate(visitDate)){
        data.put(VISIT_DATE, INVALID_VISIT_DATE);
        return slotList;
      }
      long doctorId = Long.parseLong(doctorIdString);

      slotList = visitDao.findAvailableTime(visitDate, doctorId);
    } catch (DaoException e) {
      logger.log(Level.DEBUG, "Time slots failed to be found", e);
      throw new ServiceException("Time slits failed to be found", e);
    } finally {
      entityTransaction.endTransaction();
    }
    return slotList;
  }

  @Override
  public boolean cancelVisit(long visitId) throws ServiceException, ConnectionPoolException {
    EntityTransaction entityTransaction = new EntityTransaction();
    VisitDaoImpl visitDao = new VisitDaoImpl();
    entityTransaction.beginTransaction(visitDao);
    boolean isCancelled;
    try {
      isCancelled = visitDao.updateVisitStateByVisitId(visitId, VisitState.CANCELLED);
    } catch (DaoException e) {
      logger.log(Level.DEBUG, "Visit state failed to be cancelled", e);
      throw new ServiceException("Visit state failed to be cancelled", e);
    } finally {
      entityTransaction.endTransaction();
    }
    return isCancelled;
  }

  private static boolean checkVisitDate(LocalDate date) {
    LocalDate today = LocalDate.now();
    return date.isAfter(today);

  }

}
