package entity;

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
    private Specialization specializationId;

    public Doctor() {
    }

    public Doctor(Category category, LocalDate experience, String photoPath, Specialization specializationId) {
        this.category = category;
        this.experience = experience;
        this.photoPath = photoPath;
        this.specializationId = specializationId;
    }

    public Doctor(long userId, String firstName, String middleName, String lastName,
                  String login, String password, String email, String phoneNumber,
                  LocalDateTime registrationDate, UserState state, UserRole role,
                  Category category, LocalDate experience, String photoPath, Specialization specializationId) {
        super(userId, firstName, middleName, lastName, login, password, email, phoneNumber, state, role);
        this.category = category;
        this.experience = experience;
        this.photoPath = photoPath;
        this.specializationId = specializationId;
    }


}
