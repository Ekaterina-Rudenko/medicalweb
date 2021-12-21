package by.epam.medicalweb.dao;

import by.epam.medicalweb.entity.Doctor;
import by.epam.medicalweb.exception.DaoException;

import java.util.List;

public interface DoctorDao {
    List<Doctor> findAllByCategory(Doctor.Category category) throws DaoException;

}
