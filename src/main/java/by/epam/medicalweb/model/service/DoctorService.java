package by.epam.medicalweb.model.service;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.Doctor;

public interface DoctorService {
    boolean updateDoctorPhoto(String photoPath, long doctorId) throws ServiceException, ConnectionPoolException;
    Doctor findDoctorInfoById(long doctorId) throws ServiceException, ConnectionPoolException;
}
