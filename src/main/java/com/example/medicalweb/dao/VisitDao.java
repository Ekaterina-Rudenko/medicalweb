package com.example.medicalweb.dao;

import com.example.medicalweb.entity.Visit;
import com.example.medicalweb.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

public interface VisitDao {
    List<Visit> findAllByPatientId(long patientId) throws DaoException;
    List<Visit> findAllByDoctorId(long doctorId) throws DaoException;
    List<Visit> findAllBySpecializationId(long specializationId) throws DaoException;
    List<Visit> findAllByServiceId(long serviceId) throws DaoException;
    List<Visit> findAllByVisitDate(LocalDate date) throws DaoException;
    List<Visit> findAllByPaymentType(Visit.TypePayment paymentType) throws DaoException;
    boolean updateVisitStateByVisitId(long visitId, Visit.VisitState visitState)throws DaoException;



}
