package by.epam.medicalweb.model.dao;

import by.epam.medicalweb.exception.DaoException;

public interface SpecializationDao {
    boolean updateSpecialization(long id, String specName) throws DaoException;
}
