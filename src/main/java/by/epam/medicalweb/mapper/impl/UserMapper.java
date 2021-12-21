package by.epam.medicalweb.mapper.impl;

import by.epam.medicalweb.entity.User;
import by.epam.medicalweb.mapper.BaseMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserMapper implements BaseMapper<User> {
    public static final String USER_ID = "user_id";
    public static final String FIRST_NAME = "first_name";
    public static final String MIDDLE_NAME = "middle_name";
    public static final String LAST_NAME = "last_name";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String USER_STATE = "user_state";
    public static final String USER_ROLE = "user_role";
    public static final String REGISTRATION_DATE = "registration_date";

    @Override
    public Optional<User> mapEntity(ResultSet resultSet) {
        User user = new User();
        Optional<User> optionalUser;
        try {
            setUserData(resultSet, user);
            optionalUser = Optional.of(user);
        } catch (SQLException e) {
            optionalUser = Optional.empty();
        }
        return optionalUser;
    }


    protected static void setUserData(ResultSet resultSet, User user) throws SQLException {
        user.setUserId(resultSet.getLong(USER_ID));
        user.setFirstName(resultSet.getString(FIRST_NAME));
        user.setMiddleName(resultSet.getString(MIDDLE_NAME));
        user.setLastName(resultSet.getString(LAST_NAME));
        user.setLogin(resultSet.getString(LOGIN));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setEmail(resultSet.getString(EMAIL));
        user.setPhoneNumber(resultSet.getString(PHONE));
        user.setState(User.UserState.valueOf(resultSet.getString(USER_STATE).trim().toUpperCase()));
        user.setRole(User.UserRole.valueOf(resultSet.getString(USER_ROLE).trim().toUpperCase()));
        user.setRegistrationDate(resultSet.getTimestamp(REGISTRATION_DATE).toLocalDateTime());
    }
}
