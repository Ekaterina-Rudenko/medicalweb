package by.epam.medicalweb.model.dao;

import by.epam.medicalweb.model.entity.Doctor;
import by.epam.medicalweb.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface DoctorDao {
    List<Doctor> findDoctorsByCategory(String category) throws DaoException;
    List<Doctor> findDoctorsBySpecializationId(String specializationId) throws DaoException;
    Optional<Doctor> findDoctorInfoById (long id) throws DaoException;
    List<Doctor> findDoctorsByLastName (String lastName) throws DaoException;
    boolean updateDoctorPhotoById(String photo, long id) throws DaoException;
    boolean updateDoctorSpecializationById(long specializationId, long doctorId) throws DaoException;
    boolean updateDoctorCategoryById(String category, long id) throws DaoException;
    boolean insertDoctorAdditionalInfoById(long id, String category,String photo, long specializationId) throws DaoException;
}
