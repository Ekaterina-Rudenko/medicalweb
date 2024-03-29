package by.epam.medicalweb.model.dao;

import by.epam.medicalweb.pool.ConnectionPool;
import by.epam.medicalweb.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction {

  static Logger logger = LogManager.getLogger();
  private Connection connection;

  public void beginTransaction(AbstractDao dao, AbstractDao... daos) {
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

  public void endTransaction() {
    //check
    if (connection != null) {
      try {
        connection.setAutoCommit(true);
        ConnectionPool.getInstance().releaseConnection(connection);
        connection = null;
      } catch (SQLException e) {
        logger.log(Level.ERROR, "Failed to change connection autocommit", e);
      } catch (ConnectionPoolException e) {
        logger.log(Level.DEBUG, "Error while releasing connection.", e);
      }
    }
  }

  public void beginQuery(AbstractDao dao) {
    if (connection == null) {
      connection = ConnectionPool.getInstance().takeConnection();
    }
    dao.setConnection(connection);
  }

  public void endQuery() throws ConnectionPoolException {
    if (connection != null) {
      ConnectionPool.getInstance().releaseConnection(connection);
    }
    connection = null;
  }

  public void commit() {
    try {
      connection.commit();
    } catch (SQLException e) {
      logger.log(Level.ERROR, "Failed to commit", e);
    }
  }

  public void rollback() {
    try {
      connection.rollback();
    } catch (SQLException e) {
      logger.log(Level.ERROR, "Failed to rollback", e);
    }
  }

}
