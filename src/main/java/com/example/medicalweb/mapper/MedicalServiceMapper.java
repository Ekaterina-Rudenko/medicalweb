package com.example.medicalweb.mapper;

import com.example.medicalweb.entity.MedicalService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MedicalServiceMapper implements BaseMapper<MedicalService> {
    public static final String SERVICE_ID = "service_id";
    public static final String SERVICE_NAME = "service_name";
    public static final String SERVICE_PRICE = "service_price";

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
