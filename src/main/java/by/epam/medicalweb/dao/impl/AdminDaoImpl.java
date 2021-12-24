package by.epam.medicalweb.dao.impl;

import by.epam.medicalweb.entity.Admin;
import by.epam.medicalweb.dao.AbstractDao;
import by.epam.medicalweb.dao.AdminDao;

import java.util.List;

public class AdminDaoImpl extends AbstractDao<Admin> implements AdminDao {
    /*private static final String SQL_FIND_ALL_ADMINS =*/


    @Override
    public List<Admin> findAll() {
        return null;
    }

    @Override
    public Admin findEntityById(long id) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean delete(Admin entity) {
        return false;
    }

    @Override
    public boolean create(Admin entity) {
        return false;
    }

    @Override
    public Admin update(Admin entity) {
        return null;
    }
}
