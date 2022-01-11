package by.epam.medicalweb.util.impl;

import by.epam.medicalweb.util.Validator;

import java.util.Map;

public class ValidatorImpl implements Validator {
 /*   private static ValidatorImpl instance;
    private static final String INCORRECT_VALUE_PARAMETER = "incorrect";
    private static final String NAME_REGEX = "[А-ЯA-Z][а-яa-z]{3,30}";
    private static final String LOGIN_REGEX = "[a-zA-Z][A-Za-z\\d]{4,30}";
    private static final String PASSWORD_REGEX = "[a-zA-Z][A-Za-z\\d]{7,45}";
    private static final String EMAIL_REGEX = "(([A-Za-z\\d._]+){3,30}@([a-z]+){2,7}\\.([a-z]+){2,3})";
    private static final String BIRTDATE_REGEX = "
    private static final String PHONE_NUMBER_REGEX = "\\+375\\(\\d{2}\\)\\d{3}-\\d{2}-\\d{2}";
    private static final String PHONE_NUMBER_SECOND_REGEX = "\\d{9}";


    private ValidatorImpl() {
    }

    public static ValidatorImpl getInstance() {
        if (instance == null) {
            instance = new ValidatorImpl();
        }
        return instance;
    }
    @Override
    public boolean checkName(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    @Override
    public boolean checkLogin(String login) {
        return login != null && login.matches(LOGIN_REGEX);
    }

    @Override
    public boolean checkPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }


    @Override
    public boolean checkEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    @Override
    public boolean checkPhoneNumber(String number) {
        return number != null && number.matches(PHONE_NUMBER_REGEX);
    }

    @Override
    public boolean checkUserData(Map<String, String> userData) {
        boolean isValid = true;
        if (!checkName(userData.get(NAME))) {
            userData.put(NAME, INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkLogin(userData.get(LOGIN))) {
            userData.put(LOGIN, INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkPassword(userData.get(PASSWORD))) {
            userData.put(PASSWORD, INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }

        if (!checkEmail(userData.get(EMAIL))) {
            userData.put(EMAIL, INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkPhoneNumber(userData.get(PHONE_NUMBER))) {
            userData.put(PHONE_NUMBER, INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        return isValid;
    }*/
}
