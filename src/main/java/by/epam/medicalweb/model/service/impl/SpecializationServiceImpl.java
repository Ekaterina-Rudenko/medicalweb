package by.epam.medicalweb.model.service.impl;

import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.impl.SpecializationDaoImpl;
import by.epam.medicalweb.model.entity.Specialization;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SpecializationServiceImpl {
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

    public List<Specialization> findAll() throws ServiceException {
        List<Specialization> specializations = new ArrayList<>();
        AbstractDao<Specialization> specDao = new SpecializationDaoImpl();
        try {
            specializations = specDao.findAll();
            return specializations;
        } catch (DaoException e) {
           logger.log(Level.ERROR, "Failed to find all the specializations ", e);
            throw new ServiceException("Failed to find all the specializations " , e);
        }
    }

}
