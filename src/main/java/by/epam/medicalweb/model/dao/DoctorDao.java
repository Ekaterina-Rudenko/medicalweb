package by.epam.medicalweb.model.dao;

import by.epam.medicalweb.model.entity.Doctor;
import by.epam.medicalweb.exception.DaoException;

import java.util.List;

public interface DoctorDao {
    List<Doctor> findAllByCategory(Doctor.Category category) throws DaoException;

}
