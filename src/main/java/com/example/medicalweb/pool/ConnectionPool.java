package com.example.medicalweb.pool;

import com.example.medicalweb.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    static Logger logger = LogManager.getLogger();
    private static ConnectionPool instance;
    private BlockingQueue<ProxyConnection> availableConnections;
    private BlockingQueue<ProxyConnection> usedConnections;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean create = new AtomicBoolean(false);
    private static final int POOL_SIZE = ConnectionCreator.getInstance().getPoolSize();

    private ConnectionPool() {
        availableConnections = new LinkedBlockingQueue<>(POOL_SIZE);
        usedConnections = new LinkedBlockingQueue<>();
        ConnectionCreator creator = ConnectionCreator.getInstance();
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                ProxyConnection connection = new ProxyConnection(creator.createConnection());
                availableConnections.offer(connection);
            } catch (ConnectionPoolException e) {
                logger.log(Level.ERROR, "Failed to create connection pool", e);
            }
        }
        if(availableConnections.size() < POOL_SIZE){
            for(int i = 0; i < (POOL_SIZE - availableConnections.size()); i++) {
                try {
                    ProxyConnection connection = new ProxyConnection(creator.createConnection());
                    availableConnections.offer(connection);
                } catch (ConnectionPoolException e) {
                    logger.log(Level.ERROR, "Failed to create connection pool", e);
                }
            }
        }
        if(availableConnections.isEmpty()){
            logger.log(Level.ERROR, "No connections were created");
            throw new ExceptionInInitializerError("No connections were created");
        }
    }

    public static ConnectionPool getInstance() {
        if (!create.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    create.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection takeConnection() {
        ProxyConnection connection = null;
        try {
            connection = availableConnections.take();
            usedConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Failed to take connection");
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws ConnectionPoolException {

        if (connection.getClass() != ProxyConnection.class) {
            logger.log(Level.ERROR, "Connection is not ProxyConnection");
            throw new ConnectionPoolException("Connection is not ProxyConnection");
        }
        ProxyConnection proxyConnection = (ProxyConnection) connection;
        try {
            usedConnections.remove(proxyConnection);
            availableConnections.put(proxyConnection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Interrupted exception while releasing connection", e);
            Thread.currentThread().interrupt();
        }
    }

    public void closePool() {
        for (int i = 0; i <= POOL_SIZE; i++) {
            try {
                availableConnections.take().reallyClose();
                logger.log(Level.INFO, "Connection was closed");
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Interrupted exception while closing pool", e);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
                logger.log(Level.INFO, "Driver was deregistered");
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Fail to deregister driver", e);
            }
        });
    }
}
