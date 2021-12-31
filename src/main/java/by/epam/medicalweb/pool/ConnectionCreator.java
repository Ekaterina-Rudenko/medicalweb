package by.epam.medicalweb.pool;

import by.epam.medicalweb.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionCreator {
    static Logger logger = LogManager.getLogger();
    static ConnectionCreator instance;
    static final String PROPERTY_FILE_PATH = "data\\database.properties";
    static final Properties properties = new Properties();
    static final String PROPERTY_FILE;

    static final String DB_URL;
    static final String DB_DRIVER;
    static final int POOL_SIZE;

    static {
        try {
            ClassLoader loader = ConnectionCreator.class.getClassLoader();
            URL resource = loader.getResource(PROPERTY_FILE_PATH);
            if (resource == null) {
                logger.log(Level.ERROR, "File was not found");
                throw new IllegalArgumentException();
            }
            PROPERTY_FILE = resource.getFile();
            properties.load(new FileReader(PROPERTY_FILE));
            DB_DRIVER = (String) properties.get("db.driver");
            POOL_SIZE = Integer.parseInt((String) properties.get("poolSize"));
        } catch (IOException e) {
            logger.log(Level.FATAL, "Database properties are not defined", e);
            throw new RuntimeException("Database properties are not defined", e);
        }
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.log(Level.FATAL, "Failed to register database driver", e);
            throw new RuntimeException("Failed to register database driver", e);
        }
        DB_URL = (String) properties.get("db.url");
    }

    private ConnectionCreator() {
    }

    public static ConnectionCreator getInstance() {
        if (instance != null) {
            instance = new ConnectionCreator();
        }
        return instance;
    }

    static Connection createConnection() throws ConnectionPoolException {
        try {
            return DriverManager.getConnection(DB_URL, properties);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Connection was not created", e);
        }
    }
}

