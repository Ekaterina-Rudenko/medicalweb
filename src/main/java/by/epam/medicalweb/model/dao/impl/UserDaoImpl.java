package by.epam.medicalweb.model.dao.impl;

import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.UserDao;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.mapper.impl.UserMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    static Logger logger = LogManager.getLogger();
    private static final String SQL_SELECT_USER_BY_ID = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone, user_state, user_role, registration_date 
            FROM users
            WHERE user_id = ?; """;
    private static final String SQL_SELECT_ALL_USERS = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone, user_state, user_role, registration_date
            FROM users;""";
    private static final String SQL_DELETE_USER_BY_ID = """
            DELETE FROM users WHERE user_id = ?;""";
    private static final String SQL_CREATE_USER = """
            INSERT INTO users(first_name, middle_name, last_name, login, password, email, phone, user_state, user_role)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);""";
    private static final String SQL_SELECT_USER_BY_NAME = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone, user_state, user_role, registration_date
            FROM users
            WHERE last_name = ?;""";
    private static final String SQL_SELECT_USER_BY_LOGIN = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone, user_state, user_role, registration_date
            FROM users
            WHERE login = ?;""";
    private static final String SQL_SELECT_USER_BY_EMAIL = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone, user_state, user_role, registration_date
            FROM users
            WHERE email = ?;""";
    private static final String SQL_SELECT_USER_BY_PHONE = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone, user_state, user_role, registration_date
            FROM users
            WHERE email = ?;""";
    private static final String SQL_SELECT_USERS_BY_STATE = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone, user_state, user_role, registration_date
            FROM users
            WHERE user_state = ?;""";
    private static final String SQL_SELECT_USERS_BY_ROLE = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone, user_state, user_role, registration_date
            FROM users
            WHERE user_role = ?;""";
    private static final String SQL_UPDATE_USER_BY_ID = """
            UPDATE users
            SET first_name = ?, middle_name = ?, last_name = ?, login = ?, password = ?, email = ?, phone = ?, user_state = ?, user_role = ?
            WHERE user_id = ?;""";
    private static final String SQL_UPDATE_USER_STATE_BY_ID = """
            UPDATE users SET user_state = ? WHERE user_id = ?;""";
    private static final String SQL_UPDATE_USER_FIRST_NAME_BY_ID =
            "UPDATE users SET first_name = ? WHERE user_id = ?";
    private static final String SQL_UPDATE_USER_MIDDLE_NAME_BY_ID =
            "UPDATE users SET middle_name = ? WHERE user_id = ?";
    private static final String SQL_UPDATE_USER_LAST_NAME_BY_ID =
            "UPDATE users SET last_name = ? WHERE user_id = ?";
    private static final String SQL_UPDATE_USER_EMAIL_BY_ID =
            "UPDATE users SET email = ? WHERE user_id = ?";
    private static final String SQL_UPDATE_USER_PHONE_BY_ID =
            "UPDATE users SET phone = ? WHERE user_id = ?";
    private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = """
            SELECT user_id, first_name, middle_name, last_name, login, password, email, phone, user_state, user_role, registration_date
            FROM users
            WHERE login = ? AND password = ?;""";

    public UserDaoImpl() {
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_USERS);
             ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<User> optionalUser = new UserMapper().mapEntity(resultSet);
                    if (optionalUser.isPresent()) {
                        userList.add(optionalUser.get());
                    }
                }
            } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to find all users, access database error", e);
            throw new DaoException("Failed to find all users, access database error", e);
        }
        return userList;
    }

    @Override
    public Optional<User> findEntityById(long id) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalUser = new UserMapper().mapEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to find user by id", e);
            throw new DaoException("Failed to find user by id", e);
        }
        return optionalUser;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        boolean isDeleted;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            statement.setLong(1, id);
            isDeleted = (statement.executeUpdate() == 1);
            logger.log(Level.DEBUG, "User with id " + id + " was deleted");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to delete user by id", e);
            throw new DaoException("Failed to delete user by id", e);
        }
        return isDeleted;
    }

    @Override
    public boolean delete(User entity) throws DaoException {
        boolean isDeleted;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            statement.setLong(1, entity.getUserId());
            isDeleted = (statement.executeUpdate() == 1);
            logger.log(Level.DEBUG, "User with id " + entity.getUserId() + " was deleted");
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to delete user by id", e);
            throw new DaoException("Failed to delete user by id", e);
        }
        return isDeleted;
    }

    @Override
    public long create(User entity) throws DaoException {
        long userId = 0;
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getMiddleName());
            statement.setString(3, entity.getLastName());
            statement.setString(4, entity.getLogin());
            statement.setString(5, entity.getPassword());
            statement.setString(6, entity.getEmail());
            statement.setString(7, entity.getPhoneNumber());
            statement.setString(8, entity.getState().getStateValue());
            statement.setString(9, entity.getRole().getRoleValue());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    userId = resultSet.getLong(1);
                    logger.log(Level.DEBUG, " User with id " + userId + " was added to database ");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to create user", e);
            throw new DaoException("Failed to create user ", e);
        }
        return userId;
    }

    @Override
    public List<User> findUserByLastName(String lastName) throws DaoException {
        List<User> usersList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_NAME)) {
            statement.setString(1, lastName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<User> optionalUser = new UserMapper().mapEntity(resultSet);
                    if(optionalUser.isPresent()){
                        usersList.add(optionalUser.get());
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to find user by name", e);
            throw new DaoException("Failed to find user by name", e);
        }
        return usersList;
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalUser = new UserMapper().mapEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to find user by login", e);
            throw new DaoException("Failed to find user by login", e);
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalUser = new UserMapper().mapEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to find user by email", e);
            throw new DaoException("Failed to find user by email", e);
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findUserByPhoneNumber(String phoneNumber) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_PHONE)) {
            statement.setString(1, phoneNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalUser = new UserMapper().mapEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to find user by phone number", e);
            throw new DaoException("Failed to find user by phone number", e);
        }
        return optionalUser;
    }

    @Override
    public List<User> findUsersByState(String userState) throws DaoException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USERS_BY_STATE)) {
            statement.setString(1, userState);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<User> optionalUser = new UserMapper().mapEntity(resultSet);
                    if (optionalUser.isPresent()) {
                        userList.add(optionalUser.get());
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to find users by state", e);
            throw new DaoException("Failed to find users by state", e);
        }
        return userList;
    }

    @Override
    public List<User> findUsersByRole(String role) throws DaoException {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USERS_BY_ROLE)) {
            statement.setString(1, role);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<User> optionalUser = new UserMapper().mapEntity(resultSet);
                    if (optionalUser.isPresent()) {
                        userList.add(optionalUser.get());
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to find users by role", e);
            throw new DaoException("Failed to find users by role", e);
        }
        return userList;
    }

    @Override
    public boolean updateUserStateById(Long id, String state) throws DaoException {
        boolean isUpdated;
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_STATE_BY_ID)) {
            statement.setString(1, state);
            statement.setLong(2, id);
            isUpdated = (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to update user " + id + " state", e);
            throw new DaoException("Failed to update user " + id + " state", e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateUserFirstNameByID(long id, String firstName) throws DaoException {
        boolean isUpdated;
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_FIRST_NAME_BY_ID)) {
            statement.setString(1, firstName);
            statement.setLong(2, id);
            isUpdated = (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to update user " + id + " first name", e);
            throw new DaoException("Failed to update user " + id + " first name", e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateUserMiddleNameByID(long id, String middleName) throws DaoException {
        boolean isUpdated;
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_MIDDLE_NAME_BY_ID)) {
            statement.setString(1, middleName);
            statement.setLong(2, id);
            isUpdated = (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to update user " + id + " middle name", e);
            throw new DaoException("Failed to update user " + id + " middle name", e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateUserLastNameByID(long id, String lastName) throws DaoException {
        boolean isUpdated;
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_LAST_NAME_BY_ID)) {
            statement.setString(1, lastName);
            statement.setLong(2, id);
            isUpdated = (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to update user " + id + " last name", e);
            throw new DaoException("Failed to update user " + id + " last name", e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateUserEmailByID(long id, String email) throws DaoException {
        boolean isUpdated;
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_EMAIL_BY_ID)) {
            statement.setString(1, email);
            statement.setLong(2, id);
            isUpdated = (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to update user " + id + " email", e);
            throw new DaoException("Failed to update user " + id + " email", e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateUserPhoneNumberByID(long id, String phoneNumber) throws DaoException {
        boolean isUpdated;
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_PHONE_BY_ID)) {
            statement.setString(1, phoneNumber);
            statement.setLong(2, id);
            isUpdated = (statement.executeUpdate() == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to update user " + id + " phoneNumber", e);
            throw new DaoException("Failed to update user " + id + " phoneNumber", e);
        }
        return isUpdated;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    optionalUser = new UserMapper().mapEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to find user by phone number due to database error", e);
            throw new DaoException("Failed to find user by phone number due to database error", e);
        }
        return optionalUser;
    }
}
