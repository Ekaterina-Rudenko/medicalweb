package by.epam.medicalweb.model.entity;

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

    public static final int START_OF_WORKING_DAY = 8;
    public static final int FINISH_OF_WORKING_DAY = 18;
    private Category category;
    private String photoPath;
    private long specializationId;

    public Doctor() {
    }

    public Doctor(Category category, String photoPath, long specializationId) {
        this.category = category;
        this.photoPath = photoPath;
        this.specializationId = specializationId;
    }

    public Doctor(long userId, String firstName, String middleName, String lastName,
                  String login, String password, String email, String phoneNumber,
                  LocalDateTime registrationDate, UserState state, UserRole role,
                  Category category, LocalDate experience, String photoPath, long specializationId) {
        super(userId, firstName, middleName, lastName, login, password, email, phoneNumber, state, role, registrationDate);
        this.category = category;
        this.photoPath = photoPath;
        this.specializationId = specializationId;
    }
    public static class DoctorBuilder {
        private Doctor doctor = new Doctor();

        public DoctorBuilder setUserId(long id) {
            doctor.setUserId(id);
            return this;
        }

        public DoctorBuilder setFirstName(String name) {
            doctor.setFirstName(name);
            return this;
        }

        public DoctorBuilder setMiddleName(String middleName) {
            doctor.setMiddleName(middleName);
            return this;
        }

        public DoctorBuilder setLastName(String lastName) {
            doctor.setLastName(lastName);
            return this;
        }

        public DoctorBuilder setLogin(String login) {
            doctor.setLogin(login);
            return this;
        }

        public DoctorBuilder setPassword(String password) {
            doctor.setPassword(password);
            return this;
        }

        public DoctorBuilder setEmail(String email) {
            doctor.setEmail(email);
            return this;
        }

        public DoctorBuilder setPhone(String phone) {
            doctor.setPhoneNumber(phone);
            return this;
        }

        public DoctorBuilder setState(UserState state) {
            doctor.setState(state);
            return this;
        }

        public DoctorBuilder setRole(UserRole role) {
            doctor.setRole(role);
            return this;
        }

        public DoctorBuilder setRegistrationDate(LocalDateTime date) {
            doctor.setRegistrationDate(date);
            return this;
        }
        public DoctorBuilder setCategory(Category category) {
            doctor.setCategory(category);
            return this;
        }

        public DoctorBuilder setPhotoPath(String photoPath) {
            doctor.setPhotoPath(photoPath);
            return this;
        }

        public DoctorBuilder setSpecialization(long specializationId) {
            doctor.setSpecializationId(specializationId);
            return this;
        }

        public Doctor build() {
            return doctor;
        }
    }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
