package by.epam.medicalweb.model.service.impl;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.EntityTransaction;
import by.epam.medicalweb.model.dao.impl.SpecializationDaoImpl;
import by.epam.medicalweb.model.entity.Specialization;
import by.epam.medicalweb.model.service.SpecializationService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SpecializationServiceImpl implements SpecializationService {
    private static Logger logger = LogManager.getLogger();
    private static SpecializationServiceImpl instance;

    private SpecializationServiceImpl() {
    }

    public static SpecializationServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpecializationServiceImpl();
        }
        return instance;
    }

    public List<Specialization> findAll() throws ServiceException, ConnectionPoolException {
        EntityTransaction entityTransaction = new EntityTransaction();
        AbstractDao<Specialization> specDao = new SpecializationDaoImpl();
        entityTransaction.beginQuery(specDao);
        List<Specialization> specializations;
        try {
            specializations = specDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Failed to find all the specializations ", e);
            throw new ServiceException("Failed to find all the specializations ", e);
        } finally {
            entityTransaction.endQuery();
        }
        return specializations;
    }

}
