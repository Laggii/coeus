package service;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * Created by Alexeev on 05.10.2016.
 */

/**
 * FormValidator provides functionality for testing form input such as email, phone, name, etc
 * Returns error code reason if validation fails
 */
public class FormValidator {

    private static final EmailValidator emailValidator = EmailValidator.getInstance();

    //TODO: move and translate (enum maybe?)
    private static final String EMAIL_INVALID_ERROR = "You must provide a valid email";
    private static final String FIRSTNAME_INVALID_ERROR = "You must provide a valid first name 35 characters long. Only Unicode characters are allowed";
    private static final String LASTNAME_INVALID_ERROR = "You must provide a valid last name 35 characters long. Only Unicode characters are allowed";
    private static final String PASSWORD_INVALID_ERROR = "Password length should be from 6 to 40 characters";
    private static final String REPEAT_PASSWORD_INVALID_ERROR = "Passwords do not match";

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
     * @return empty error code if valid
     */
    public static String validateLogin(String email, String password) {
        if (!validateEmail(email)) {
            return EMAIL_INVALID_ERROR;
        }
        if (!validatePassword(password)) {
            return PASSWORD_INVALID_ERROR;
        }
        return "";
    }

    /**
     * Validate registration form
     *
     * @param email
     * @param firstName
     * @param lastName
     * @param password
     * @param repeatPassword
     * @return empty error code if valid
     */
    public static String validateRegistration(String email, String firstName, String lastName, String password, String repeatPassword) {
        String loginResult = validateLogin(email, password);
        if (!loginResult.isEmpty()) {
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

        return "";
    }
}
