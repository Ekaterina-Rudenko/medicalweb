package by.epam.medicalweb.model.service;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.Specialization;

import java.util.List;

public interface SpecializationService {
    List<Specialization> findAll() throws ServiceException, ConnectionPoolException;
}
