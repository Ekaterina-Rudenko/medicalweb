package dao;

import entity.Doctor;
import exception.DaoException;

import java.util.List;

public interface DoctorDao {
    List<Doctor> findAllByCategory(Doctor.Category category) throws DaoException;

}
