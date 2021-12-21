package by.epam.medicalweb.mapper.impl;

import by.epam.medicalweb.entity.Prescription;
import by.epam.medicalweb.mapper.BaseMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PrescriptionMapper implements BaseMapper<Prescription> {
    public static final String PRESCRIPTION_ID = "prescription_id";
    public static final String VISIT_ID = "visit_id";
    public static final String PRESCRIPTION_TEXT = "prescription_text";

    @Override
    public Optional<Prescription> mapEntity(ResultSet resultSet) {
        Prescription prescription = new Prescription();
        Optional<Prescription> optionalPrescription;
        try {
            prescription.setPrescriptionId(resultSet.getLong(PRESCRIPTION_ID));
            prescription.setVisitId(resultSet.getLong(VISIT_ID));
            prescription.setPrescriptionText(resultSet.getString(PRESCRIPTION_TEXT));
            optionalPrescription = Optional.of(prescription);
        } catch (SQLException e) {
            optionalPrescription = Optional.empty();
        }
        return optionalPrescription;
    }
}
