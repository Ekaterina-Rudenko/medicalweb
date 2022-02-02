package by.epam.medicalweb.model.service.impl;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.dao.EntityTransaction;
import by.epam.medicalweb.model.dao.impl.UserDaoImpl;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.service.UserService;
import by.epam.medicalweb.util.PasswordEncoder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static Logger logger = LogManager.getLogger();
    private static UserServiceImpl instance;

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

}
