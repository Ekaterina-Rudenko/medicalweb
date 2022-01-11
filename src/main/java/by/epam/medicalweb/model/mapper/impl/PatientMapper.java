package by.epam.medicalweb.model.mapper.impl;

import by.epam.medicalweb.model.entity.Doctor;
import by.epam.medicalweb.model.entity.Patient;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.mapper.BaseMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import static by.epam.medicalweb.model.dao.ColumnName.*;

public class PatientMapper implements BaseMapper<Patient> {
    static Logger logger = LogManager.getLogger();

    @Override
    public Optional<Patient> mapEntity(ResultSet resultSet) {
        Patient patient = new Patient();
        Optional<Patient> optionalPatient;
        try {
            UserMapper.setUserData(resultSet, patient);
            setPatientData(resultSet, patient);
            optionalPatient = Optional.of(patient);
        } catch (SQLException e) {
            logger.log(Level.DEBUG, "Patient entity was not created", e);
            optionalPatient = Optional.empty();
        }
        return optionalPatient;
    }

    public Optional<Patient> mapEntityPartially(ResultSet resultSet) {
        Patient patient = new Patient();
        Optional<Patient> optionalPatient;
        try {
            patient.setUserId(resultSet.getLong(PATIENT_ID));
            setPatientData(resultSet, patient);
            optionalPatient = Optional.of(patient);
        } catch (SQLException e) {
            logger.log(Level.DEBUG, "Patient entity was not created", e);
            optionalPatient = Optional.empty();
        }
        return optionalPatient;
    }

    public Optional<Patient> mapEntityFromUser(User user, ResultSet resultSet) {
        Patient patient = new Patient();
        Optional<Patient> optionalPatient;
        try {
            patient.setFirstName(user.getFirstName());
            patient.setMiddleName(user.getMiddleName());
            patient.setLastName(user.getLastName());
            patient.setLogin(user.getLogin());
            patient.setPassword(user.getPassword());
            patient.setEmail(user.getEmail());
            patient.setPhoneNumber(user.getPhoneNumber());
            patient.setState(user.getState());
            patient.setRole(user.getRole());
            patient.setRegistrationDate(user.getRegistrationDate());
            setPatientData(resultSet, patient);
            optionalPatient = Optional.of(patient);
        } catch (SQLException e) {
            logger.log(Level.DEBUG, "Patient entity was not created", e);
            optionalPatient = Optional.empty();
        }
        return optionalPatient;
    }

    private void setPatientData(ResultSet resultSet, Patient patient) throws SQLException {
        patient.setGender(Patient.Gender.valueOf(resultSet.getString(GENDER).trim().toUpperCase()));
        patient.setBirthDate(resultSet.getDate(BIRTHDATE).toLocalDate());
        patient.setBalance(resultSet.getBigDecimal(BALANCE));
    }
}
