package by.epam.medicalweb.model.entity;

import java.time.LocalDateTime;

public class User extends AbstractEntity {
    public enum UserState {
        ACTIVE("active"), INACTIVE("inactive"), BLOCKED("blocked");
        private String state;

        UserState(String state) {
            this.state = state;
        }

        public String getStateValue() {
            return state;
        }
    }

    public enum UserRole {
        ADMIN("admin"), DOCTOR("doctor"), PATIENT("patient"), GUEST("guest");
        private String role;

        UserRole(String role) {
            this.role = role;
        }

        public String getRoleValue() {
            return role;
        }
    }

    private long userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private String phoneNumber;
    private UserState state;
    private UserRole role;
    private LocalDateTime registrationDate;


    public User() {
    }

    public User(long userId, String firstName, String middleName, String lastName,
                String login, String password, String email, String phoneNumber,
                UserState state, UserRole role, LocalDateTime registrationDate) {
        this.userId = userId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.role = role;
        this.registrationDate = registrationDate;
    }
    public User(String firstName, String middleName, String lastName,
                String login, String password, String email, String phoneNumber,
                UserState state, UserRole role) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.role = role;
    }
    public static class UserBuilder{
        private User user = new User();

        public UserBuilder setUserId(long id){
            user.userId = id;
            return this;
        }
        public UserBuilder setFirstName(String name){
            user.firstName = name;
            return this;
        }
        public UserBuilder setMiddleName(String middleName){
            user.middleName = middleName;
            return this;
        }
        public UserBuilder setLastName(String lastName){
            user.lastName = lastName;
            return this;
        }
        public UserBuilder setLogin(String login){
            user.login = login;
            return this;
        }
        public UserBuilder setPassword(String password){
            user.password = password;
            return this;
        }
        public UserBuilder setEmail(String email){
            user.email = email;
            return  this;
        }
        public UserBuilder setPhone(String phone){
            user.phoneNumber = phone;
            return  this;
        }
        public UserBuilder setState(UserState state){
            user.state = state;
            return this;
        }
        public UserBuilder setRole(UserRole role){
            user.role = role;
            return this;
        }
        public UserBuilder setRegistrationDate(LocalDateTime date){
            user.registrationDate = date;
            return this;
        }
        public User build(){
            return user;
        }

    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return (userId != user.userId) &&
                firstName != null ? firstName.equals(user.firstName) : user.firstName == null &&
                middleName != null ? middleName.equals(user.middleName) : user.middleName == null &&
                lastName != null ? lastName.equals(user.lastName) : user.lastName == null &&
                login != null ? login.equals(user.login) : user.login == null &&
                password != null ? password.equals(user.password) : user.password == null &&
                email != null ? email.equals(user.email) : user.email == null &&
                phoneNumber != null ? phoneNumber.equals(user.phoneNumber) : user.phoneNumber == null &&
                state == user.state &&
                role == user.role &&
                registrationDate != null ? registrationDate.equals(user.registrationDate) : user.registrationDate == null;
    }

    @Override
    public int hashCode() {
        int result = 31 * (int) userId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("userId=").append(userId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", state=").append(state);
        sb.append(", role=").append(role);
        sb.append(", registrationDate=").append(registrationDate);
        sb.append('}');
        return sb.toString();
    }
}
