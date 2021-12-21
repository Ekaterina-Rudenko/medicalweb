package by.epam.medicalweb.dao.impl;

import by.epam.medicalweb.dao.AbstractDao;
import by.epam.medicalweb.entity.Doctor;
import by.epam.medicalweb.exception.DaoException;

import java.util.List;

public class DoctorDao extends AbstractDao<Doctor> implements by.epam.medicalweb.dao.DoctorDao {
    private static final String SQL_FIND_DOCTOR_BY_ID = """
    SELECT user_id, first_name, middle_name, last_name, login, password, email, phone,
            user_state, user_role, registration_date, category, experience, doctor_photo
    FROM users JOIN doctors
    ON users.user_id = doctors.doctor_id
    WHERE user_id = ?;""";
    private static final String SQL_FIND_ALL_DOCTORS = """
    SELECT user_id, first_name, middle_name, last_name, login, password, email, phone,
            user_state, user_role, registration_date, category, experience, doctor_photo, specialization_id
    FROM users JOIN doctors
    ON users.user_id = doctors.doctor_id""";
    private static final String SQL_CREATE_USER = """
            INSERT into users(first_name, middle_name, last_name, login, password, user_state, user_role) 
            VALUES (?, ?, ?, ?, ?, ?, ? );""";
    private static final String SQL_CREATE_DOCTOR = """
    INSERT INTO doctors (doctor_id, category, experience, specialization_id)
    VALUES (?, ?, ?, ? );""";


    @Override
    public List<Doctor> findAll() {
        return null;
    }

    @Override
    public Doctor findEntityById(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean delete(Doctor entity) {
        return false;
    }

    @Override
    public boolean create(Doctor entity) {
        return false;
    }

    @Override
    public Doctor update(Doctor entity) {
        return null;
    }

    @Override
    public List<Doctor> findAllByCategory(Doctor.Category category) throws DaoException {
        return null;
    }
}
