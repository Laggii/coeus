package service;

import org.apache.commons.validator.routines.EmailValidator;

import static service.ErrorCode.*;

/**
 * Created by Alexeev on 05.10.2016.
 */

/**
 * FormValidator provides functionality for testing form input such as email, phone, name, etc
 * Returns error code reason if validation fails
 */
public class FormValidator {

    private static final EmailValidator emailValidator = EmailValidator.getInstance();

    /**
     * Validate email string using Apache Commons EmailValidator
     *
     * @param email
     * @return true if email is valid
     */
    public static boolean validateEmail(String email) {
        return emailValidator.isValid(email);
    }

    /**
     * Validate name length and allowed characters (only Unicode)
     *
     * @param name
     * @return true if name is valid
     */
    public static boolean validateName(String name) {
        return name != null && !name.isEmpty() && name.length() <= 35 && name.matches("^\\pL*");
    }

    /**
     * Validate password length
     *
     * @param password
     * @return true if password is valid
     */
    public static boolean validatePassword(String password) {
        return password != null && !password.isEmpty() && password.length() <= 40 && password.length() >= 6;
    }

    /**
     * Validate login form
     *
     * @param email
     * @param password
     * @return valid code if valid
     */
    public static ErrorCode validateLogin(String email, String password) {
        if (!validateEmail(email)) {
            return EMAIL_INVALID_ERROR;
        }
        if (!validatePassword(password)) {
            return PASSWORD_INVALID_ERROR;
        }
        return VALID;
    }

    /**
     * Validate registration form
     *
     * @param email
     * @param firstName
     * @param lastName
     * @param password
     * @param repeatPassword
     * @return valid error code if valid
     */
    public static ErrorCode validateRegistration(String email, String firstName, String lastName, String password, String repeatPassword) {
        ErrorCode loginResult = validateLogin(email, password);
        if (loginResult != VALID) {
            return loginResult;
        }

        if (!validateName(firstName)) {
            return FIRSTNAME_INVALID_ERROR;
        }

        if (!validateName(lastName)) {
            return LASTNAME_INVALID_ERROR;
        }

        if (!validatePassword(repeatPassword)) {
            return PASSWORD_INVALID_ERROR;
        }

        if (!repeatPassword.equals(password)) {
            return REPEAT_PASSWORD_INVALID_ERROR;
        }

        return VALID;
    }
}
