package by.epam.medicalweb.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class MailCreator {
    static Logger logger = LogManager.getLogger();
    static final String MAIL_CONFIGURATION_FILE = "configuration/mail.properties";

    public static void createMail(String mailTo, String subject, String body) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(getMailConfigurationPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            logger.log(Level.ERROR, "Mail configuration properties are not defined", e);
            throw new RuntimeException("Mail configuration properties are not defined", e);
        }
        MailThread mailThread = new MailThread(mailTo, subject, body, properties);
        mailThread.run();
    }

    private static String getMailConfigurationPath() {
        String filePath;
        ClassLoader classLoader = MailCreator.class.getClassLoader();
        URL resource = classLoader.getResource(MAIL_CONFIGURATION_FILE);
        if (resource == null) {
            logger.log(Level.ERROR, "File doesn't exist" + MAIL_CONFIGURATION_FILE);
            throw new IllegalArgumentException("Configuration file doesn't exist");
        }
        filePath = resource.getFile();
        return filePath;
    }


}
