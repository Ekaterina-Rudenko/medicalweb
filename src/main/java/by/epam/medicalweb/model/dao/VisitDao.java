package by.epam.medicalweb.model.dao;

import by.epam.medicalweb.model.entity.Visit;
import by.epam.medicalweb.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

public interface VisitDao {
    static final int START_OF_WORKING_DAY = 8;
    static final int FINISH_OF_WORKING_DAY = 18;

    List<Visit> findVisitsByPatientId(long patientId) throws DaoException;
    List<Visit> findAllByDoctorId(long doctorId) throws DaoException;
    List<Visit> findAllBySpecializationId(long specializationId) throws DaoException;
    List<Visit> findAllByServiceId(long serviceId) throws DaoException;
    List<Visit> findAllByVisitDate(LocalDate date) throws DaoException;
    List<Visit> findAllByPaymentType(Visit.TypePayment paymentType) throws DaoException;
    boolean updateVisitDateAndTime(long visitId, LocalDate visitDate, int time) throws DaoException;
    boolean updateVisitStateByVisitId(long visitId, Visit.VisitState visitState)throws DaoException;
    List<Integer> findAvailableTime(LocalDate visitDate, long doctorId) throws DaoException;


}
