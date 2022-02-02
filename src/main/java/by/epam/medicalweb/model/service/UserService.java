package by.epam.medicalweb.model.service;

import by.epam.medicalweb.exception.ConnectionPoolException;
import by.epam.medicalweb.exception.ServiceException;
import by.epam.medicalweb.model.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> logIn(String login, String password) throws ServiceException, ConnectionPoolException;
}
