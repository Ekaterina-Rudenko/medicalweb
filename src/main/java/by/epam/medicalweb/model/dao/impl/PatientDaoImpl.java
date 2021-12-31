package by.epam.medicalweb.model.dao.impl;

import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.PatientDao;
import by.epam.medicalweb.model.entity.Patient;

import java.util.List;
import java.util.Optional;

public class PatientDaoImpl extends AbstractDao<Patient> implements PatientDao {

    @Override
    public List<Patient> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Patient> findEntityById(long id) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Patient entity) throws DaoException {
        return false;
    }

    @Override
    public long create(Patient entity) {
        return 0;
    }

    @Override
    public Patient update(Patient entity) {
        return null;
    }
}
