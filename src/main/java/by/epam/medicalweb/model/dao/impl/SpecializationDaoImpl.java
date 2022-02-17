package by.epam.medicalweb.model.dao.impl;

import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.SpecializationDao;
import by.epam.medicalweb.model.entity.Specialization;
import by.epam.medicalweb.model.mapper.impl.SpecializationMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpecializationDaoImpl extends AbstractDao<Specialization> implements SpecializationDao {
    private static Logger logger = LogManager.getLogger();

    private static final String SQL_FIND_SPECIALIZATION_BY_ID = """
            SELECT spec_id, spec_name
            FROM specializations
            WHERE spec_id = (?);""";
    private static final String SQL_FIND_ALL_SPECIALIZATIONS = """
            SELECT spec_id, spec_name
            FROM specializations
            ORDER BY spec_name;""";
    private static final String SQL_DELETE_SPECIALIZATIONS_BY_ID = """
            DELETE
            FROM specializations
            WHERE spec_id = (?);""";
    private static final String SQL_UPDATE_SPECIALIZATIONS_BY_ID = """
            UPDATE specializations
            SET spec_name = ?
            WHERE spec_id = (?);""";
    private static final String SQL_INSERT_NEW_SPECIALIZATION = """
            INSERT INTO specializations (spec_name)
            VALUE (?):""";

    public SpecializationDaoImpl(){}
    @Override
    public List<Specialization> findAll() throws DaoException {
        List<Specialization> listSpecialization = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_SPECIALIZATIONS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Optional<Specialization> optionalSpecialization = new SpecializationMapper().mapEntity(resultSet);
                if (optionalSpecialization.isPresent()) {
                    listSpecialization.add(optionalSpecialization.get());
                }
            }
        } catch (
                SQLException e) {
            logger.log(Level.ERROR, "Failed to select all the specialization", e);
            throw new DaoException("Failed to select all the specialization", e);
        }
        return listSpecialization;
    }

    @Override
    public Optional<Specialization> findEntityById(long id) throws DaoException {
        Optional<Specialization> specialization = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_SPECIALIZATION_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    specialization = new SpecializationMapper().mapEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to select specialization by id", e);
            throw new DaoException("Failed to select specialization by id", e);
        }
        return specialization;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SPECIALIZATIONS_BY_ID)){
            statement.setLong(1, id);
            int update = statement.executeUpdate();
            return (update > 0) ? true : false;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to delete the specialization by id", e);
            throw new DaoException("Failed to delete the specialization by id", e);
        }
    }

    @Override
    public boolean delete(Specialization entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SPECIALIZATIONS_BY_ID)) {
            statement.setLong(1, entity.getSpecializationId());
            int update = statement.executeUpdate();
            return (update > 0) ? true : false;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to delete the specialization", e);
            throw new DaoException("Failed to delete the specialization", e);
        }
    }

    @Override
    public long create(Specialization entity) throws DaoException, SQLException {
        long specId = 0;
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW_SPECIALIZATION, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            int isUpdated = statement.executeUpdate();
            if (isUpdated == 1) {
                try (ResultSet resultSet = statement.getGeneratedKeys();) {
                    if (resultSet.next()) {
                        specId = resultSet.getLong(1);
                        entity.setSpecializationId(specId);
                    }
                }
                logger.log(Level.DEBUG, "The specialization with id " + specId + " was added to database ");
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to insert the specialization", e);
            throw new DaoException("Failed to insert the specialization", e);
        }
        return specId;
    }
    @Override
    public boolean updateSpecialization(long id, String specName) throws DaoException {
        boolean isUpdated;
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SPECIALIZATIONS_BY_ID)) {
            statement.setLong(1, id);
            statement.setString(2, specName);
            isUpdated = (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to update specialization name", e);
            throw new DaoException("Failed to update specialization name", e);
        }
        return isUpdated;
    }
}
