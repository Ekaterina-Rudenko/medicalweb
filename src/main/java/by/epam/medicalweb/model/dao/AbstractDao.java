package by.epam.medicalweb.model.dao;

import by.epam.medicalweb.model.entity.AbstractEntity;
import by.epam.medicalweb.exception.DaoException;
import by.epam.medicalweb.pool.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao <T extends AbstractEntity> {
    static Logger logger = LogManager.getLogger();
    protected ProxyConnection proxyConnection;

    public abstract List<T> findAll() throws DaoException;
    public abstract Optional<T> findEntityById(long id) throws DaoException;
    public abstract boolean delete(long id) throws DaoException;
    public abstract boolean delete(T entity) throws DaoException;
    public abstract long create(T entity) throws DaoException;

    protected void closeStatement(Statement statement){
        try{
            if (statement != null){
                statement.close();
            }
        } catch (SQLException e){
            logger.log(Level.ERROR, "Failed to close statement", e);
        }
    }

    void setConnection(Connection connection) {
        this.proxyConnection = (ProxyConnection) connection;}
}
