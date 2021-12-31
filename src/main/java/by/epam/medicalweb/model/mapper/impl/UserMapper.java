package by.epam.medicalweb.model.mapper.impl;

import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.mapper.BaseMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static by.epam.medicalweb.model.dao.ColumnName.*;

public class UserMapper implements BaseMapper<User> {

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
