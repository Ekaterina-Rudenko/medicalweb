package by.epam.medicalweb.util;

import by.epam.medicalweb.exception.DaoException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.apache.commons.codec.binary.StringUtils;

public class ImageEncoder {

  private static final String IMAGE_TYPE = "data:image/jpeg;base64,";
  private static final String DEFAULT_IMG = "C:/Ekaterina/medical_resource/pict/default.jpg";

  private ImageEncoder() {
  }

  public static String encodeImage(String imageName) throws DaoException {
    String image;
    try {
      if(imageName == null){
        imageName = DEFAULT_IMG;
      }
      File pict = new File(imageName);
      byte[] byteContent = Files.readAllBytes(pict.toPath());
      StringBuilder sb = new StringBuilder(IMAGE_TYPE);
      byte[] encodedImage = org.apache.commons.codec.binary.Base64.encodeBase64(byteContent);
      String base64String = StringUtils.newStringUtf8(encodedImage);
      image = sb.append(base64String).toString();
    } catch (IOException e) {
      throw new DaoException("Image bytes cannot be read", e);
    }
    return image;
  }

}
