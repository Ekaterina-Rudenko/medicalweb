package by.epam.medicalweb.model.mapper.impl;

import by.epam.medicalweb.model.entity.Specialization;
import by.epam.medicalweb.model.mapper.BaseMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import static by.epam.medicalweb.model.dao.ColumnName.*;

public class SpecializationMapper implements BaseMapper<Specialization> {

    @Override
    public Optional<Specialization> mapEntity(ResultSet resultSet){
        Specialization specialization = new Specialization();
        Optional<Specialization> optionalSpecialization;
        try {
            specialization.setSpecializationId(resultSet.getLong(SPECIALIZATION_ID));
            specialization.setName(resultSet.getString(SPECIALIZATION_NAME));
            optionalSpecialization = Optional.of(specialization);
        } catch (SQLException e) {
            optionalSpecialization = Optional.empty();
        }
        return optionalSpecialization;
    }
}
