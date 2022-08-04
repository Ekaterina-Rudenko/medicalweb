package by.epam.medicalweb.model.service;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.Patient;

import java.math.BigDecimal;
import java.util.Map;

public interface PatientService {
    boolean registerPatient(Map<String, String> data) throws ServiceException, ConnectionPoolException;
    Patient findPatientInfoById(long patientId) throws ServiceException, ConnectionPoolException;
    boolean topUpBalance(long patientId, BigDecimal money) throws ServiceException, ConnectionPoolException;
}
