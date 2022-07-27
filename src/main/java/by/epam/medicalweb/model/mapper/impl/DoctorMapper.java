package by.epam.medicalweb.model.mapper.impl;

import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.model.entity.Doctor;
import by.epam.medicalweb.model.entity.Doctor.DoctorBuilder;
import by.epam.medicalweb.model.entity.Specialization;
import by.epam.medicalweb.model.entity.User;
import by.epam.medicalweb.model.mapper.BaseMapper;

import by.epam.medicalweb.util.ImageEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.medicalweb.model.dao.ColumnName.*;

public class DoctorMapper implements BaseMapper<Doctor> {

  private static Logger logger = LogManager.getLogger();

  @Override
  public Optional<Doctor> mapEntity(ResultSet resultSet) throws DaoException {
    Doctor doctor = new Doctor();
    Optional<Doctor> optionalDoctor;
    try {
      doctor = new DoctorBuilder()
          .setUserId(resultSet.getLong(USER_ID))
          .setFirstName(resultSet.getString(FIRST_NAME))
          .setMiddleName(resultSet.getString(MIDDLE_NAME))
          .setLastName(resultSet.getString(LAST_NAME))
          .setEmail(resultSet.getString(EMAIL))
          .setPhone(resultSet.getString(PHONE))
          .setLogin(resultSet.getString(LOGIN))
          .setPassword(resultSet.getString(PASSWORD))
          .setState(User.UserState.valueOf(resultSet.getString(USER_STATE).trim().toUpperCase()))
          .setRole(User.UserRole.valueOf(resultSet.getString(USER_ROLE).trim().toUpperCase()))
          .setRegistrationDate(resultSet.getTimestamp(REGISTRATION_DATE).toLocalDateTime())
          .setCategory(Doctor.Category.valueOf(resultSet.getString(CATEGORY).trim().toUpperCase()))
          .setPhotoPath(ImageEncoder.encodeImage(resultSet.getString(DOCTOR_PHOTO)))
          .setSpecialization(new Specialization.Builder()
              .setSpecializationId(Long.parseLong(resultSet.getString(SPECIALIZATION_ID)))
              .setSpecializationName(resultSet.getString(SPECIALIZATION_NAME))
              .build())
          .build();

      optionalDoctor = Optional.of(doctor);
      System.out.println("doctor " + doctor);
    } catch (SQLException e) {
      logger.error("Sql exception in doctor mapper");
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
      logger.error("Sql exception in doctor mapper");
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
      System.out.println(optionalDoctor);
    } catch (SQLException e) {
      logger.error("Sql exception in doctor mapper");
      optionalDoctor = Optional.empty();
    }
    return optionalDoctor;
  }

  private void setDoctorData(ResultSet resultSet, Doctor doctor) throws SQLException {
    doctor.setCategory(Doctor.Category.valueOf(resultSet.getString(CATEGORY).trim().toUpperCase()));
    doctor.setPhotoPath(resultSet.getString(DOCTOR_PHOTO));
    doctor.setSpecialization(new Specialization.Builder()
        .setSpecializationId(Long.parseLong(resultSet.getString(SPECIALIZATION_ID)))
        .setSpecializationName(resultSet.getString(SPECIALIZATION_NAME))
        .build());
  }
}
