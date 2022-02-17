package by.epam.medicalweb.model.service;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> logIn(String login, String password) throws ServiceException, ConnectionPoolException;
    boolean checkData(Map<String, String> data) throws DaoException, ConnectionPoolException;
    User buildBasicUser(Map<String, String> data) throws ConnectionPoolException;
}
