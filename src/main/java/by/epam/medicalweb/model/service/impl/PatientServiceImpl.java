package by.epam.medicalweb.model.service.impl;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.mail.MailCreator;
import by.epam.medicalweb.model.dao.EntityTransaction;
import by.epam.medicalweb.model.dao.impl.PatientDaoImpl;
import by.epam.medicalweb.model.dao.impl.UserDaoImpl;
import by.epam.medicalweb.model.entity.Patient;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.service.PatientService;
import by.epam.medicalweb.model.service.UserService;
import by.epam.medicalweb.util.PasswordEncoder;
import by.epam.medicalweb.util.Validator;
import by.epam.medicalweb.util.impl.ValidatorImpl;
import java.math.BigDecimal;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import static by.epam.medicalweb.controller.command.RequestParameterName.*;

public class PatientServiceImpl implements PatientService {
    private static Logger logger = LogManager.getLogger();
    private static final String EMAIL_SUBJECT = "Medical centre HEALTHY registration";
    private static final String EMAIL_BODY = "You have been registered successfully!";
    private Validator validator = ValidatorImpl.getInstance();
    private static PatientServiceImpl instance;

    private PatientServiceImpl() {
    }

    public static PatientService getInstance() {
        if (instance == null) {
            instance = new PatientServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean registerPatient(Map<String, String> data) throws ServiceException, ConnectionPoolException {
        UserDaoImpl userDao = new UserDaoImpl();
        PatientDaoImpl patientDao = new PatientDaoImpl();
        UserService userService = UserServiceImpl.getInstance();
        EntityTransaction transaction = new EntityTransaction();
        transaction.beginTransaction(userDao, patientDao);
        try {
            String birthday = data.get(BIRTHDATE);
            String gender = data.get(GENDER);
            boolean isCorrectData = userService.checkData(data);
            if(!isCorrectData){
                return false;
            }
            LocalDate birthdate = LocalDate.parse(birthday);
            Patient.Gender patientGender = Patient.Gender.valueOf(gender.toUpperCase());
           /* User newUser = new User(firstName, middleName, lastName, login,
                    encryptPassword, email, phone, User.UserState.ACTIVE, User.UserRole.PATIENT);*/
            User newUser = userService.buildBasicUser(data);
            newUser.setRole(User.UserRole.PATIENT);
            long userId = userDao.create(newUser);
            Patient patientInfo = new Patient.PatientBuilder()
                    .setUserId(userId)
                    .setGender(patientGender)
                    .setBirthDate(birthdate)
                    .build();
            long patientId = patientDao.create(patientInfo);
           /* if (patientId != 0) {
                MailCreator.createMail(email, EMAIL_SUBJECT, EMAIL_BODY);
            }*/
            return (patientId != 0);
        } catch (DaoException e) {
            transaction.rollback();
            logger.log(Level.DEBUG, "New Patient failed to be registered. Error in service method", e);
            throw new ServiceException("New Patient failed to be registered. Error in service method", e);
        } finally {
            transaction.endTransaction();
        }
    }

    public Patient findPatientInfoById(long patientId) throws ServiceException, ConnectionPoolException {
        PatientDaoImpl patientDao = new PatientDaoImpl();
        EntityTransaction entityTransaction = new EntityTransaction();
        entityTransaction.beginQuery(patientDao);
        Patient patient;
        try{
            Optional<Patient>  optionalPatient = patientDao.findPatientInfoById(patientId);
            if(optionalPatient.isPresent()){
                patient = optionalPatient.get();
            } else {
                throw new ServiceException("Patient info with id " + patientId +" is absent" );
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR,"Patient with id: " + patientId + " info was not found", e );
            throw new ServiceException("Patient with id: " + patientId + " info was not found", e);
        } finally {
            entityTransaction.endQuery();
        }
        return patient;
    }

    public boolean topUpBalance(long patientId, BigDecimal money) throws ServiceException, ConnectionPoolException {
        PatientDaoImpl patientDao = new PatientDaoImpl();
        EntityTransaction entityTransaction = new EntityTransaction();
        entityTransaction.beginTransaction(patientDao);
        boolean isUpdated;
        try{
            isUpdated = patientDao.updatePatientBalance(patientId, money);
        } catch (DaoException e) {
            logger.log(Level.ERROR,"Patient balance wasn't updated", e );
            throw new ServiceException("Patient balance wasn't updated", e);
        } finally {
            entityTransaction.endTransaction();
        }
        return isUpdated;
    }

}
