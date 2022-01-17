package by.epam.medicalweb.model.dao.impl;

import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.PrescriptionDao;
import by.epam.medicalweb.model.entity.Prescription;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
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
        return null;
    }

    @Override
    public Optional<Prescription> findEntityById(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Prescription entity) throws DaoException {
        return false;
    }

    @Override
    public long create(Prescription entity) throws DaoException, SQLException {
        return 0;
    }
}
