package by.epam.medicalweb.mapper.impl;

import by.epam.medicalweb.entity.Admin;
import by.epam.medicalweb.mapper.BaseMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static by.epam.medicalweb.mapper.impl.UserMapper.*;

public class AdminMapper implements BaseMapper<Admin> {
    public static final String ADMIN_PHOTO = "admin_photo";

    @Override
    public Optional<Admin> mapEntity(ResultSet resultSet) {
        Admin admin = new Admin();
        Optional<Admin> optionalAdmin;
        try {
            setUserData(resultSet, admin);
            admin.setPhotoPath(resultSet.getString(ADMIN_PHOTO));
            optionalAdmin = Optional.of(admin);
        } catch (SQLException e) {
            optionalAdmin = Optional.empty();
        }
        return optionalAdmin;
    }

    public Optional<Admin> mapEntityPartly(ResultSet resultSet) {
        Admin admin = new Admin();
        Optional<Admin> optionalAdmin;
        try {
            admin.setPhotoPath(resultSet.getString(ADMIN_PHOTO));
            optionalAdmin = Optional.of(admin);
        } catch (SQLException e) {
            optionalAdmin = Optional.empty();
        }
        return optionalAdmin;
    }
}
