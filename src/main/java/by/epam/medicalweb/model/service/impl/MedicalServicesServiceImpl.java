package by.epam.medicalweb.model.service.impl;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.dao.EntityTransaction;
import by.epam.medicalweb.model.dao.impl.MedicalServiceDaoImpl;
import by.epam.medicalweb.model.entity.MedicalService;
import by.epam.medicalweb.model.service.MedicalServicesService;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MedicalServicesServiceImpl implements MedicalServicesService {

  private static Logger logger = LogManager.getLogger();
  private static MedicalServicesServiceImpl instance;

  private MedicalServicesServiceImpl() {
  }

  public static MedicalServicesServiceImpl getInstance() {
    if (instance == null) {
      instance = new MedicalServicesServiceImpl();
    }
    return instance;
  }

  @Override
  public List<MedicalService> findAll() throws ServiceException, ConnectionPoolException {
    EntityTransaction entityTransaction = new EntityTransaction();
    MedicalServiceDaoImpl medicalServiceDao = new MedicalServiceDaoImpl();
    entityTransaction.beginQuery(medicalServiceDao);
    List<by.epam.medicalweb.model.entity.MedicalService> services;
    try {
      services = medicalServiceDao.findAll();
    } catch (DaoException e) {
      logger.log(Level.ERROR, "Failed to find all the services ", e);
      throw new ServiceException("Failed to find all the services ", e);
    } finally {
      entityTransaction.endQuery();
    }
    return services;
  }

  @Override
  public List<MedicalService> findBySpecialization(long specId)
      throws ServiceException, ConnectionPoolException {
    EntityTransaction entityTransaction = new EntityTransaction();
    MedicalServiceDaoImpl medicalServiceDao = new MedicalServiceDaoImpl();
    entityTransaction.beginQuery(medicalServiceDao);
    List<by.epam.medicalweb.model.entity.MedicalService> services;
    try {
      services = medicalServiceDao.findServicesBySpecializationId(specId);
    } catch (DaoException e) {
      logger.log(Level.ERROR, "Failed to find all the services by specialization id", e);
      throw new ServiceException("Failed to find all the services by specialization id", e);
    } finally {
      entityTransaction.endQuery();
    }
    return services;
  }
}
