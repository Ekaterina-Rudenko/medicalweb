package mapper;

import entity.MedicalService;
import entity.Specialization;
import exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SpecializationMapper implements BaseMapper<Specialization>{
    public static final String SPECIALIZATION_ID = "specialization_id";
    public static final String SPECIALIZATION_NAME = "specialization_name";

    @Override
    public Optional<Specialization> mapEntity(ResultSet resultSet) throws DaoException {
        Specialization specialization = new Specialization();
        Optional<Specialization> optionalSpecialization;
        try {
            specialization.setSpecializationId(resultSet.getLong(SPECIALIZATION_ID));
            specialization.setSpecializationName(resultSet.getString(SPECIALIZATION_NAME));
            optionalSpecialization = Optional.of(specialization);
        } catch (SQLException e) {
            optionalSpecialization = Optional.empty();
        }
        return optionalSpecialization;
    }
}
