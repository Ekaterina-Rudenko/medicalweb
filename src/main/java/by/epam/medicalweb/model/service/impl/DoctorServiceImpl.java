package by.epam.medicalweb.model.service.impl;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.dao.EntityTransaction;
import by.epam.medicalweb.model.dao.impl.DoctorDaoImpl;
import by.epam.medicalweb.model.entity.Doctor;
import by.epam.medicalweb.model.service.DoctorService;
import by.epam.medicalweb.util.Validator;
import by.epam.medicalweb.util.impl.ValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class DoctorServiceImpl implements DoctorService {
    private static Logger logger = LogManager.getLogger();
    private Validator validator = ValidatorImpl.getInstance();
    private static DoctorServiceImpl instance;

    private DoctorServiceImpl() {
    }

    public static DoctorService getInstance() {
        if (instance == null) {
            instance = new DoctorServiceImpl();
        }
        return instance;
    }
    @Override
    public boolean updateDoctorPhoto(String photoPath, long doctorId) throws ServiceException, ConnectionPoolException {
        DoctorDaoImpl doctorDao = new DoctorDaoImpl();
        EntityTransaction entityTransaction = new EntityTransaction();
        entityTransaction.beginTransaction(doctorDao);
        try {
            return doctorDao.updateDoctorPhotoById(photoPath, doctorId);
        } catch (DaoException e) {
            throw new ServiceException("Doctor photo wasn't updated, exception in service method", e);
        } finally {
            entityTransaction.endTransaction();
        }
    }
    @Override
    public Doctor findDoctorInfoById(long doctorId) throws ServiceException, ConnectionPoolException {
        DoctorDaoImpl doctorDao = new DoctorDaoImpl();
        EntityTransaction entityTransaction = new EntityTransaction();
        entityTransaction.beginTransaction(doctorDao);
        Doctor doctor;
        try{
            Optional<Doctor> optionalDoctor = doctorDao.findDoctorInfoById(doctorId);
            if(optionalDoctor.isPresent()){
                doctor = optionalDoctor.get();
            } else {
                throw new ServiceException("Doctor info with id " + doctorId +" is absent" );
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR,"Doctor with id: " + doctorId + " info was not found", e );
            throw new ServiceException("Doctor with id: " + doctorId + " info was not found", e);
        } finally {
            entityTransaction.endTransaction();
        }
        return doctor;
    }
}
