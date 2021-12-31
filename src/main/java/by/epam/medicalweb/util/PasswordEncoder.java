package by.epam.medicalweb.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    static Logger logger = LogManager.getLogger();
    public static String encryptPass(String password) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bytes = md5.digest(password.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes){
            builder.append(String.format("%02X", b));
        }
        return builder.toString();
    }
}
