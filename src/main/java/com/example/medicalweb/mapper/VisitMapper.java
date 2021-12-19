package com.example.medicalweb.mapper;

import com.example.medicalweb.entity.Visit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class VisitMapper implements BaseMapper<Visit> {
    public static final String VISIT_ID = "visit_id";
    public static final String SPECIALIZATION_ID = "specialization_id";
    public static final String DOCTOR_ID = "doctor_id";
    public static final String SERVICE_ID = "service_id";
    public static final String VISIT_DATE = "visit_date";
    public static final String TYPE_PAYMENT = "type_payment";
    public static final String VISIT_STATE = "visit_state";
    public static final String PATIENT_ID = "patient_id";

    @Override
    public Optional<Visit> mapEntity(ResultSet resultSet) {
        Visit visit = new Visit();
        Optional<Visit> optionalVisit;
        try {
            visit.setVisitId(resultSet.getLong(VISIT_ID));
            visit.setSpecializationId(resultSet.getLong(SPECIALIZATION_ID));
            visit.setDoctorId(resultSet.getLong(DOCTOR_ID));
            visit.setServiceId(resultSet.getLong(SERVICE_ID));
            visit.setDate(resultSet.getTimestamp(VISIT_DATE).toLocalDateTime());
            //todo Какие типы данных для времени в SQL и соответствующие в Java
            visit.setTypeOfPayment(Visit.TypePayment.valueOf(resultSet.getString(TYPE_PAYMENT).trim().toUpperCase()));
            visit.setState(Visit.VisitState.valueOf(resultSet.getString(VISIT_STATE).trim().toUpperCase()));
            visit.setPatientId(resultSet.getLong(PATIENT_ID));
            optionalVisit = Optional.of(visit);
        } catch (SQLException e) {
            optionalVisit = Optional.empty();
        }
        return optionalVisit;
    }
}
