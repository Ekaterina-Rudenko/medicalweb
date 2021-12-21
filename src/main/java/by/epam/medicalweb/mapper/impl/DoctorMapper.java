package by.epam.medicalweb.mapper.impl;

import by.epam.medicalweb.entity.Doctor;
import by.epam.medicalweb.mapper.BaseMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static by.epam.medicalweb.mapper.impl.UserMapper.setUserData;

public class DoctorMapper implements BaseMapper<Doctor> {
    public static final String CATEGORY = "category";
    public static final String EXPERIENCE = "experience";
    public static final String DOCTOR_PHOTO = "doctor_photo";
    public static final String SPECIALIZATION_ID = "specialization_id";

    @Override
    public Optional<Doctor> mapEntity(ResultSet resultSet) {
        Doctor doctor = new Doctor();
        Optional<Doctor> optionalDoctor;
        try {
            setUserData(resultSet, doctor);
            doctor.setCategory(Doctor.Category.valueOf(resultSet.getString(CATEGORY).trim().toUpperCase()));
            doctor.setExperience(resultSet.getDate(EXPERIENCE).toLocalDate());
            doctor.setPhotoPath(resultSet.getString(DOCTOR_PHOTO));
            doctor.setSpecializationId(resultSet.getLong(SPECIALIZATION_ID));
            optionalDoctor = Optional.of(doctor);
        } catch (SQLException e) {
            optionalDoctor = Optional.empty();
        }
        return optionalDoctor;
    }
}
