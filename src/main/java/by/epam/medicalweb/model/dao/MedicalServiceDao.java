package by.epam.medicalweb.model.dao;

import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.model.entity.MedicalService;

import java.math.BigDecimal;
import java.util.List;

public interface MedicalServiceDao {
    boolean updateServicePrice(long id, BigDecimal price) throws DaoException;
    List<MedicalService> findServicesBySpecializationId(long specializationId) throws DaoException;
}
