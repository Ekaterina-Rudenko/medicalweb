package by.epam.medicalweb.mapper.impl;

import by.epam.medicalweb.entity.Patient;
import by.epam.medicalweb.mapper.BaseMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static by.epam.medicalweb.mapper.impl.UserMapper.setUserData;

public class PatientMapper implements BaseMapper<Patient> {
    public static final String GENDER = "gender";
    public static final String BIRTHDATE = "birthdate";
    public static final String BALANCE = "balance";

    @Override
    public Optional<Patient> mapEntity(ResultSet resultSet) {
        Patient patient = new Patient();
        Optional<Patient> optionalPatient;
        try {
            setUserData(resultSet, patient);
            patient.setGender(Patient.Gender.valueOf(resultSet.getString(GENDER).trim().toUpperCase()));
            patient.setBirthDate(resultSet.getDate(BIRTHDATE).toLocalDate());
            patient.setBalance(resultSet.getBigDecimal(BALANCE));
            optionalPatient = Optional.of(patient);
        } catch (SQLException e) {
            optionalPatient = Optional.empty();
        }
        return optionalPatient;
    }
}
