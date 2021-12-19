package com.example.medicalweb.dao;

import com.example.medicalweb.entity.AbstractEntity;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDao <T extends AbstractEntity> {
    static Logger logger = LogManager.getLogger();
    protected Connection connection;

    public abstract List<T> findAll();
    public abstract T findEntityById(long id);
    public abstract boolean delete(long id);
    public abstract boolean delete(T entity);
    public abstract boolean create(T entity);
    public abstract T update(T entity);

    public void close(Statement statement){
        try{
            if (statement != null){
                statement.close();
            }
        } catch (SQLException e){
            logger.log(Level.ERROR, "Failed to close statement", e);
        }
    }
    void setConnection(Connection connection) {this.connection = connection;}
}
