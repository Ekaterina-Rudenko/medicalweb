package by.epam.medicalweb.model.service.impl;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.EntityTransaction;
import by.epam.medicalweb.model.dao.impl.SpecializationDaoImpl;
import by.epam.medicalweb.model.entity.Specialization;
import by.epam.medicalweb.model.service.SpecializationService;
import java.sql.SQLException;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

  public Specialization findSpecializationById(long id)
      throws ServiceException, ConnectionPoolException {
    EntityTransaction entityTransaction = new EntityTransaction();
    AbstractDao<Specialization> specDao = new SpecializationDaoImpl();
    entityTransaction.beginQuery(specDao);
    Specialization specialization;
    try {
      Optional<Specialization> optionalSpecialization = specDao.findEntityById(id);
      if (optionalSpecialization.isPresent()) {
        specialization = optionalSpecialization.get();
      } else {
        throw new ServiceException("Specialization with id " + id + " is absent");
      }
    } catch (DaoException e) {
      logger.log(Level.ERROR, "Failed to find all the specializations ", e);
      throw new ServiceException("Failed to find all the specializations ", e);
    } finally {
      entityTransaction.endQuery();
    }
    return specialization;
  }

  @Override
  public long createSpecialization(String name) throws ConnectionPoolException, ServiceException {
    EntityTransaction entityTransaction = new EntityTransaction();
    AbstractDao<Specialization> specializationDao = new SpecializationDaoImpl();
    entityTransaction.beginQuery(specializationDao);
    long specId = 0;
    try {
      specId = specializationDao.create(new Specialization.Builder().setSpecializationName(name).build());
    } catch (SQLException | DaoException e) {
      logger.log(Level.DEBUG, "New specialization failed to be added", e);
      throw new ServiceException("New specialization failed to be added", e);
    } finally {
      entityTransaction.endQuery();
    }
    return specId;
  }

}
