package by.epam.medicalweb.model.service.impl;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.dao.EntityTransaction;
import by.epam.medicalweb.model.dao.UserDao;
import by.epam.medicalweb.model.dao.impl.UserDaoImpl;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.service.UserService;
import by.epam.medicalweb.util.PasswordEncoder;
import by.epam.medicalweb.util.Validator;
import by.epam.medicalweb.util.impl.ValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Optional;

import static by.epam.medicalweb.controller.command.RequestParameterName.*;
import static by.epam.medicalweb.controller.command.RequestParameterName.REPEATED_PASSWORD;

public class UserServiceImpl implements UserService {
    private static Logger logger = LogManager.getLogger();
    private static UserServiceImpl instance;
    private Validator validator = ValidatorImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public Optional<User> logIn(String login, String password) throws ServiceException, ConnectionPoolException {
        UserDaoImpl userDao = new UserDaoImpl();
        Optional<User> optionalUser;
        EntityTransaction entityTransaction = new EntityTransaction();
        entityTransaction.beginTransaction(userDao);
        String encodedPassword = PasswordEncoder.encryptPass(password);
        try {
            optionalUser = userDao.findUserByLoginAndPassword(login, encodedPassword);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Failed to log in, exception in service log in method ", e);
            throw new ServiceException("Failed to log in user ", e);
        } finally {
            entityTransaction.endTransaction();
        }
        return optionalUser;
    }

    /* method is used in other methods (Patient/Doctor registration),
    as it is a repeated part of the code
     */
    @Override
    public User buildBasicUser(Map<String, String> data) throws ConnectionPoolException {
        User basicUser;
        String firstName = data.get(FIRST_NAME);
        String middleName = data.get(MIDDLE_NAME);
        String lastName = data.get(LAST_NAME);
        String login = data.get(LOGIN);
        String password = data.get(PASSWORD);
        String email = data.get(EMAIL);
        String phone = data.get(PHONE);
        String encryptPassword = PasswordEncoder.encryptPass(password);
        basicUser = new User.UserBuilder()
                .setFirstName(firstName)
                .setMiddleName(middleName)
                .setLastName(lastName)
                .setLogin(login)
                .setPassword(encryptPassword)
                .setEmail(email)
                .setPhone(phone)
                .setState(User.UserState.ACTIVE)
                .build();
        return basicUser;
    }

    @Override
    public boolean checkData(Map<String, String> data) throws DaoException, ConnectionPoolException {
        UserDaoImpl userDao = new UserDaoImpl();
        EntityTransaction entityTransaction = new EntityTransaction();
        entityTransaction.beginQuery(userDao);
        String login = data.get(LOGIN);
        String email = data.get(EMAIL);
        String password = data.get(PASSWORD);
        String repeatedPassword = data.get(REPEATED_PASSWORD);

        boolean validationResult = true;

        validationResult = validator.checkRegistration(data);
        logger.log(Level.DEBUG, "Registration data is valid: " + validationResult);
        if (!validationResult) {
            return false;
        }

        if (userDao.findUserByLogin(login).isPresent()) {
            data.put(LOGIN, NOT_UNIQUE_LOGIN);
            validationResult = false;
        }
        if (userDao.findUserByEmail(email).isPresent()) {
            data.put(EMAIL, NOT_UNIQUE_EMAIL);
            validationResult = false;
        }

        if (!password.equals(repeatedPassword)) {
            data.put(REPEATED_PASSWORD, INVALID_REPEATED_PASSWORD);
            validationResult = false;
        }
        entityTransaction.endQuery();
        return validationResult;
    }

}
