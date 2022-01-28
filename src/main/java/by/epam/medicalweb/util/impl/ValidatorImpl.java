package by.epam.medicalweb.util.impl;

import by.epam.medicalweb.util.Validator;

public class ValidatorImpl implements Validator {
    private static ValidatorImpl instance;
    private static final String INCORRECT_VALUE_PARAMETER = "incorrect";
    private static final String NAME_REGEX = "[А-Я\\p{Upper}][а-яё\\p{Lower}]{1,30}";;
    private static final String LOGIN_REGEX = "\\w{4,30}";
    private static final String PASSWORD_REGEX = "\\w{7,45}";
    private static final String EMAIL_REGEX =  "(([\\w.]+){5,25}@([\\p{Lower}]+){3,7}\\.([\\p{Lower}]+){2,3})";
    private static final String BIRTHDATE_REGEX = "(((19\\d{2})|(20[0-2]\\d))-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1]))";;
    private static final String PHONE_NUMBER_REGEX = "(25|29|33|44)\\d{7}";;
    private static final String PHONE_NUMBER_SECOND_REGEX = "\\d{9}";
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
    public boolean checkBirthDate(String date) {
        return date != null && date.matches(BIRTHDATE_REGEX);
    }
    @Override
    public boolean checkMoney(String price) {
        return price != null && price.matches(MONEY_REGEX);
    }
    @Override
    public boolean checkPhotoUrl(String photoUrl) {
        return photoUrl != null && photoUrl.matches(PHOTO_URL_REGEX);
    }

/*    public boolean checkUserData(Map<String, String> userData) {
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
        return isValid;*/

}
