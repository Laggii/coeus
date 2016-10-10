package service;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by Alexeev on 09.10.2016.
 */
public class TestFormValidator {
    private static final String[] CORRECT_EMAILS = {
            "email@example.com",
            "firstname.lastname@example.com",
            "email@subdomain.example.com",
            "firstname+lastname@example.com",
            "1234567890@example.com",
            "email@example-one.com",
            "_______@example.com",
            "email@example.name",
            "email@example.museum",
            "email@example.co.jp",
            "firstname-lastname@example.com"};

    private static final String[] INCORRECT_EMAILS = {
            "plainaddress",
            "#@%^%#$@#$@#.com",
            "@example.com",
            "Joe Smith <email@example.com>",
            "email.example.com",
            "“email”@example.com",
            "email@example@example.com",
            ".email@example.com",
            "email.@example.com",
            "email..email@example.com",
            "あいうえお@example.com",
            "email@example.com (Joe Smith)",
            "email@example",
            "email@-example.com",
            "email@example.web",
            "email@111.222.333.44444",
            "email@example..com",
            "Abc..123@example.com"};

    private static final String[] CORRECT_NAMES = {"John", "john", "doe", "Александр", "Григорій"};

    private static final String[] INCORRECT_NAMES = {
            "John ",
            " John",
            "Jo hn",
            "John'",
            "John`",
            "John~",
            "John!",
            "John@",
            "John#",
            "John$",
            "John;",
            "John^",
            "John\"",
            "John\\",
            "John/",
            "/John",
            "John.",
            "\\John",
            "O'John", //yep its not possible for the moment
            "John-Doe", //and this too
    };


    @Test
    public void testValidateEmail() {
        Arrays.stream(CORRECT_EMAILS).forEach(email -> assertTrue(FormValidator.validateEmail(email)));
        Arrays.stream(INCORRECT_EMAILS).forEach(email -> assertFalse(FormValidator.validateEmail(email)));
    }

    @Test
    public void testValidateName() {
        Arrays.stream(CORRECT_NAMES).forEach(name -> assertTrue(FormValidator.validateName(name)));
        Arrays.stream(INCORRECT_NAMES).forEach(name -> assertFalse(FormValidator.validateName(name)));
    }

    @Test
    public void testValidatePassword() {
        assertTrue(FormValidator.validatePassword("q1w2e3r4t5y6"));
        assertFalse(FormValidator.validatePassword(""));
        assertFalse(FormValidator.validatePassword(null));
    }

    @Test
    //TODO: if translated, dont forget to change reference here too
    public void testValidateRegistration() {
        String email = "example@example.com";
        String firstName = "John";
        String lastName = "Doe";
        String password = "unhashedpass";
        String repeatPassword = "unhashedpass";
        assertEquals("", FormValidator.validateRegistration(email, firstName, lastName, password, repeatPassword));

        email = "@";
        assertEquals("You must provide a valid email",
                FormValidator.validateRegistration(email, firstName, lastName, password, repeatPassword));

        email = "example@example.com";
        repeatPassword = "anotherpassword";
        assertEquals("Passwords do not match",
                FormValidator.validateRegistration(email, firstName, lastName, password, repeatPassword));

        repeatPassword = "unhashedpass";
        firstName = "John'";
        assertEquals("You must provide a valid first name 35 characters long. Only Unicode characters are allowed",
                FormValidator.validateRegistration(email, firstName, lastName, password, repeatPassword));

        firstName = "John";
        lastName = ";Doe";
        assertEquals("You must provide a valid last name 35 characters long. Only Unicode characters are allowed",
                FormValidator.validateRegistration(email, firstName, lastName, password, repeatPassword));

        lastName = "Doe";
        password = "short";
        repeatPassword = "short";
        assertEquals("Password length should be from 6 to 40 characters",
                FormValidator.validateRegistration(email, firstName, lastName, password, repeatPassword));
    }


}
