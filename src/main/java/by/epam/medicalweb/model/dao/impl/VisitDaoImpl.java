package by.epam.medicalweb.model.dao.impl;

import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.VisitDao;
import by.epam.medicalweb.model.entity.Visit;
import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.model.mapper.impl.VisitMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VisitDaoImpl extends AbstractDao<Visit> implements VisitDao {

  private static Logger logger = LogManager.getLogger();

  private static final String SQL_SELECT_ALL_VISITS = """
       SELECT visit_id, visits.spec_id, spec_name,
       visits.doctor_id, u1.last_name AS doctor_surname, u1.first_name AS doctor_name,
       visits.service_id, service_name, visit_date, visit_time, 
       type_payment, visit_state,
       visits.patient_id, u2.last_name AS patient_surname, u2.first_name AS patient_name 
      FROM visits
          JOIN specializations ON specializations.spec_id = visits.spec_id
          JOIN users u1 ON u1.user_id = doctor_id
          JOIN services ON services.service_id = visits.service_id
          JOIN users u2 ON u2.user_id = visits.patient_id;""";
  private static final String SQL_SELECT_VISIT_BY_ID = """
      SELECT visit_id, visits.spec_id, spec_name,
      visits.doctor_id, u1.last_name AS doctor_surname, u1.first_name AS doctor_name,
      visits.service_id, service_name, visit_date, visit_time,
      type_payment, visit_state,
      u2.last_name AS patient_surname, u2.first_name AS patient_name
      FROM visits
          JOIN specializations ON specializations.spec_id = visits.spec_id
          JOIN users u1 ON u1.user_id = doctor_id
          JOIN services ON services.service_id = visits.service_id
          JOIN users u2 ON u2.user_id = visits.patient_id
      WHERE visits.visit_id = ?;""";
  private static final String SQL_SELECT_VISITS_BY_DOCTOR_ID = """
       SELECT visit_id, visits.spec_id, spec_name,
      visits.doctor_id, u1.last_name AS doctor_surname, u1.first_name AS doctor_name,
      visits.service_id, service_name, visit_date, visit_time,
      type_payment, visit_state,
      u2.last_name AS patient_surname, u2.first_name AS patient_name
      FROM visits
          JOIN specializations ON specializations.spec_id = visits.spec_id
          JOIN users u1 ON u1.user_id = doctor_id
          JOIN services ON services.service_id = visits.service_id
          JOIN users u2 ON u2.user_id = visits.patient_id
      WHERE visits.doctor_id = ?;""";
  private static final String SQL_SELECT_VISITS_BY_SPECIALIZATION_ID = """
       SELECT visit_id, visits.spec_id, spec_name,
       visits.doctor_id, u1.last_name AS doctor_surname, u1.first_name AS doctor_name,
       visits.service_id, service_name, visit_date, visit_time,
       type_payment, visit_state,
       visits.patient_id, u2.last_name AS patient_surname, u2.first_name AS patient_name  
      FROM visits
          JOIN specializations ON specializations.spec_id = visits.spec_id
          JOIN users u1 ON u1.user_id = doctor_id
          JOIN services ON services.service_id = visits.service_id
          JOIN users u2 ON u2.user_id = visits.patient_id
      WHERE visits.specialization_id = ?;""";
  private static final String SQL_SELECT_VISITS_BY_SERVICE_ID = """
      SELECT  visit_id, visits.spec_id, spec_name,
       visits.doctor_id, u1.last_name AS doctor_surname, u1.first_name AS doctor_name,
       visits.service_id, service_name, visit_date, visit_time,
       type_payment, visit_state,
       visits.patient_id, u2.last_name AS patient_surname, u2.first_name AS patient_name
      FROM visits
          JOIN specializations ON specializations.spec_id = visits.spec_id
          JOIN users u1 ON u1.user_id = doctor_id
          JOIN services ON services.service_id = visits.service_id
          JOIN users u2 ON u2.user_id = visits.patient_id
      WHERE visits.service_id = ?;""";
  private static final String SQL_SELECT_VISITS_BY_VISIT_DATE = """
      SELECT visit_id, visits.spec_id, spec_name,
       visits.doctor_id, u1.last_name AS doctor_surname, u1.first_name AS doctor_name,
       visits.service_id, service_name, visit_date, visit_time,
       type_payment, visit_state,
       visits.patient_id, u2.last_name AS patient_surname, u2.first_name AS patient_name 
                  
      FROM visits
          JOIN specializations ON specializations.spec_id = visits.spec_id
          JOIN users u1 ON u1.user_id = doctor_id
          JOIN services ON services.service_id = visits.service_id
          JOIN users u2 ON u2.user_id = visits.patient_id
      WHERE visits.visit_date = ?;""";
  private static final String SQL_SELECT_VISITS_BY_DOCTOR_ID_AND_VISIT_DATE = """
       SELECT visit_id, visits.spec_id, spec_name,
       visits.doctor_id, u1.last_name AS doctor_surname, u1.first_name AS doctor_name,
       visits.service_id, service_name, visit_date, visit_time,
       type_payment, visit_state,
       visits.patient_id, u2.last_name AS patient_surname, u2.first_name AS patient_name 
      FROM visits
          JOIN specializations ON specializations.spec_id = visits.spec_id
          JOIN users u1 ON u1.user_id = doctor_id
          JOIN services ON services.service_id = visits.service_id
          JOIN users u2 ON u2.user_id = visits.patient_id
      WHERE visits.doctor_id = ? AND visits.visit_date = ?;""";
  private static final String SQL_SELECT_VISITS_BY_SPECIALIZATION_ID_AND_VISIT_DATE = """
       SELECT visit_id, visits.spec_id, spec_name,
       visits.doctor_id, u1.last_name AS doctor_surname, u1.first_name AS doctor_name,
       visits.service_id, service_name, visit_date, visit_time,
       type_payment, visit_state,
       visits.patient_id, u2.last_name AS patient_surname, u2.first_name AS patient_name 
      FROM visits
          JOIN specializations ON specializations.spec_id = visits.spec_id
          JOIN users u1 ON u1.user_id = doctor_id
          JOIN services ON services.service_id = visits.service_id
          JOIN users u2 ON u2.user_id = visits.patient_id
      WHERE visits.specialization_id = ? AND visits.visit_date = ?;""";
  private static final String SQL_SELECT_VISITS_BY_SERVICE_ID_AND_VISIT_DATE = """
       SELECT visit_id, visits.spec_id, spec_name,
       visits.doctor_id, u1.last_name AS doctor_surname, u1.first_name AS doctor_name,
       visits.service_id, service_name, visit_date, visit_time,
       type_payment, visit_state,
       visits.patient_id, u2.last_name AS patient_surname, u2.first_name AS patient_name 
      FROM visits
          JOIN specializations ON specializations.spec_id = visits.spec_id
          JOIN users u1 ON u1.user_id = doctor_id
          JOIN services ON services.service_id = visits.service_id
          JOIN users u2 ON u2.user_id = visits.patient_id
      WHERE visits.service_id = ? AND visits.visit_date = ?;""";
  private static final String SQL_SELECT_VISITS_BY_TYPE_PAYMENT = """
       SELECT visit_id, visits.spec_id, spec_name,
       visits.doctor_id, u1.last_name AS doctor_surname, u1.first_name AS doctor_name,
       visits.service_id, service_name, visit_date, visit_time, 
       type_payment, visit_state,
       visits.patient_id, u2.last_name AS patient_surname, u2.first_name AS patient_name 
      FROM visits
          JOIN specializations ON specializations.spec_id = visits.spec_id
          JOIN users u1 ON u1.user_id = doctor_id
          JOIN services ON services.service_id = visits.service_id
          JOIN users u2 ON u2.user_id = visits.patient_id
      WHERE visits.type_payment = ?;""";
  private static final String SQL_SELECT_VISITS_BY_VISIT_STATE = """
         SELECT visit_id, visits.spec_id, spec_name,
       visits.doctor_id, u1.last_name AS doctor_surname, u1.first_name AS doctor_name,
       visits.service_id, service_name, visit_date, visit_time, 
       type_payment, visit_state,
       visits.patient_id, u2.last_name AS patient_surname, u2.first_name AS patient_name 
          JOIN specializations ON specializations.spec_id = visits.spec_id
          JOIN users u1 ON u1.user_id = doctor_id
          JOIN services ON services.service_id = visits.service_id
          JOIN users u2 ON u2.user_id = visits.patient_id
      WHERE visits.visit_state = ?;""";
  private static final String SQL_SELECT_VISITS_BY_PATIENT_ID = """
       SELECT visit_id, visits.spec_id, spec_name,
       visits.doctor_id, u1.last_name AS doctor_surname, u1.first_name AS doctor_name,
       visits.service_id, service_name, visit_date, visit_time,
       type_payment, visit_state,
       visits.patient_id, u2.last_name AS patient_surname, u2.first_name AS patient_name 
      FROM visits
          JOIN specializations ON specializations.spec_id = visits.spec_id
          JOIN users u1 ON u1.user_id = doctor_id
          JOIN services ON services.service_id = visits.service_id
          JOIN users u2 ON u2.user_id = visits.patient_id
      WHERE visits.patient_id = ?;""";
  private static final String SQL_DELETE_VISIT_BY_ID = """
      DELETE FROM visits WHERE visit_id = (?)""";
  private static final String SQL_INSERT_NEW_VISIT = """
      INSERT INTO visits (spec_id, doctor_id, service_id, visit_date, visit_time, 
      type_payment, visit_state, patient_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)""";
  private static final String SQL_UPDATE_VISIT_DATE_AND_TIME_BY_VISIT_ID = """
      UPDATE visits SET visit_date = ?, visit_time = ? WHERE visit_id = ?;""";
  private static final String SQL_UPDATE_VISIT_TIME_BY_VISIT_ID = """
      UPDATE visits SET visit_time = ? WHERE visit_id = ?;""";
  private static final String SQL_UPDATE_VISIT_STATE_BY_VISIT_ID = """
      UPDATE visits SET visit_state = ? WHERE visit_id = ?;""";

  private static final String SQL_SELECT_FREE_TIME_SLOTS_BY_DATE_AND_DOCTOR_ID = """
      SELECT time_slots.slot
      FROM time_slots
      WHERE slot NOT IN (
      SELECT visit_time
      FROM visits
      WHERE visit_date=? AND doctor_id = ? AND visit_state = ?)
      AND slot BETWEEN ? AND ?""";


  @Override
  public List<Visit> findAll() throws DaoException {
    List<Visit> listVisit = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_VISITS);
        ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        Optional<Visit> optionalVisit = new VisitMapper().mapEntity(resultSet);
        if (optionalVisit.isPresent()) {
          listVisit.add(optionalVisit.get());
        }
      }
    } catch (SQLException e) {
      logger.log(Level.ERROR, "Failed to select all the visits", e);
      throw new DaoException("Failed to select all the visits", e);
    }
    return listVisit;
  }

  @Override
  public Optional<Visit> findEntityById(long id) throws DaoException {
    Optional<Visit> visit = Optional.empty();
    try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_VISIT_BY_ID)) {
      statement.setLong(1, id);
      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          visit = new VisitMapper().mapEntity(resultSet);
        }
      }
    } catch (SQLException e) {
      logger.log(Level.ERROR, "Failed to select a visit by id", e);
      throw new DaoException("Failed to select a visit by id", e);
    }
    return visit;
  }

  @Override
  public List<Visit> findVisitsByPatientId(long patientId) throws DaoException {
    List<Visit> listVisit = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(
        SQL_SELECT_VISITS_BY_PATIENT_ID)) {
      statement.setLong(1, patientId);
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          Optional<Visit> optionalVisit = new VisitMapper().mapEntity(resultSet);
          if (optionalVisit.isPresent()) {
            listVisit.add(optionalVisit.get());
          }
        }
      }
    } catch (SQLException e) {
      logger.log(Level.ERROR, "Failed to select all the visits by patient id", e);
      throw new DaoException("Failed to select all the visits by patient id", e);
    }
    return listVisit;
  }

  @Override
  public boolean delete(long id) throws DaoException {
    try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_VISIT_BY_ID)) {
      statement.setLong(1, id);
      int update = statement.executeUpdate();
      return update > 0;
    } catch (SQLException e) {
      logger.log(Level.ERROR, "Failed to delete the visit info by id", e);
      throw new DaoException("Failed to delete the visit by id", e);
    }
  }

  @Override
  public boolean delete(Visit entity) throws DaoException {
    try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_VISIT_BY_ID)) {
      statement.setLong(1, entity.getVisitId());
      int update = statement.executeUpdate();
      return update > 0;
    } catch (SQLException e) {
      logger.log(Level.ERROR, "Failed to delete the visit", e);
      throw new DaoException("Failed to delete the visit", e);
    }
  }

  @Override
  public long create(Visit entity) throws DaoException {
    long visitId = 0;
    try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_VISIT,
        Statement.RETURN_GENERATED_KEYS)) {
      statement.setLong(1, entity.getSpecialization().getSpecializationId());
      statement.setLong(2, entity.getDoctor().getUserId());
      statement.setLong(3, entity.getService().getServiceId());
      statement.setDate(4, Date.valueOf(entity.getDate().toString()));
      statement.setInt(5, entity.getTime());
      statement.setString(6, entity.getTypeOfPayment().getTypePaymentString().toUpperCase());
      statement.setString(7, entity.getState().getStateString().toUpperCase());
      statement.setLong(8, entity.getPatient().getUserId());
      int isUpdated = statement.executeUpdate();
      try (ResultSet resultSet = statement.getGeneratedKeys();) {
        if (resultSet.next()) {
          visitId = resultSet.getLong(1);
          entity.setVisitId(visitId);
        }
      }
      logger.log(Level.DEBUG, "The visit with id " + visitId + " was added to database ");
    } catch (SQLException e) {
      logger.log(Level.ERROR, "Failed to insert the visit", e);
      throw new DaoException("Failed to insert the visit", e);
    }
    return visitId;
  }

  @Override
  public boolean updateVisitDateAndTime(long visitId, LocalDate visitDate, int time)
      throws DaoException {
    boolean isUpdated;
    try (PreparedStatement statement = connection.prepareStatement(
        SQL_UPDATE_VISIT_DATE_AND_TIME_BY_VISIT_ID)) {
      statement.setDate(1, Date.valueOf(visitDate.toString()));
      statement.setInt(2, time);
      statement.setLong(3, visitId);
      isUpdated = (statement.executeUpdate() == 1);
    } catch (SQLException e) {
      logger.log(Level.ERROR, "Failed to update visit date and time", e);
      throw new DaoException("Failed to update visit date and time", e);
    }
    return isUpdated;
  }

  @Override
  public List<Visit> findAllByDoctorId(long doctorId) throws DaoException {
    return null;
  }

  @Override
  public List<Visit> findAllBySpecializationId(long specializationId) throws DaoException {
    return null;
  }

  @Override
  public List<Visit> findAllByServiceId(long serviceId) throws DaoException {
    return null;
  }

  @Override
  public List<Visit> findAllByVisitDate(LocalDate date) throws DaoException {
    return null;
  }

  @Override
  public List<Visit> findAllByPaymentType(Visit.TypePayment paymentType) throws DaoException {
    return null;
  }

  @Override
  public boolean updateVisitStateByVisitId(long visitId, Visit.VisitState visitState)
      throws DaoException {
    boolean isUpdated;
    try (PreparedStatement statement = connection.prepareStatement(
        SQL_UPDATE_VISIT_STATE_BY_VISIT_ID)) {
      statement.setString(1, visitState.getStateString());
      statement.setLong(2, visitId);
      isUpdated = (statement.executeUpdate() == 1);
    } catch (SQLException e) {
      logger.log(Level.ERROR, "Failed to update visit state", e);
      throw new DaoException("Failed to update visit state", e);
    }
    return isUpdated;
  }

  @Override
  public List<Integer> findAvailableTime(LocalDate visitDate, long doctorId) throws DaoException {
    int startTime = START_OF_WORKING_DAY;
    int endTime = FINISH_OF_WORKING_DAY;
    String visitState = "NEW";
    List<Integer> availableSlots = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(
        SQL_SELECT_FREE_TIME_SLOTS_BY_DATE_AND_DOCTOR_ID)) {
      statement.setDate(1, Date.valueOf(visitDate.toString()));
      statement.setLong(2, doctorId);
      statement.setString(3, visitState);
      statement.setInt(4, startTime);
      statement.setInt(5, endTime);
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          availableSlots.add(resultSet.getInt(1));
        }
      }
    } catch (SQLException e) {
      logger.log(Level.ERROR, "Failed to select all the available slots", e);
      throw new DaoException("Failed to select all the available slots", e);
    }
    return availableSlots;
  }
}
