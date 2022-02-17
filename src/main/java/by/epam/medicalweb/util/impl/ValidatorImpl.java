package by.epam.medicalweb.util.impl;

import by.epam.medicalweb.util.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;


import static by.epam.medicalweb.controller.command.RequestParameterName.*;

public class ValidatorImpl implements Validator {
    private static Logger logger = LogManager.getLogger();
    private static ValidatorImpl instance;
    private static final String INCORRECT_VALUE_PARAMETER = "incorrect";
    private static final String NAME_REGEX = "^[A-Za-zА-Яа-я]{3,30}$";;
    private static final String LOGIN_REGEX = "\\w{3,30}";
    private static final String PASSWORD_REGEX = "[\\w.]{5,45}";
    private static final String EMAIL_REGEX =  "(([\\w.]+){3,30}@([\\p{Lower}]+){2,7}\\.([\\p{Lower}]+){2,4})";
    //private static final String BIRTHDATE_REGEX = "(((19\\d{2})|(20[0-2]\\d))-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1]))";;
    private static final String PHONE_NUMBER_REGEX = "\\d{9}";;
    private static final String MONEY_REGEX = "\\d{1,4}\\.?\\d{0,2}";
    private static final String PHOTO_URL_REGEX = "([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)";


    private ValidatorImpl() {
    }

    public static ValidatorImpl getInstance() {
        if (instance == null) {
            instance = new ValidatorImpl();
        }
        return instance;
    }
@Override
    public boolean isCorrectName(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    @Override
    public boolean isCorrectLogin(String login) {
        return login != null && login.matches(LOGIN_REGEX);
    }

    @Override
    public boolean isCorrectPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    @Override
    public boolean isCorrectEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    @Override
    public boolean isCorrectPhoneNumber(String number) {
        boolean result = true;
        if (number == null){
            logger.log(Level.ERROR, "number is null" );
            result = false;
        }
        if(!number.matches(PHONE_NUMBER_REGEX)){
            logger.log(Level.ERROR, "number doesn't match" );
            result=false;
        }
        return result;
    }
    /*@Override
    public boolean isCorrectBirthDate(String date) {
        return date != null && date.matches(BIRTHDATE_REGEX);
    }*/
    @Override
    public boolean checkMoney(String price) {
        return price != null && price.matches(MONEY_REGEX);
    }
    @Override
    public boolean isCorrectPhotoUrl(String photoUrl) {
        return photoUrl != null && photoUrl.matches(PHOTO_URL_REGEX);
    }

    @Override
    public boolean checkRegistration(Map<String, String> data) {
        boolean isValid = true;
        if (!isCorrectName(data.get(FIRST_NAME))) {
            data.put(FIRST_NAME, INVALID_FIRST_NAME);
            isValid = false;
        }
        if (!isCorrectName(data.get(MIDDLE_NAME))) {
            data.put(MIDDLE_NAME, INVALID_MIDDLE_NAME);
            isValid = false;
        }
        if (!isCorrectName(data.get(LAST_NAME))) {
            data.put(LAST_NAME, INVALID_LAST_NAME);
            isValid = false;
        }
        if (!isCorrectLogin(data.get(LOGIN))) {
            data.put(LOGIN, INVALID_LOGIN);
            isValid = false;
        }

        if (!isCorrectPassword(data.get(PASSWORD))) {
            data.put(PASSWORD, INVALID_PASSWORD);
            isValid = false;
        }
        if (!isCorrectPhoneNumber(data.get(PHONE))) {
            data.put(PHONE, INVALID_PHONE_NUMBER);
            isValid = false;
        }
        if (!isCorrectEmail(data.get(EMAIL))) {
            data.put(EMAIL, INVALID_EMAIL);
            isValid = false;
        }

        return isValid;
    }
}
