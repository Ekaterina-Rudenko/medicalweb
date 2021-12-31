package by.epam.medicalweb.model.mapper.impl;

import by.epam.medicalweb.model.entity.Prescription;
import by.epam.medicalweb.model.mapper.BaseMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import static by.epam.medicalweb.model.dao.ColumnName.*;

public class PrescriptionMapper implements BaseMapper<Prescription> {

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
