package by.epam.medicalweb.model.dao.impl;

import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.PrescriptionDao;
import by.epam.medicalweb.model.entity.Prescription;
import by.epam.medicalweb.model.mapper.impl.PrescriptionMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrescriptionDaoImpl extends AbstractDao<Prescription> implements PrescriptionDao {
    private static Logger logger = LogManager.getLogger();
    private static final String SQL_FIND_ALL_PRESCRIPTIONS = """
            SELECT prescription_id, visit_id, prescription_text
            FROM prescriptions;""";
    private static final String SQL_FIND_PRESCRIPTION_BY_ID = """
            SELECT prescription_id, visit_id, prescription_text
            FROM prescriptions
            WHERE prescription_id = (?);""";
    private static final String SQL_DELETE_PRESCRIPTION = """
            DELETE
            FROM prescriptions
            WHERE prescription_id = (?);""";
    private static final String SQL_INSERT_PRESCRIPTION = """
            INSERT INTO prescriptions (visit_id, prescription_text)
            VALUES(?, ?)""";
    private static final String SQL_UPDATE_PRESCRIPTION = """
            UPDATE prescriptions SET prescription_text = ?
            WHERE prescription_id = (?)""";

    @Override
    public List<Prescription> findAll() throws DaoException {
        List<Prescription> listSpecialization = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_PRESCRIPTIONS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Optional<Prescription> optionalPrescription = new PrescriptionMapper().mapEntity(resultSet);
                if (optionalPrescription.isPresent()) {
                    listSpecialization.add(optionalPrescription.get());
                }
            }
        } catch (
                SQLException e) {
            logger.log(Level.ERROR, "Failed to select all the prescriptions", e);
            throw new DaoException("Failed to select all the prescriptions", e);
        }
        return listSpecialization;
    }

    @Override
    public Optional<Prescription> findEntityById(long id) throws DaoException {
        Optional<Prescription> prescription = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_PRESCRIPTION_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    prescription = new PrescriptionMapper().mapEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to select prescription by id", e);
            throw new DaoException("Failed to select prescription by id", e);
        }
        return prescription;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_PRESCRIPTION)){
            statement.setLong(1, id);
            int update = statement.executeUpdate();
            return (update > 0) ? true : false;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to delete the prescription", e);
            throw new DaoException("Failed to delete the prescription", e);
        }
    }

    @Override
    public boolean delete(Prescription entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_PRESCRIPTION)) {
            statement.setLong(1, entity.getPrescriptionId());
            int update = statement.executeUpdate();
            return (update > 0) ? true : false;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to delete the prescription", e);
            throw new DaoException("Failed to delete the prescription", e);
        }
    }

    @Override
    public long create(Prescription entity) throws DaoException, SQLException {
        long prescriptionId = 0;
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_PRESCRIPTION, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, entity.getVisitId());
            statement.setString(2, entity.getPrescriptionText());
            int isUpdated = statement.executeUpdate();
            if (isUpdated == 1) {
                try (ResultSet resultSet = statement.getGeneratedKeys();) {
                    if (resultSet.next()) {
                        prescriptionId = resultSet.getLong(1);
                        entity.setPrescriptionId(prescriptionId);
                    }
                }
                logger.log(Level.DEBUG, "The prescription with id " + prescriptionId + " was added to database ");
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to insert new prescription", e);
            throw new DaoException("Failed to insert new prescription", e);
        }
        return prescriptionId;
    }
    @Override
    public boolean updatePrescription(long id, String text) throws DaoException {
        boolean isUpdated;
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PRESCRIPTION)) {
            statement.setLong(1, id);
            statement.setString(2, text);
            isUpdated = (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to update prescription text", e);
            throw new DaoException("Failed to update prescription text", e);
        }
        return isUpdated;
    }
}
