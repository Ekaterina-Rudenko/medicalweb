package by.epam.medicalweb.mapper.impl;

import by.epam.medicalweb.entity.Visit;
import by.epam.medicalweb.mapper.BaseMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class VisitMapper implements BaseMapper<Visit> {
    public static final String VISIT_ID = "visit_id";
    public static final String SPECIALIZATION_ID = "specialization_id";
    public static final String DOCTOR_ID = "doctor_id";
    public static final String SERVICE_ID = "service_id";
    public static final String VISIT_DATE = "visit_date";
    public static final String VISIT_TIME = "visit_time";
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
            visit.setDate(resultSet.getDate(VISIT_DATE).toLocalDate());
            visit.setTime(resultSet.getInt(VISIT_TIME));
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
