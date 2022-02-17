package by.epam.medicalweb.util;

import jakarta.servlet.http.Part;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ImageUploader {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String UPLOAD_DIR = "C:/Ekaterina/medical_resource/pict/";
    public static String uploadImage(Part imagePart){
        String pathForDb = "";
        try(InputStream inputStream = imagePart.getInputStream()){
            String submittedFileName = imagePart.getSubmittedFileName();
            pathForDb = UPLOAD_DIR + submittedFileName;
            Path imagePath = new File(pathForDb).toPath();
            long bytes = Files.copy(
                    inputStream,
                    imagePath,
                    StandardCopyOption.REPLACE_EXISTING);
            LOGGER.log(Level.DEBUG, "Image uploaded successfully: " + bytes + " " + pathForDb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathForDb;
    }
}
