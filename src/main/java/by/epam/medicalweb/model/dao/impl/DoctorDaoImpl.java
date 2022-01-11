package by.epam.medicalweb.model.dao.impl;

import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.DoctorDao;
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

public class DoctorDaoImpl extends AbstractDao<Doctor> implements DoctorDao {
    static Logger logger = LogManager.getLogger();

    private static final String SQL_FIND_DOCTOR_BY_ID = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone, user_state, user_role, registration_date, category, doctor_photo, specialization_id
            FROM users 
            JOIN doctors ON users.user_id = doctors.doctor_id
            WHERE user_id = (?);""";

    private static final String SQL_FIND_DOCTOR_INFO_BY_ID = """
            SELECT category, doctor_photo, specialization_id
            FROM doctors
            WHERE doctor_id = (?);""";

    private static final String SQL_FIND_ALL_DOCTORS = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone, user_state, user_role, registration_date, category, doctor_photo, specialization_id
            FROM users 
            JOIN doctors ON users.user_id = doctors.doctor_id;""";

    private static final String SQL_INSERT_DOCTOR_INFO = """
            INSERT INTO doctors (doctor_id, category, doctor_photo, specialization_id)
            VALUES (?, ?, ? , ?);""";

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
    private static final String SQL_DELETE_DOCTOR_INFO_BY_ID = """
            DELETE
            FROM doctors
            WHERE doctor_id = ?;""";
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
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_FIND_ALL_DOCTORS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Doctor> optionalDoctor = new DoctorMapper().mapEntity(resultSet);
                    if (optionalDoctor.isPresent()) {
                        listDoctor.add(optionalDoctor.get());
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to select all the doctors", e);
            throw new DaoException("Failed to select all the doctors", e);
        }
        return listDoctor;
    }

    @Override
    public Optional<Doctor> findEntityById(long id) throws DaoException {
        Optional<Doctor> doctor = Optional.empty();
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_FIND_DOCTOR_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    doctor = new DoctorMapper().mapEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to select a doctor by id", e);
            throw new DaoException("Failed to select a doctor by id", e);
        }
        return doctor;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_DELETE_DOCTOR_INFO_BY_ID)) {
            statement.setLong(1, id);
            int update = statement.executeUpdate();
            return (update > 0) ? true : false;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to delete the doctor info by id", e);
            throw new DaoException("Failed to delete the doctor info by id", e);
        }
    }

    @Override
    public boolean delete(Doctor doctor) throws DaoException {
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_DELETE_DOCTOR_INFO_BY_ID)) {
            statement.setLong(1, doctor.getUserId());
            int update = statement.executeUpdate();
            return (update > 0) ? true : false;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to delete the doctor's info", e);
            throw new DaoException("Failed to delete the doctor's info", e);
        }
    }

    @Override
    public long create(Doctor entity) throws DaoException {
        long doctorId = 0;
        try (PreparedStatement statement = proxyConnection.prepareStatement(SQL_INSERT_DOCTOR_INFO)) {
            statement.setLong(1, entity.getUserId());
            statement.setString(2, entity.getCategory().toString());
            statement.setString(3, entity.getPhotoPath());
            statement.setLong(4, entity.getSpecializationId());
            int isUpdated = statement.executeUpdate();
            if (isUpdated == 1) {
                doctorId = entity.getUserId();
                logger.log(Level.DEBUG, "Info for doctor with id " + doctorId + " was added to database ");
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to insert the doctor's info", e);
            throw new DaoException("Failed to insert the doctor's info", e);
        }
        return doctorId;
    }

    @Override
    public List<Doctor> findDoctorsByCategory(String category) throws DaoException {
        List<Doctor> listDoctor = new ArrayList<>();
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_FIND_DOCTORS_BY_CATEGORY)) {
            statement.setString(1, category);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Doctor> optionalDoctor = new DoctorMapper().mapEntity(resultSet);
                    if (optionalDoctor.isPresent()) {
                        listDoctor.add(optionalDoctor.get());
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to select doctors by category", e);
            throw new DaoException("Failed to select doctors by category", e);
        }
        return listDoctor;
    }

    @Override
    public List<Doctor> findDoctorsBySpecializationId(String specializationId) throws DaoException {
        List<Doctor> listDoctor = new ArrayList<>();
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_FIND_DOCTORS_BY_SPECIALIZATION)) {
            statement.setString(1, specializationId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Doctor> optionalDoctor = new DoctorMapper().mapEntity(resultSet);
                    if (optionalDoctor.isPresent()) {
                        listDoctor.add(optionalDoctor.get());
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to select doctors by specialization", e);
            throw new DaoException("Failed to select doctors by specialization", e);
        }
        return listDoctor;
    }

    @Override
    public Optional<Doctor> findDoctorInfoById(long id) throws DaoException {
        Optional<Doctor> doctor = Optional.empty();
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_FIND_DOCTOR_INFO_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    doctor = new DoctorMapper().mapEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to find doctor info by id", e);
            throw new DaoException("Failed to find doctor info by id", e);
        }
        return doctor;
    }

    @Override
    public List<Doctor> findDoctorsByLastName(String lastName) throws DaoException {
        List<Doctor> listDoctor = new ArrayList<>();
        try (PreparedStatement statement = this.proxyConnection.prepareStatement(SQL_FIND_DOCTOR_BY_LAST_NAME)) {
            statement.setString(1, lastName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Doctor> optionalDoctor = new DoctorMapper().mapEntity(resultSet);
                    if (optionalDoctor.isPresent()) {
                        listDoctor.add(optionalDoctor.get());
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to select doctors by last name", e);
            throw new DaoException("Failed to select doctors by last name", e);
        }
        return listDoctor;
    }

    @Override
    public boolean updateDoctorPhotoById(String photo, long id) throws DaoException {
        boolean isUpdated;
        try (PreparedStatement statement = proxyConnection.prepareStatement(SQL_UPDATE_DOCTOR_PHOTO_BY_ID)) {
            statement.setString(1, photo);
            statement.setLong(2, id);
            isUpdated = (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to update doctor photo", e);
            throw new DaoException("Failed to update doctor photo", e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateDoctorSpecializationById(long specializationId, long doctorId) throws DaoException {
        boolean isUpdated;
        try (PreparedStatement statement = proxyConnection.prepareStatement(SQL_UPDATE_DOCTOR_SPECIALIZATION_BY_ID)) {
            statement.setLong(1, specializationId);
            statement.setLong(2, doctorId);
            isUpdated = (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to update doctor specialization", e);
            throw new DaoException("Failed to update doctor specialization", e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateDoctorCategoryById(String category, long id) throws DaoException {
        boolean isUpdated;
        try (PreparedStatement statement = proxyConnection.prepareStatement(SQL_UPDATE_DOCTOR_CATEGORY_BY_ID)) {
            statement.setString(1, category);
            statement.setLong(2, id);
            isUpdated = (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to update doctor category", e);
            throw new DaoException("Failed to update doctor category", e);
        }
        return isUpdated;
    }

    @Override
    public boolean insertDoctorAdditionalInfoById(long id, String category, String photo, long specializationId) throws DaoException {
        boolean isInserted = false;
        try (PreparedStatement statement = proxyConnection.prepareStatement(SQL_INSERT_DOCTOR_INFO)) {
            statement.setLong(1, id);
            statement.setString(2, category);
            statement.setString(3, photo);
            statement.setLong(4, specializationId);
            isInserted = (statement.executeUpdate() == 1);
            if(isInserted) {
                logger.log(Level.DEBUG, "Info for doctor with id " + id + " was added to database ");
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to insert the doctor's info", e);
            throw new DaoException("Failed to insert the doctor's info", e);
        }
        return isInserted;
    }

}
