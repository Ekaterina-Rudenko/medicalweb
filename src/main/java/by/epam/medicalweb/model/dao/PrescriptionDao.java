package by.epam.medicalweb.model.dao;

import by.epam.medicalweb.exception.DaoException;

public interface PrescriptionDao {
    boolean updatePrescription(long id, String text) throws DaoException;
}
