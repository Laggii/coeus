package model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Alexeev on 29.09.2016.
 */
public class User {

    private long userId;

    private String email;
    private String hash;

    private String firstName;
    private String lastName;

    private char gender;

    private Date birthDate;

    private String phone;

    private int roleId;

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

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    private User(Builder builder) {
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

    public static class Builder {
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

        public Builder setUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setHash(String hash) {
            this.hash = hash;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setGender(char gender) {
            this.gender = gender;
            return this;
        }

        public Builder setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setRoleId(int roleId) {
            this.roleId = roleId;
            return this;
        }

        public Builder setRegDate(Timestamp regDate) {
            this.regDate = regDate;
            return this;
        }

        public User build() {
            if (email == null || hash == null || lastName == null || firstName == null ) {
                throw new IllegalArgumentException("User required parameters are empty");
            }
            return new User(this);
        }
    }
}
