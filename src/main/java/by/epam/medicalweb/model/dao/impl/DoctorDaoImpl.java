package by.epam.medicalweb.model.dao.impl;

import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.EntityTransaction;
import by.epam.medicalweb.model.entity.Doctor;
import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.model.mapper.impl.DoctorMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorDaoImpl extends AbstractDao<Doctor> implements by.epam.medicalweb.model.dao.DoctorDao {
    static Logger logger = LogManager.getLogger();
    private static final String SQL_FIND_DOCTOR_BY_ID = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone, user_state, user_role, registration_date, category, doctor_photo, specialization_id
            FROM users 
            JOIN doctors ON users.user_id = doctors.doctor_id
            WHERE user_id = (?);""";

    private static final String SQL_FIND_ALL_DOCTORS = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone, user_state, user_role, registration_date, category, doctor_photo, specialization_id
            FROM users 
            JOIN doctors ON users.user_id = doctors.doctor_id;""";

    private static final String SQL_INSERT_USER = """
            INSERT INTO users(first_name, middle_name, last_name, login, password, email, phone, user_state, user_role) 
            VALUES (?, ?, ?, ?, ?, ?, ? );""";

    private static final String SQL_INSERT_DOCTOR = """
            INSERT INTO doctors (doctor_id, category, specialization_id)
            VALUES (?, ?, ?, ? );""";

    private static final String SQL_FIND_DOCTORS_BY_SPECIALIZATION = """
            SELECT  user_id, first_name, middle_name, last_name,email, phone, user_state, user_role, category, doctor_photo,spec_name
            FROM users
            JOIN doctors ON users.user_id = doctors.doctor_id
            JOIN specializations ON doctors.specialization_id = s.spec_id
            WHERE spec_name = (?);""";

    private static final String SQL_FIND_DOCTORS_BY_CATEGORY = """
            SELECT  user_id, first_name, middle_name, last_name,email, phone, user_state, user_role, category, doctor_photo
            FROM users
            JOIN doctors ON users.user_id = doctors.doctor_id
            WHERE category = (?);""";

    private static final String SQL_FIND_DOCTOR_BY_LAST_NAME = """
            SELECT  user_id, first_name, middle_name, last_name,email, phone, user_state, user_role, category, doctor_photo
            FROM users
            JOIN doctors ON users.user_id = doctors.doctor_id
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
    private static final String SQL_UPDATE_DOCTOR_SPECIALIZATION_BY_ID = """
            UPDATE doctors SET specialization_id = (?) WHERE doctor_id = (?);""";

    public DoctorDaoImpl() {
    }

    @Override
    public List<Doctor> findAll() throws DaoException {
        List<Doctor> listDoctor = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = this.proxyConnection.prepareStatement(SQL_FIND_ALL_DOCTORS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Optional<Doctor> optionalDoctor = new DoctorMapper().mapEntity(resultSet);
                if (optionalDoctor.isPresent()) {
                    listDoctor.add(optionalDoctor.get());
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to select all the doctors", e);
            throw new DaoException("Failed to select all the doctors", e);
        } finally {
            closeStatement(statement);
        }
        return listDoctor;
    }

    @Override
    public Optional<Doctor> findEntityById(long id) throws DaoException {
        Doctor doctor = new Doctor();
        PreparedStatement statement = null;
        try {
            statement = this.proxyConnection.prepareStatement(SQL_FIND_DOCTOR_BY_ID);
            ResultSet resultSet = statement.executeQuery();
            Optional<Doctor> optionalDoctor = new DoctorMapper().mapEntity(resultSet);
            if (optionalDoctor.isPresent()) {
                doctor = optionalDoctor.get();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to select a doctor by id", e);
            throw new DaoException("Failed to select a doctor by id", e);
        } finally {
            closeStatement(statement);
        }
        return doctor;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = this.proxyConnection.prepareStatement(SQL_DELETE_DOCTOR_BY_ID);
            statement.setLong(1, id);
            int update = statement.executeUpdate();
            return (update > 0) ? true : false;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to delete the doctor by id", e);
            throw new DaoException("Failed to delete the doctor by id", e);
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public boolean delete(Doctor doctor) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = this.proxyConnection.prepareStatement(SQL_DELETE_DOCTOR_BY_ID);
            statement.setLong(1, doctor.getUserId());
            int update = statement.executeUpdate();
            return (update > 0) ? true : false;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to delete the doctor", e);
            throw new DaoException("Failed to delete the doctor", e);
        } finally {
            closeStatement(statement);
        }
    }

    @Override
    public long create(Doctor entity) {
        EntityTransaction entityTransaction;
        PreparedStatement statement;
        entityTransaction = new EntityTransaction();
        entityTransaction.beginTransaction(new UserDaoImpl());

        return 0;
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
