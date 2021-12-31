package by.epam.medicalweb.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Patient extends User {
    public enum Gender {
        MALE("male"), FEMALE("female");
        String gender;

        Gender(String gender) {
            this.gender = gender;
        }

        public String getGender() {
            return gender;
        }
    }

    private Gender gender;
    private LocalDate birthDate;
    private BigDecimal balance;

    public Patient() {
    }

    public Patient(Gender gender, LocalDate birthDate, BigDecimal balance) {
        this.gender = gender;
        this.birthDate = birthDate;
        this.balance = balance;
    }

    public Patient(long userId, String firstName, String middleName, String lastName, String login, String password, String email, String phoneNumber, LocalDateTime registrationDate, UserState state, UserRole role, Gender gender, LocalDate birthDate, BigDecimal balance) {
        super(userId, firstName, middleName, lastName, login, password, email, phoneNumber, state, role, registrationDate);
        this.gender = gender;
        this.birthDate = birthDate;
        this.balance = balance;
    }
    public static class PatientBuilder {
        private Patient patient = new Patient();

        public PatientBuilder setUserId(long id) {
            patient.setUserId(id);
            return this;
        }

        public PatientBuilder setFirstName(String name) {
            patient.setFirstName(name);
            return this;
        }

        public PatientBuilder setMiddleName(String middleName) {
            patient.setMiddleName(middleName);
            return this;
        }

        public PatientBuilder setLastName(String lastName) {
            patient.setLastName(lastName);
            return this;
        }

        public PatientBuilder setLogin(String login) {
            patient.setLogin(login);
            return this;
        }

        public PatientBuilder setPassword(String password) {
            patient.setPassword(password);
            return this;
        }

        public PatientBuilder setEmail(String email) {
            patient.setEmail(email);
            return this;
        }

        public PatientBuilder setPhone(String phone) {
            patient.setPhoneNumber(phone);
            return this;
        }


        public PatientBuilder setState(UserState state) {
            patient.setState(state);
            return this;
        }

        public PatientBuilder setRole(UserRole role) {
            patient.setRole(role);
            return this;
        }

        public PatientBuilder setRegistrationDate(LocalDateTime date) {
            patient.setRegistrationDate(date);
            return this;
        }
        public PatientBuilder setGender(Gender gender) {
            patient.setGender(gender);
            return this;
        }

        public PatientBuilder setBalance(BigDecimal balance) {
            patient.setBalance(balance);
            return this;
        }

        public PatientBuilder setBirthDate(LocalDate birthDate) {
            patient.setBirthDate(birthDate);
            return this;
        }

        public Patient build() {
            return patient;
        }
    }


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return gender == patient.gender &&
                birthDate != null ? birthDate.equals(patient.birthDate) : patient.birthDate == null &&
                balance != null ? (balance.compareTo(patient.balance) == 0) : patient.balance == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Patient{");
        sb.append("gender=").append(gender);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
