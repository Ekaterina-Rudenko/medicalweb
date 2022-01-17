package by.epam.medicalweb.model.dao.impl;

import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.PatientDao;
import by.epam.medicalweb.model.entity.Patient;
import by.epam.medicalweb.model.mapper.impl.PatientMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDaoImpl extends AbstractDao<Patient> implements PatientDao {
    private static Logger logger = LogManager.getLogger();
    private static final String SQL_FIND_PATIENT_BY_ID = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone, user_state, user_role, registration_date, gender, birthdate, balance
            FROM users 
            JOIN patients ON users.user_id = patients.patient_id
            WHERE user_id = (?);""";
    private static final String SQL_FIND_PATIENT_INFO_BY_ID = """
            SELECT gender, birthdate, balance
            FROM patients
            WHERE patient_id = (?);""";
    private static final String SQL_FIND_ALL_PATIENTS = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone, user_state, user_role, registration_date, gender, birthdate, balance
            FROM users 
            JOIN patients ON users.user_id = patients.patient_id;""";
    private static final String SQL_INSERT_PATIENT_INFO = """
            INSERT INTO patients (patient_id, gender, birthdate)
            VALUES (?, ?, ?);""";

    private static final String SQL_FIND_PATIENT_BY_LAST_NAME = """
            SELECT  user_id, first_name, middle_name, last_name,email, phone, user_state, user_role, gender, birthdate, balance
            FROM users
            JOIN patients ON users.user_id = patients.patient_id
            WHERE last_name = (?);""";
    private static final String SQL_DELETE_PATIENT_INFO_BY_ID = """
            DELETE
            FROM patients
            WHERE patient_id = ?;""";
    private static final String SQL_DELETE_PATIENT_BY_ID = """
            DELETE patients, users
            FROM patients JOIN users
            ON users.user_id = patients.patient_id
            WHERE patient_id = (?);""";
    private static final String SQL_UPDATE_BALANCE_BY_ID = """
            UPDATE patients SET balance = (?) WHERE patient_id = (?); """;

    @Override
    public List<Patient> findAll() throws DaoException {
        List<Patient> listPatient = new ArrayList<>();
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_FIND_ALL_PATIENTS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Optional<Patient> optionalPatient = new PatientMapper().mapEntity(resultSet);
                if (optionalPatient.isPresent()) {
                    listPatient.add(optionalPatient.get());
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to select all patients", e);
            throw new DaoException("Failed to select all patients", e);
        }
        return listPatient;
    }

    @Override
    public Optional<Patient> findEntityById(long id) throws DaoException {
        Optional<Patient> patient = Optional.empty();
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_FIND_PATIENT_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    patient = new PatientMapper().mapEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to find patient by id", e);
            throw new DaoException("Failed to find patient by id", e);
        }
        return patient;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_DELETE_PATIENT_INFO_BY_ID)){
            statement.setLong(1, id);
            int update = statement.executeUpdate();
            return (update > 0) ? true : false;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to delete the patient's info by id", e);
            throw new DaoException("Failed to delete the patient's info by id", e);
        }
    }

    @Override
    public boolean delete(Patient entity) throws DaoException {
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_DELETE_PATIENT_INFO_BY_ID)) {
            statement.setLong(1, entity.getUserId());
            int update = statement.executeUpdate();
            return (update > 0) ? true : false;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to delete the patient's info", e);
            throw new DaoException("Failed to delete the patient's info", e);
        }
    }

    @Override
    public long create(Patient entity) throws DaoException {
        long patientId = 0;
        try (PreparedStatement statement = proxyConnection.prepareStatement(SQL_INSERT_PATIENT_INFO)) {
            statement.setLong(1, entity.getUserId());
            statement.setString(2, entity.getGender().toString());
            statement.setDate(3, Date.valueOf(entity.getBirthDate()));
            int isUpdated = statement.executeUpdate();
            if (isUpdated == 1) {
                patientId = entity.getUserId();
                logger.log(Level.DEBUG, "Info for patient with id " + patientId + " was added to database ");
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to insert the patient's info", e);
            throw new DaoException("Failed to insert the patient's info", e);
        }
        return patientId;
    }

    @Override
    public boolean updatePatientBalance(long patientId, BigDecimal money) throws DaoException {
        boolean isUpdated;
        try (PreparedStatement statement = proxyConnection.prepareStatement(SQL_UPDATE_BALANCE_BY_ID)) {
            statement.setBigDecimal(1, money);
            statement.setLong(2, patientId);
            isUpdated = (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to update balance", e);
            throw new DaoException("Failed to update balance", e);
        }
        return isUpdated;
    }

    @Override
    public List<Patient> findPatientsByLastName(String lastName) throws DaoException {
        List<Patient> listPatient = new ArrayList<>();
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_FIND_PATIENT_BY_LAST_NAME)) {
            statement.setString(1, lastName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Patient> optionalPatient = new PatientMapper().mapEntity(resultSet);
                    if (optionalPatient.isPresent()) {
                        listPatient.add(optionalPatient.get());
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to select patients by last name", e);
            throw new DaoException("Failed to select patients by last name", e);
        }
        return listPatient;
    }
    @Override
    public Optional<Patient> findPatientInfoById(long id) throws DaoException {
        Optional<Patient> patient = Optional.empty();
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_FIND_PATIENT_INFO_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    patient = new PatientMapper().mapEntityPatientInfo(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to find patient info by id", e);
            throw new DaoException("Failed to find patient info by id", e);
        }
        return patient;
    }
}
