package by.epam.medicalweb.util;

import java.util.Map;

public interface Validator {
    boolean isCorrectName(String name);
    boolean isCorrectLogin(String login);
    boolean isCorrectPassword(String password);
    boolean isCorrectEmail(String email);
    boolean isCorrectPhoneNumber(String email);
/*    boolean isCorrectBirthDate(String number);*/
    boolean checkMoney(String price);
    boolean isCorrectPhotoUrl(String photoUrl);
    boolean checkRegistration(Map<String, String> data);
}
