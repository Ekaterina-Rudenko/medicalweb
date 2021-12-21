package by.epam.medicalweb.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Doctor extends User {
    public enum Category {
        FIRST("first"), SECOND("second"), HIGHER("higher");
        private String category;

        Category(String category) {
            this.category = category;
        }

        public String getCategory() {
            return category;
        }
    }

    private Category category;
    private LocalDate experience;
    private String photoPath;
    private long specializationId;

    public Doctor() {
    }

    public Doctor(Category category, LocalDate experience, String photoPath, long specializationId) {
        this.category = category;
        this.experience = experience;
        this.photoPath = photoPath;
        this.specializationId = specializationId;
    }

    public Doctor(long userId, String firstName, String middleName, String lastName,
                  String login, String password, String email, String phoneNumber,
                  LocalDateTime registrationDate, UserState state, UserRole role,
                  Category category, LocalDate experience, String photoPath, long specializationId) {
        super(userId, firstName, middleName, lastName, login, password, email, phoneNumber, state, role, registrationDate);
        this.category = category;
        this.experience = experience;
        this.photoPath = photoPath;
        this.specializationId = specializationId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getExperience() {
        return experience;
    }

    public void setExperience(LocalDate experience) {
        this.experience = experience;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public long getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(long specializationId) {
        this.specializationId = specializationId;
    }
}
