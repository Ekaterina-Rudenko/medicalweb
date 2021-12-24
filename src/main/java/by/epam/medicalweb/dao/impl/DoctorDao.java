package by.epam.medicalweb.dao.impl;

import by.epam.medicalweb.dao.AbstractDao;
import by.epam.medicalweb.entity.Doctor;
import by.epam.medicalweb.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao extends AbstractDao<Doctor> implements by.epam.medicalweb.dao.DoctorDao {
    static Logger logger = LogManager.getLogger();
    private static final String SQL_FIND_DOCTOR_BY_ID = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone,
                    user_state, user_role, registration_date, category, experience, doctor_photo
            FROM users JOIN doctors
            ON users.user_id = doctors.doctor_id
            WHERE user_id = (?);""";
    private static final String SQL_FIND_ALL_DOCTORS = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone,
                    user_state, user_role, registration_date, category, experience, doctor_photo, specialization_id
            FROM users JOIN doctors
            ON users.user_id = doctors.doctor_id;""";
    private static final String SQL_CREATE_USER = """
            INSERT into users(first_name, middle_name, last_name, login, password, user_state, user_role) 
            VALUES (?, ?, ?, ?, ?, ?, ? );""";
    private static final String SQL_CREATE_DOCTOR = """
            INSERT INTO doctors (doctor_id, category, experience, specialization_id)
            VALUES (?, ?, ?, ? );""";
    private static final String SQL_FIND_DOCTORS_BY_SPECIALIZATION = """
            SELECT  user_id, first_name, middle_name, last_name,email, phone,
                    user_state, user_role, category, experience, doctor_photo,
                   spec_name
            FROM users
                JOIN doctors
                    ON users.user_id = doctors.doctor_id
            JOIN specializations s
                ON doctors.specialization_id = s.spec_id
            WHERE spec_name = (?);""";
    private static final String SQL_FIND_DOCTORS_BY_CATEGORY = """
            SELECT  user_id, first_name, middle_name, last_name,email, phone,
                    user_state, user_role, category, experience, doctor_photo
            FROM users
            JOIN doctors
              ON users.user_id = doctors.doctor_id
              WHERE category = (?);""";
    private static final String SQL_FIND_DOCTOR_BY_LAST_NAME = """
            SELECT  user_id, first_name, middle_name, last_name,email, phone,
                        user_state, user_role, category, experience, doctor_photo
                FROM users
                JOIN doctors
                  ON users.user_id = doctors.doctor_id
                  WHERE last_name = (?);""";

    private static final String SQL_DELETE_DOCTOR_BY_ID = """
            DELETE doctors, users
            FROM doctors JOIN users
            ON users.user_id = doctors.doctor_id
            WHERE doctor_id = (?);""";

    private static final String SQL_UPDATE_DOCTOR_PHOTO_BY_ID = """
            UPDATE doctors SET doctor_photo = (?) WHERE doctor_id = (?); """;

    private static final String SQL_UPDATE_DOCTOR_CATEGORY_BY_ID = """
            UPDATE doctors SET category = (?) WHERE doctor_id = (?);""";

    public DoctorDao(){
        this.connection;
    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> listDoctor = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = this.connection.prepareStatement(SQL_FIND_ALL_DOCTORS);
            ResultSet resultSet = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
