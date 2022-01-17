package by.epam.medicalweb.model.dao;

import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.model.entity.Patient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PatientDao {
    boolean updatePatientBalance(long patientId, BigDecimal money) throws DaoException;
    List<Patient> findPatientsByLastName(String lastName) throws DaoException;
    Optional<Patient> findPatientInfoById(long id) throws DaoException;
}
