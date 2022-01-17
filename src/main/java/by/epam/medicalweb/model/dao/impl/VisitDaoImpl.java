package by.epam.medicalweb.model.dao.impl;

import by.epam.medicalweb.model.dao.AbstractDao;
import by.epam.medicalweb.model.dao.VisitDao;
import by.epam.medicalweb.model.entity.Visit;
import by.epam.medicalweb.exception.DaoException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class VisitDaoImpl extends AbstractDao<Visit> implements VisitDao {

    private static final String SQL_SELECT_ALL_VISITS = """
             SELECT spec_name,u1.last_name AS doctor_surname, service_name, visit_date, visit_time, u2.last_name AS patient_surname, type_payment, visit_state
            FROM visits
                JOIN specializations ON specializations.spec_id = visits.spec_id
                JOIN users u1 ON u1.user_id = doctor_id
                JOIN services ON services.service_id = visits.service_id
                JOIN users u2 ON u2.user_id = visits.patient_id;""";
    private static final String SQL_SELECT_VISIT_BY_ID = """
            SELECT spec_name,u1.last_name AS doctor_surname, service_name, visit_date, visit_time,u2.last_name AS patient_surname, type_payment, visit_state
            FROM visits
                JOIN specializations ON specializations.spec_id = visits.spec_id
                JOIN users u1 ON u1.user_id = doctor_id
                JOIN services ON services.service_id = visits.service_id
                JOIN users u2 ON u2.user_id = visits.patient_id
            WHERE visits.visit_id = ?;""";
    private static final String SQL_SELECT_VISITS_BY_DOCTOR_ID = """
             SELECT spec_name,u1.last_name AS doctor_surname, service_name, visit_date,visit_time, u2.last_name AS patient_surname, type_payment, visit_state
            FROM visits
                JOIN specializations ON specializations.spec_id = visits.spec_id
                JOIN users u1 ON u1.user_id = doctor_id
                JOIN services ON services.service_id = visits.service_id
                JOIN users u2 ON u2.user_id = visits.patient_id
            WHERE visits.doctor_id = ?;""";
    private static final String SQL_SELECT_VISITS_BY_SPECIALIZATION_ID = """
             SELECT spec_name,u1.last_name AS doctor_surname, service_name, visit_date,visit_time, u2.last_name AS patient_surname, type_payment, visit_state
            FROM visits
                JOIN specializations ON specializations.spec_id = visits.spec_id
                JOIN users u1 ON u1.user_id = doctor_id
                JOIN services ON services.service_id = visits.service_id
                JOIN users u2 ON u2.user_id = visits.patient_id
            WHERE visits.specialization_id = ?;""";
    private static final String SQL_SELECT_VISITS_BY_SERVICE_ID = """
            SELECT spec_name,u1.last_name AS doctor_surname, service_name, visit_date,visit_time, u2.last_name AS patient_surname, type_payment, visit_state
            FROM visits
                JOIN specializations ON specializations.spec_id = visits.spec_id
                JOIN users u1 ON u1.user_id = doctor_id
                JOIN services ON services.service_id = visits.service_id
                JOIN users u2 ON u2.user_id = visits.patient_id
            WHERE visits.service_id = ?;""";
    private static final String SQL_SELECT_VISITS_BY_VISIT_DATE = """
            SELECT spec_name,u1.last_name AS doctor_surname, service_name, visit_date,visit_time, u2.last_name AS patient_surname, type_payment, visit_state
            FROM visits
                JOIN specializations ON specializations.spec_id = visits.spec_id
                JOIN users u1 ON u1.user_id = doctor_id
                JOIN services ON services.service_id = visits.service_id
                JOIN users u2 ON u2.user_id = visits.patient_id
            WHERE visits.visit_date = ?;""";
    private static final String SQL_SELECT_VISITS_BY_DOCTOR_ID_AND_VISIT_DATE = """
             SELECT spec_name,u1.last_name AS doctor_surname, service_name, visit_date, visit_time, u2.last_name AS patient_surname, type_payment, visit_state
            FROM visits
                JOIN specializations ON specializations.spec_id = visits.spec_id
                JOIN users u1 ON u1.user_id = doctor_id
                JOIN services ON services.service_id = visits.service_id
                JOIN users u2 ON u2.user_id = visits.patient_id
            WHERE visits.doctor_id = ? AND visits.visit_date = ?;""";
    private static final String SQL_SELECT_VISITS_BY_SPECIALIZATION_ID_AND_VISIT_DATE = """
               SELECT spec_name,u1.last_name AS doctor_surname, service_name, visit_date,visit_time, u2.last_name AS patient_surname, type_payment, visit_state
            FROM visits
                JOIN specializations ON specializations.spec_id = visits.spec_id
                JOIN users u1 ON u1.user_id = doctor_id
                JOIN services ON services.service_id = visits.service_id
                JOIN users u2 ON u2.user_id = visits.patient_id
            WHERE visits.specialization_id = ? AND visits.visit_date = ?;""";
    private static final String SQL_SELECT_VISITS_BY_SERVICE_ID_AND_VISIT_DATE = """
               SELECT spec_name,u1.last_name AS doctor_surname, service_name, visit_date,visit_time, u2.last_name AS patient_surname, type_payment, visit_state
            FROM visits
                JOIN specializations ON specializations.spec_id = visits.spec_id
                JOIN users u1 ON u1.user_id = doctor_id
                JOIN services ON services.service_id = visits.service_id
                JOIN users u2 ON u2.user_id = visits.patient_id
            WHERE visits.service_id = ? AND visits.visit_date = ?;""";
    private static final String SQL_SELECT_VISITS_BY_TYPE_PAYMENT = """
              SELECT spec_name,u1.last_name AS doctor_surname, service_name, visit_date, visit_time, u2.last_name AS patient_surname, type_payment, visit_state
            FROM visits
                JOIN specializations ON specializations.spec_id = visits.spec_id
                JOIN users u1 ON u1.user_id = doctor_id
                JOIN services ON services.service_id = visits.service_id
                JOIN users u2 ON u2.user_id = visits.patient_id
            WHERE visits.type_payment = ?;""";
    private static final String SQL_SELECT_VISITS_BY_VISIT_STATE = """
               SELECT spec_name,u1.last_name AS doctor_surname, service_name, visit_date, visit_time, u2.last_name AS patient_surname, type_payment, visit_state
            FROM visits
                JOIN specializations ON specializations.spec_id = visits.spec_id
                JOIN users u1 ON u1.user_id = doctor_id
                JOIN services ON services.service_id = visits.service_id
                JOIN users u2 ON u2.user_id = visits.patient_id
            WHERE visits.visit_state = ?;""";
    private static final String SQL_SELECT_VISITS_BY_PATIENT_ID = """
            SELECT spec_name,u1.last_name AS doctor_surname, service_name, visit_date, visit_time, u2.last_name AS patient_surname, type_payment, visit_state
            FROM visits
                JOIN specializations ON specializations.spec_id = visits.spec_id
                JOIN users u1 ON u1.user_id = doctor_id
                JOIN services ON services.service_id = visits.service_id
                JOIN users u2 ON u2.user_id = visits.patient_id
            WHERE visits.patient_id = ?;""";
    private static final String SQL_DELETE_VISIT_BY_ID = """
            DELETE FROM visits WHERE visit_id = (?)""";
    private static final String SQL_INSERT_NEW_VISIT = """
            INSERT INTO visits (specialization_id, doctor_id, service_id, visit_date, 
            type_payment, visit_state, patient_id) VALUES (?, ?, ?, ?, ?, ?, ?)""";
    private static final String SQL_UPDATE_VISIT_DATE_AND_TIME_BY_VISIT_ID = """
            UPDATE visits SET visit_date = ?, visit_time = ? WHERE visit_id = ?;""";
    private static final String SQL_UPDATE_VISIT_TIME_BY_VISIT_ID = """
            UPDATE visits SET visit_time = ? WHERE visit_id = ?;""";
    private static final String SQL_UPDATE_VISIT_STATE_BY_VISIT_ID = """
            UPDATE visits SET visit_state = ? WHERE visit_id = ?;""";

    private static final String SQL_SELECT_FREE_TIME_SLOTS_BY_DATE_AND_DOCTOR_ID = """
            SELECT time_slots.slot
            FROM time_slots
            JOIN visits ON visits.visit_date = ? AND visits.doctor_id = ?
            WHERE slot BETWEEN ? AND ?
            AND slot <> visit_time;""";


    @Override
    public List<Visit> findAll() {

        return null;
    }

    @Override
    public Optional<Visit> findEntityById(long id) {
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
    public long create(Visit entity) {
        return 0;
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
