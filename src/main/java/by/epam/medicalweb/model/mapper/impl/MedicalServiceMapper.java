package by.epam.medicalweb.model.mapper.impl;

import by.epam.medicalweb.model.entity.MedicalService;
import by.epam.medicalweb.model.entity.Specialization;
import by.epam.medicalweb.model.mapper.BaseMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.medicalweb.model.dao.ColumnName.*;

public class MedicalServiceMapper implements BaseMapper<MedicalService> {
    private static Logger logger = LogManager.getLogger();

    @Override
    public Optional<MedicalService> mapEntity(ResultSet resultSet){
        MedicalService service = new MedicalService();
        Optional<MedicalService> optionalMedicalService;
        try {
            service.setServiceId(resultSet.getLong(SERVICE_ID));
            service.setSpecialization(new Specialization.Builder()
                    .setSpecializationName(resultSet.getString(SPECIALIZATION_NAME))
                    .build());
            service.setServiceName(resultSet.getString(SERVICE_NAME));
            service.setPrice(resultSet.getBigDecimal(SERVICE_PRICE));
            optionalMedicalService = Optional.of(service);
        } catch (SQLException e) {
            logger.log(Level.INFO, "Sql Exception in service mapper");
            optionalMedicalService = Optional.empty();
        }
        return optionalMedicalService;
    }
}
