package by.epam.medicalweb.model.mapper.impl;

import by.epam.medicalweb.model.entity.*;
import by.epam.medicalweb.model.mapper.BaseMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static by.epam.medicalweb.model.dao.ColumnName.*;

public class VisitMapper implements BaseMapper<Visit> {

    public Optional<Visit> mapEntityWithNames(ResultSet resultSet) {
        Visit visit = new Visit();
        Optional<Visit> optionalVisit = null;
        try {
            visit.setVisitId(resultSet.getLong(VISIT_ID));
            visit.setSpecialization(new Specialization.Builder()
                    .setSpecializationName(resultSet.getString(SPECIALIZATION_NAME))
                    .build());
            visit.setDoctor(new Doctor.DoctorBuilder()
                    .setLastName(resultSet.getString(LAST_NAME))
                    .build());
            visit.setService(new MedicalService.ServiceBuilder()
                    .setServiceName(resultSet.getString(SERVICE_NAME))
                    .build());
            visit.setDate(resultSet.getDate(VISIT_DATE).toLocalDate());
            visit.setTime(resultSet.getInt(VISIT_TIME));
            visit.setTypeOfPayment(Visit.TypePayment.valueOf(resultSet.getString(TYPE_PAYMENT).trim().toUpperCase()));
            visit.setState(Visit.VisitState.valueOf(resultSet.getString(VISIT_STATE).trim().toUpperCase()));
            visit.setPatient(new Patient.PatientBuilder()
                    .setLastName(resultSet.getString(LAST_NAME))
                    .build());
            optionalVisit = Optional.of(visit);
        } catch (SQLException e) {
            optionalVisit = Optional.empty();
        }
        return optionalVisit;
    }

    @Override
    public Optional<Visit> mapEntity(ResultSet resultSet) {
        Visit visit = new Visit();
        Optional<Visit> optionalVisit = null;
        try {
            visit.setVisitId(resultSet.getLong(VISIT_ID));
            visit.setSpecialization(new Specialization.Builder()
                    .setSpecializationId(resultSet.getLong(SPECIALIZATION_ID))
                    .setSpecializationName(resultSet.getString(SPECIALIZATION_NAME))
                    .build());
            visit.setDoctor(new Doctor.DoctorBuilder()
                    .setUserId(resultSet.getLong(DOCTOR_ID))
                    .setFirstName(resultSet.getString(DOCTOR_NAME))
                    .setLastName(resultSet.getString(DOCTOR_SURNAME))
                    .build());
            visit.setService(new MedicalService.ServiceBuilder()
                    .setServiceId(resultSet.getLong(SERVICE_ID))
                    .setServiceName(resultSet.getString(SERVICE_NAME))
                    .build());
            visit.setDate(resultSet.getDate(VISIT_DATE).toLocalDate());
            visit.setTime(resultSet.getInt(VISIT_TIME));
            visit.setTypeOfPayment(Visit.TypePayment.valueOf(resultSet.getString(TYPE_PAYMENT).trim().toUpperCase()));
            visit.setState(Visit.VisitState.valueOf(resultSet.getString(VISIT_STATE).trim().toUpperCase()));
            visit.setPatient(new Patient.PatientBuilder()
                    .setUserId(resultSet.getLong(PATIENT_ID))
                    .setFirstName(resultSet.getString(PATIENT_NAME))
                    .setLastName(resultSet.getString(PATIENT_SURNAME))
                    .build());
            optionalVisit = Optional.of(visit);
        } catch (SQLException e) {
            optionalVisit = Optional.empty();
        }
        return optionalVisit;
    }

}
