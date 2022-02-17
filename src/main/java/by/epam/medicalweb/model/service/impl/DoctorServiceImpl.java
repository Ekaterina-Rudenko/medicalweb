package by.epam.medicalweb.model.service.impl;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.EntityTransaction;
import by.epam.medicalweb.model.dao.impl.DoctorDaoImpl;
import by.epam.medicalweb.model.dao.impl.UserDaoImpl;
import by.epam.medicalweb.model.entity.Doctor;
import by.epam.medicalweb.model.entity.Patient;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.service.DoctorService;
import by.epam.medicalweb.model.service.UserService;
import by.epam.medicalweb.util.PasswordEncoder;
import by.epam.medicalweb.util.Validator;
import by.epam.medicalweb.util.impl.ValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.print.Doc;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.epam.medicalweb.controller.command.RequestParameterName.*;
import static by.epam.medicalweb.controller.command.RequestParameterName.INVALID_REPEATED_PASSWORD;

public class DoctorServiceImpl implements DoctorService {
    private static Logger logger = LogManager.getLogger();
    private static final String DEFAULT_IMAGE_PATH = "images/default.jpg";
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
            entityTransaction.rollback();
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
        try {
            Optional<Doctor> optionalDoctor = doctorDao.findDoctorInfoById(doctorId);
            if (optionalDoctor.isPresent()) {
                doctor = optionalDoctor.get();
            } else {
                throw new ServiceException("Doctor info with id " + doctorId + " is absent");
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Doctor with id: " + doctorId + " info was not found", e);
            throw new ServiceException("Doctor with id: " + doctorId + " info was not found", e);
        } finally {
            entityTransaction.endTransaction();
        }
        return doctor;
    }

    @Override
    public boolean addDoctor(Map<String, String> data) throws ServiceException, ConnectionPoolException {
        UserDaoImpl userDao = new UserDaoImpl();
        DoctorDaoImpl doctorDao = new DoctorDaoImpl();
        UserService userService = UserServiceImpl.getInstance();
        EntityTransaction entityTransaction = new EntityTransaction();
        entityTransaction.beginTransaction(userDao, doctorDao);
        try {
            String specialization = data.get(SPECIALIZATION_ID);
            String imagePath = data.get(IMAGE_PATH);
            String category = data.get(CATEGORY);

            boolean isCorrectData = userService.checkData(data);
            if (!isCorrectData) {
                return false;
            }
            if(imagePath == null){
                imagePath = DEFAULT_IMAGE_PATH;
            }
            logger.log(Level.DEBUG, "Image path " + imagePath);


            /*isCorrectData = validator.isCorrectPhotoUrl(imagePath);
            if(!isCorrectData){
                return false;
            }*/
            /*String firstName = data.get(FIRST_NAME);
            String middleName = data.get(MIDDLE_NAME);
            String lastName = data.get(LAST_NAME);
            String login = data.get(LOGIN);
            String password = data.get(PASSWORD);
            String email = data.get(EMAIL);
            String phone = data.get(PHONE);
            String repeatedPassword = data.get(REPEATED_PASSWORD);*/



            /*boolean isUniqueResult = true;
            if (userDao.findUserByLogin(login).isPresent()) {
                data.put(LOGIN, NOT_UNIQUE_LOGIN);
                isUniqueResult = false;
            }
            if (userDao.findUserByEmail(email).isPresent()) {
                data.put(EMAIL, NOT_UNIQUE_EMAIL);
                isUniqueResult = false;
            }

            if (!password.equals(repeatedPassword)) {
                data.put(REPEATED_PASSWORD, INVALID_REPEATED_PASSWORD);
                isUniqueResult = false;
            }
            if (!isUniqueResult) {
                return false;
            }*/

           /* String encryptPassword = PasswordEncoder.encryptPass(password);
            User newUser = new User(firstName, middleName, lastName, login,
                    encryptPassword, email, phone, User.UserState.ACTIVE, User.UserRole.DOCTOR);
*/
            User newUser = userService.buildBasicUser(data);
            newUser.setRole(User.UserRole.DOCTOR);

            Doctor.Category doctorCategory = Doctor.Category.valueOf(category.toUpperCase());
            long specializationId = Long.parseLong(specialization);
            long userId = userDao.create(newUser);
            Doctor doctorInfo = new Doctor.DoctorBuilder()
                    .setUserId(userId)
                    .setCategory(doctorCategory)
                    .setPhotoPath(imagePath)
                    .setSpecialization(specializationId)
                    .build();
            long doctorId = doctorDao.create(doctorInfo);
            return (doctorId != 0);
        } catch (DaoException e) {
            entityTransaction.rollback();
            logger.log(Level.DEBUG, "New Doctor failed to be registered. Error in service method", e);
            throw new ServiceException("New Doctor failed to be registered. Error in service method", e);
        } finally {
            entityTransaction.endTransaction();
        }
    }
    public List<Doctor> findAllDoctors() throws ConnectionPoolException {
        EntityTransaction entityTransaction = new EntityTransaction();
        AbstractDao<Doctor> doctorDao = new DoctorDaoImpl();
        entityTransaction.beginTransaction(doctorDao);
        List<Doctor> doctorList = new ArrayList<>();
        try{
            doctorList = doctorDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            entityTransaction.endTransaction();
        }
        return doctorList;
    }
}
