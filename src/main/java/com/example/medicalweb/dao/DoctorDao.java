package com.example.medicalweb.dao;

import com.example.medicalweb.entity.Doctor;
import com.example.medicalweb.exception.DaoException;

import java.util.List;

public interface DoctorDao {
    List<Doctor> findAllByCategory(Doctor.Category category) throws DaoException;

}
