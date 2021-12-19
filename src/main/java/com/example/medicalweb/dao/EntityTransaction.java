package com.example.medicalweb.dao;

import com.example.medicalweb.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.medicalweb.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {
    static Logger logger = LogManager.getLogger();
    private Connection connection;

    public void begin(AbstractDao dao, AbstractDao... daos) {
        if (connection == null) {
            connection = ConnectionPool.getInstance().takeConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to change connection autocommit", e);
        }
        dao.setConnection(connection);
        for (AbstractDao daoElement : daos) {
            daoElement.setConnection(connection);
        }
    }

    public void end() throws ConnectionPoolException {
        //check
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Failed to change connection autocommit", e);
            }
            ConnectionPool.getInstance().releaseConnection(connection);
            connection = null;
        }
    }
    public void commit(){
        try{
            connection.commit();
        } catch (SQLException e){
            logger.log(Level.ERROR, "Failed to commit", e);
        }
    }
    public void rollback(){
        try{
            connection.rollback();
        } catch (SQLException e){
            logger.log(Level.ERROR, "", e);
        }
    }

}
