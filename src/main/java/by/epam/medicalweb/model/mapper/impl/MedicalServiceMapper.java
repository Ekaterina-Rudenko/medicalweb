package by.epam.medicalweb.model.mapper.impl;

import by.epam.medicalweb.model.entity.MedicalService;
import by.epam.medicalweb.model.mapper.BaseMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import static by.epam.medicalweb.model.dao.ColumnName.*;

public class MedicalServiceMapper implements BaseMapper<MedicalService> {

    @Override
    public Optional<MedicalService> mapEntity(ResultSet resultSet){
        MedicalService service = new MedicalService();
        Optional<MedicalService> optionalMedicalService;
        try {
            service.setServiceId(resultSet.getLong(SERVICE_ID));
            service.setServiceName(resultSet.getString(SERVICE_NAME));
            service.setPrice(resultSet.getBigDecimal(SERVICE_PRICE));
            optionalMedicalService = Optional.of(service);
        } catch (SQLException e) {
            optionalMedicalService = Optional.empty();
        }
        return optionalMedicalService;
    }
}
