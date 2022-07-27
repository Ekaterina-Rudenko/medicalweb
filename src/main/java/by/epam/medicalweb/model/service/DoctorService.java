package by.epam.medicalweb.model.service;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.Doctor;

import java.util.List;
import java.util.Map;

public interface DoctorService {
    boolean updateDoctorPhoto(String photoPath, long doctorId) throws ServiceException, ConnectionPoolException;

    Doctor findDoctorInfoById(long doctorId) throws ServiceException, ConnectionPoolException;

    boolean addDoctor(Map<String, String> data) throws ServiceException, ConnectionPoolException;

    List<Doctor> findAllDoctors() throws ConnectionPoolException;

    List<Doctor> findDoctorsBySpecializationId(long specId) throws ConnectionPoolException, ServiceException;
}
