package by.epam.medicalweb.model.mapper.impl;

import by.epam.medicalweb.model.entity.Doctor;
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
            doctor.setCategory(Doctor.Category.valueOf(resultSet.getString(CATEGORY).trim().toUpperCase()));
            doctor.setPhotoPath(resultSet.getString(DOCTOR_PHOTO));
            doctor.setSpecializationId(resultSet.getLong(SPECIALIZATION_ID));
            optionalDoctor = Optional.of(doctor);
        } catch (SQLException e) {
            optionalDoctor = Optional.empty();
        }
        return optionalDoctor;
    }
}
