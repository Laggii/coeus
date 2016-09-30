package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Alexeev on 29.09.2016.
 */
public class User {

    private final long userId;

    private final String email;
    private final String hash;
    private final String firstName;
    private final String lastName;

    private final char gender;

    private final Date birthDate;

    private final String phone;

    private final int roleId;

    private final Timestamp regDate;

    public long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getHash() {
        return hash;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public char getGender() {
        return gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public int getRoleId() {
        return roleId;
    }

    public Timestamp getRegDate() {
        return regDate;
    }

    private User(UserBuilder builder) {
        userId = builder.userId;
        email = builder.email;
        hash = builder.hash;
        firstName = builder.firstName;
        lastName = builder.lastName;
        gender = builder.gender;
        birthDate = builder.birthDate;
        phone = builder.phone;
        roleId = builder.roleId;
        regDate = builder.regDate;
    }

    public static class UserBuilder {
        private long userId;

        private String email;
        private String hash;
        private String firstName;
        private String lastName;

        private char gender;

        private Date birthDate;

        private String phone;

        private int roleId;

        private Timestamp regDate;

        private UserBuilder() {
        }

        public UserBuilder setId(long userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setHash(String hash) {
            this.hash = hash;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setGender(char gender) {
            this.gender = gender;
            return this;
        }

        public UserBuilder setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public UserBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder setRoleId(int roleId) {
            this.roleId = roleId;
            return this;
        }

        public UserBuilder setRegDate(Timestamp regDate) {
            this.regDate = regDate;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
