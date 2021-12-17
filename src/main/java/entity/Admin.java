package entity;

import java.time.LocalDateTime;

public class Admin extends User {
    private String photoPath;

    public Admin() {
    }

    public Admin(String photoPath){
        this.photoPath = photoPath;
    }

    public Admin(long userId, String firstName, String middleName, String lastName, String login, String password, String email, String phoneNumber, UserState state, LocalDateTime registrationDate, UserRole role, String photoPath) {
        super(userId, firstName, middleName, lastName, login, password, email, phoneNumber, state, role);
        this.photoPath = photoPath;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;
        return (photoPath != null ? admin.equals(admin.photoPath) : admin.getPhotoPath() == null);
    }
}
