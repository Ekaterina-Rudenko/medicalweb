package by.epam.medicalweb.model.dao.impl;

import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.MedicalServiceDao;
import by.epam.medicalweb.model.entity.MedicalService;
import by.epam.medicalweb.model.mapper.impl.MedicalServiceMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicalServiceDaoImpl extends AbstractDao<MedicalService> implements MedicalServiceDao {
    private static Logger logger = LogManager.getLogger();
    private static final String SQL_FIND_ALL_SERVICES = """
            SELECT service_id, spec_name, service_name, price 
            FROM services
            JOIN specializations ON specializations.spec_id = services.spec_id""";
    private static final String SQL_FIND_SERVICE_BY_ID = """
            SELECT service_id, spec_name, service_name, price 
            FROM services
            JOIN specializations ON specializations.spec_id = services.spec_id
            WHERE service_id = ?""";
    private static final String SQL_DELETE_MEDICAL_SERVICE_BY_ID = """
            DELETE
            FROM services
            WHERE service_id = ?""";
    private static final String SQL_INSERT_MEDICAL_SERVICE = """
            INSERT INTO services (spec_id, service_name, price )
            VALUES (?, ?, ?);""";
    private static final String SQL_UPDATE_SERVICE_PRICE_BY_ID = """
            UPDATE services SET price = ?
            WHERE service_id = ?;""";
    private static final String SQL_FIND_SERVICES_BY_SPECIALIZATION_ID = """
            SELECT service_id, spec_id, spec_name, service_name, price 
            FROM services
            JOIN specializations ON specializations.spec_id = services.spec_id
            WHERE spec_id = ?""";

    @Override
    public List<MedicalService> findAll() throws DaoException {
        List<MedicalService> listServices = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_SERVICES);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Optional<MedicalService> optionalService = new MedicalServiceMapper().mapEntity(resultSet);
                if (optionalService.isPresent()) {
                    listServices.add(optionalService.get());
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to select all medical services", e);
            throw new DaoException("Failed to select all medical services", e);
        }
        return listServices;
    }

    @Override
    public Optional<MedicalService> findEntityById(long id) throws DaoException {
        Optional<MedicalService> optionalMedicalService = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_SERVICE_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalMedicalService = new MedicalServiceMapper().mapEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to select medical service by id", e);
            throw new DaoException("Failed to select medical service by id", e);
        }
        return optionalMedicalService;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_MEDICAL_SERVICE_BY_ID)) {
            statement.setLong(1, id);
            int update = statement.executeUpdate();
            return (update > 0) ? true : false;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to delete medical service by id", e);
            throw new DaoException("Failed to delete medical service by id", e);
        }
    }

    @Override
    public boolean delete(MedicalService entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_MEDICAL_SERVICE_BY_ID)) {
            statement.setLong(1, entity.getServiceId());
            int update = statement.executeUpdate();
            return (update > 0) ? true : false;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to delete medical service: " + entity.getServiceName(), e);
            throw new DaoException("Failed to delete medical service: " + entity.getServiceName(), e);
        }
    }

    @Override
    public long create(MedicalService entity) throws DaoException, SQLException {
        long medicalServiceId = 0;
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_MEDICAL_SERVICE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, entity.getSpecialization().getSpecializationId());
            statement.setString(2, entity.getServiceName());
            statement.setBigDecimal(3, entity.getPrice());
            int isUpdated = statement.executeUpdate();
            if (isUpdated == 1) {
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        medicalServiceId = resultSet.getLong(1);
                        logger.log(Level.DEBUG, "Info for doctor with id " + medicalServiceId + " was added to database ");
                    }
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Failed to insert the doctor's info", e);
                    throw new DaoException("Failed to insert the doctor's info", e);
                }
            }
        }
        return medicalServiceId;
    }

    @Override
    public boolean updateServicePrice(long id, BigDecimal price) throws DaoException {
        boolean isUpdated;
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SERVICE_PRICE_BY_ID)) {
            statement.setLong(1, id);
            statement.setBigDecimal(2, price);
            isUpdated = (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to update service price", e);
            throw new DaoException("Failed to update service price", e);
        }
        return isUpdated;
    }

    public List<MedicalService> findServicesBySpecializationId(long specializationId) throws DaoException {
        List<MedicalService> listServices = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_SERVICES_BY_SPECIALIZATION_ID)) {
            statement.setLong(1, specializationId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<MedicalService> optionalService = new MedicalServiceMapper().mapEntity(resultSet);
                    if (optionalService.isPresent()) {
                        listServices.add(optionalService.get());
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to select medical services by specialization id", e);
            throw new DaoException("Failed to select medical services by specialization id", e);
        }
        return listServices;
    }
}
