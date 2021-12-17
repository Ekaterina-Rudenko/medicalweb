package dao.impl;

import dao.AbstractDao;
import dao.VisitDao;
import entity.Visit;
import exception.DaoException;
import mapper.VisitMapper;

import java.time.LocalDate;
import java.util.List;

public class VisitDaoImpl extends AbstractDao<Visit> implements VisitDao {
    private static final String SQL_SELECT_ALL_VISITS = """
            SELECT visit_id, specialization_id, doctor_id, service_id, visit_date, 
            type_payment, visit_state, patient_id FROM visits""";
    private static final String SQL_SELECT_VISIT_BY_ID = """
            SELECT specialization_id, doctor_id, service_id, visit_date,
             type_payment, visit_state, patient_id FROM visits WHERE visit_id = (?)""";
    private static final String SQL_DELETE_VISIT_BY_ID = """
            DELETE FROM visits WHERE visit_id = (?)""";
    private static final String SQL_INSERT_NEW_VISIT = """
            INSERT INTO visits (specialization_id, doctor_id, service_id, visit_date, 
            type_payment, visit_state, patient_id) VALUES (?, ?, ?, ?, ?, ?, ?)""";
    private static final String SQL_UPDATE_NEW_VISIT


    @Override
    public List<Visit> findAll() {
        return null;
    }

    @Override
    public Visit findEntityById(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean delete(Visit entity) {
        return false;
    }

    @Override
    public boolean create(Visit entity) {
        return false;
    }

    @Override
    public Visit update(Visit entity) {
        return null;
    }

    @Override
    public List<Visit> findAllByPatientId(long patientId) throws DaoException {
        return null;
    }

    @Override
    public List<Visit> findAllByDoctorId(long doctorId) throws DaoException {
        return null;
    }

    @Override
    public List<Visit> findAllBySpecializationId(long specializationId) throws DaoException {
        return null;
    }

    @Override
    public List<Visit> findAllByServiceId(long serviceId) throws DaoException {
        return null;
    }

    @Override
    public List<Visit> findAllByVisitDate(LocalDate date) throws DaoException {
        return null;
    }

    @Override
    public List<Visit> findAllByPaymentType(Visit.TypePayment paymentType) throws DaoException {
        return null;
    }

    @Override
    public boolean updateVisitStateByVisitId(long visitId, Visit.VisitState visitState) throws DaoException {
        return false;
    }
}
