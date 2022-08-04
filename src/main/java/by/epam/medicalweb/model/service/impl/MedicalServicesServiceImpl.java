package by.epam.medicalweb.model.service.impl;

import static by.epam.medicalweb.controller.command.RequestParameterName.SPECIALIZATION_ID;
import static by.epam.medicalweb.model.dao.ColumnName.SERVICE_NAME;
import static by.epam.medicalweb.model.dao.ColumnName.SERVICE_PRICE;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.EntityTransaction;
import by.epam.medicalweb.model.dao.MedicalServiceDao;
import by.epam.medicalweb.model.dao.impl.MedicalServiceDaoImpl;
import by.epam.medicalweb.model.entity.MedicalService;
import by.epam.medicalweb.model.entity.Specialization;
import by.epam.medicalweb.model.service.MedicalServicesService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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

  public long createService(Map<String, String> data)
      throws ServiceException, ConnectionPoolException {
    EntityTransaction entityTransaction = new EntityTransaction();
    AbstractDao<MedicalService> medicalServiceDao = new MedicalServiceDaoImpl();
    entityTransaction.beginTransaction(medicalServiceDao);
    long serviceId = 0;
    try {
      String name = data.get(SERVICE_NAME);
      String priceString = data.get(SERVICE_PRICE);
      String spec = data.get(SPECIALIZATION_ID);
      BigDecimal price = BigDecimal.valueOf(Double.parseDouble(priceString));
      long specId = Long.parseLong(spec);

      MedicalService medicalService = new MedicalService.ServiceBuilder()
          .setServiceName(name)
          .setServicePrice(price)
          .setSpecialization(new Specialization.Builder().setSpecializationId(specId).build())
          .build();
      serviceId = medicalServiceDao.create(medicalService);

    } catch (SQLException | DaoException e) {
      logger.log(Level.DEBUG, "Service failed to be added", e);
      throw  new ServiceException("Service failed to be added", e);
    } finally {
      entityTransaction.endTransaction();
    }
    return serviceId;
  }

  @Override
  public List<MedicalService> findAllServices() throws ServiceException, ConnectionPoolException {
    EntityTransaction entityTransaction = new EntityTransaction();
    MedicalServiceDaoImpl medicalServiceDao = new MedicalServiceDaoImpl();
    entityTransaction.beginQuery(medicalServiceDao);
    List<MedicalService> services;
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
  public boolean editMedicalService(long serviceId, BigDecimal price) throws ServiceException, ConnectionPoolException {
    EntityTransaction entityTransaction = new EntityTransaction();
    MedicalServiceDaoImpl medicalServiceDao = new MedicalServiceDaoImpl();
    entityTransaction.beginQuery(medicalServiceDao);
    boolean isUpdated;
    try {
      isUpdated = medicalServiceDao.updateServicePrice(serviceId, price);
    } catch (DaoException e) {
      logger.log(Level.ERROR, "Failed to update the service ", e);
      throw new ServiceException("Failed to update the service", e);
    } finally {
      entityTransaction.endQuery();
    }
    return isUpdated;
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
