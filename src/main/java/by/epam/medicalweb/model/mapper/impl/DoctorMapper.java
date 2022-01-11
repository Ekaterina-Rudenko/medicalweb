package by.epam.medicalweb.model.mapper.impl;

import by.epam.medicalweb.model.entity.Doctor;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.mapper.BaseMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import static by.epam.medicalweb.model.dao.ColumnName.*;

public class DoctorMapper implements BaseMapper<Doctor> {

    @Override
    public Optional<Doctor> mapEntity(ResultSet resultSet) {
        Doctor doctor = new Doctor();
        Optional<Doctor> optionalDoctor;
        try {
            UserMapper.setUserData(resultSet, doctor);
            setDoctorData(resultSet, doctor);
            optionalDoctor = Optional.of(doctor);
        } catch (SQLException e) {
            optionalDoctor = Optional.empty();
        }
        return optionalDoctor;
    }
    public Optional<Doctor> mapEntityPartially(ResultSet resultSet) {
        Doctor doctor = new Doctor();
        Optional<Doctor> optionalDoctor;
        try {
            doctor.setUserId(resultSet.getLong(DOCTOR_ID));
            setDoctorData(resultSet, doctor);
            optionalDoctor = Optional.of(doctor);
        } catch (SQLException e) {
            optionalDoctor = Optional.empty();
        }
        return optionalDoctor;
    }

    public Optional<Doctor> mapEntityFromUser(User user, ResultSet resultSet) {
        Doctor doctor = new Doctor();
        Optional<Doctor> optionalDoctor;
        try {
            doctor.setFirstName(user.getFirstName());
            doctor.setMiddleName(user.getMiddleName());
            doctor.setLastName(user.getLastName());
            doctor.setLogin(user.getLogin());
            doctor.setPassword(user.getPassword());
            doctor.setEmail(user.getEmail());
            doctor.setPhoneNumber(user.getPhoneNumber());
            doctor.setState(user.getState());
            doctor.setRole(user.getRole());
            doctor.setRegistrationDate(user.getRegistrationDate());
            setDoctorData(resultSet, doctor);
            optionalDoctor = Optional.of(doctor);
        } catch (SQLException e) {
            optionalDoctor = Optional.empty();
        }
        return optionalDoctor;
    }
    private void setDoctorData(ResultSet resultSet, Doctor doctor) throws SQLException {
        doctor.setCategory(Doctor.Category.valueOf(resultSet.getString(CATEGORY).trim().toUpperCase()));
        doctor.setPhotoPath(resultSet.getString(DOCTOR_PHOTO));
        doctor.setSpecializationId(resultSet.getLong(SPECIALIZATION_ID));
    }
}
