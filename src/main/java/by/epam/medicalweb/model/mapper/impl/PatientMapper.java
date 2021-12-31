package by.epam.medicalweb.model.mapper.impl;

import by.epam.medicalweb.model.entity.Patient;
import by.epam.medicalweb.model.mapper.BaseMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import static by.epam.medicalweb.model.dao.ColumnName.*;

public class PatientMapper implements BaseMapper<Patient> {

    @Override
    public Optional<Patient> mapEntity(ResultSet resultSet) {
        Patient patient = new Patient();
        Optional<Patient> optionalPatient;
        try {
            UserMapper.setUserData(resultSet, patient);
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
