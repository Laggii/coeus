package service;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static service.ErrorCode.*;


/**
 * Created by Alexeev on 09.10.2016.
 */
public class TestInputValidator {
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
        Arrays.stream(CORRECT_EMAILS).forEach(email -> assertTrue(InputValidator.validateEmail(email)));
        Arrays.stream(INCORRECT_EMAILS).forEach(email -> assertFalse(InputValidator.validateEmail(email)));
    }

    @Test
    public void testValidateName() {
        Arrays.stream(CORRECT_NAMES).forEach(name -> assertTrue(InputValidator.validateName(name)));
        Arrays.stream(INCORRECT_NAMES).forEach(name -> assertFalse(InputValidator.validateName(name)));
    }

    @Test
    public void testValidatePassword() {
        assertTrue(InputValidator.validatePassword("q1w2e3r4t5y6"));
        assertFalse(InputValidator.validatePassword(""));
        assertFalse(InputValidator.validatePassword(null));
    }

    @Test
    public void testValidateId() {
        assertTrue(InputValidator.validateId("1"));
        assertTrue(InputValidator.validateId("100"));
        assertFalse(InputValidator.validateId("1'"));
        assertFalse(InputValidator.validateId("'1"));
        assertFalse(InputValidator.validateId("=1;"));
        assertFalse(InputValidator.validateId(""));
    }

    @Test
    public void testValidateRegistration() {
        String email = "example@example.com";
        String firstName = "John";
        String lastName = "Doe";
        String password = "unhashedpass";
        String repeatPassword = "unhashedpass";
        assertEquals(VALID, InputValidator.validateRegistration(email, firstName, lastName, password, repeatPassword));

        email = "@";
        assertEquals(EMAIL_INVALID_ERROR,
                InputValidator.validateRegistration(email, firstName, lastName, password, repeatPassword));

        email = "example@example.com";
        repeatPassword = "anotherpassword";
        assertEquals(REPEAT_PASSWORD_INVALID_ERROR,
                InputValidator.validateRegistration(email, firstName, lastName, password, repeatPassword));

        repeatPassword = "unhashedpass";
        firstName = "John'";
        assertEquals(FIRSTNAME_INVALID_ERROR,
                InputValidator.validateRegistration(email, firstName, lastName, password, repeatPassword));

        firstName = "John";
        lastName = ";Doe";
        assertEquals(LASTNAME_INVALID_ERROR,
                InputValidator.validateRegistration(email, firstName, lastName, password, repeatPassword));

        lastName = "Doe";
        password = "short";
        repeatPassword = "short";
        assertEquals(PASSWORD_INVALID_ERROR,
                InputValidator.validateRegistration(email, firstName, lastName, password, repeatPassword));
    }

}
