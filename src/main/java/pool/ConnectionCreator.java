package pool;

import exception.ConnectionPoolException;
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

public class ConnectionCreator {
    static Logger logger = LogManager.getLogger();
    private static ConnectionCreator instance;
    private static final String PROPERTY_FILE_PATH = "data\\database.properties";
    private static final Properties properties = new Properties();
    private static String propertyFile;

    private static final String DB_URL;
    private static final String DB_DRIVER;

    static {
        try {
            ClassLoader loader = ConnectionCreator.class.getClassLoader();
            URL resource = loader.getResource(PROPERTY_FILE_PATH);
            if (resource == null) {
                logger.log(Level.ERROR, "File was not found");
                throw new IllegalArgumentException();
            }
            propertyFile = resource.getFile();
            properties.load(new FileReader(propertyFile));
            DB_DRIVER = (String) properties.get("db.driver");
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
               instance = new ConnectionCreator();}
           return instance;}

    static int getPoolSize() {
        int poolSize = Integer.parseInt((String) properties.get("poolSize"));
        return poolSize;
    }

    static Connection createConnection() throws ConnectionPoolException {
        try {
            return DriverManager.getConnection(DB_URL, properties);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Connection was not created", e);
        }
    }
}

