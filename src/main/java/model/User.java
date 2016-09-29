package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Alexeev on 29.09.2016.
 */
public class User {
    private long id;

    private String email;
    private String hash;
    private String firstName;
    private String lastName;

    private char gender;

    private Date birthDate;

    private String phone;

    private int role_id;

    private Timestamp regDate;

    public User(long id, String email, String hash, String firstName, String lastName, char gender, Date birthDate, String phone, int role_id, Timestamp regDate) {
        this.id = id;
        this.email = email;
        this.hash = hash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phone = phone;
        this.role_id = role_id;
        this.regDate = regDate;
    }
}
