package by.epam.medicalweb.model.dao;

import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findUserByLastName(String lastName) throws DaoException;
    Optional<User> findUserByLogin(String login) throws DaoException;
    Optional<User> findUserByEmail(String email) throws DaoException;
    Optional<User> findUserByPhoneNumber(String phoneNumber) throws DaoException;
    List<User> findUsersByState(String userState) throws DaoException;
    List<User> findUsersByRole(String role) throws DaoException;
    boolean updateUserStateById (Long id,String state) throws DaoException;
    boolean updateUserFirstNameByID (long id, String firstName) throws DaoException;
    boolean updateUserMiddleNameByID (long id, String middleName) throws DaoException;
    boolean updateUserLastNameByID (long id, String lastName) throws DaoException;
    boolean updateUserEmailByID (long id, String email) throws DaoException;
    boolean updateUserPhoneNumberByID (long id, String phoneNumber) throws DaoException;
    Optional<User> findUserByLoginAndPassword (String login, String password) throws DaoException;
    boolean findUserByIdAndPassword(long id, String pass) throws DaoException;
    boolean updateUserPasswordById(Long id, String password) throws DaoException;
}
