package by.epam.medicalweb.util;

public interface Validator {
    boolean checkName(String name);
    boolean checkLogin(String login);
    boolean checkPassword(String password);
    boolean checkEmail(String email);
    boolean checkPhoneNumber(String email);
    boolean checkBirthDate(String number);
    boolean checkMoney(String price);
    boolean checkPhotoUrl(String photoUrl);

    /*   boolean checkUserData();*/



}
