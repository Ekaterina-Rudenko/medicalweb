package dao.impl;

import dao.AbstractDao;
import dao.AdminDao;
import entity.Admin;

import java.util.List;

public class AdminDaoImpl extends AbstractDao<Admin> implements AdminDao {
    //private static final String SQL_SELECT_ALL_ADMINS =

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
