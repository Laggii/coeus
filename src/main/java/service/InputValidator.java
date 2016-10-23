package service;

import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;

import static service.MessageProvider.*;

/**
 * Created by Alexeev on 05.10.2016.
 */

/**
 * InputValidator provides functionality for testing user input such as email, phone, name, etc
 * Returns error code reason if validation fails
 */
public class InputValidator {

    private static final EmailValidator emailValidator = EmailValidator.getInstance();
    private static final DateValidator dateValidator = DateValidator.getInstance();

    private static final String NAME_REGEX = "^\\pL*";
    private static final String ID_REGEX = "^[0-9]+$";
    private static final String PHONE_REGEX = "\\+\\d\\s\\(\\d{3}\\)\\s\\d{3}-\\d{2}-\\d{2}";

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
        return name != null && !name.isEmpty() && name.length() <= 35 && name.matches(NAME_REGEX);
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
     * Validate id
     *
     * @param id
     * @return true if id is valid
     */
    public static boolean validateId(String id) {
        return id != null && !id.isEmpty() && id.matches(ID_REGEX);
    }

    /**
     * Validate user sex
     *
     * @param sex
     * @return true if sex is valid
     */
    public static boolean validateSex(String sex) {
        return (sex != null && !sex.isEmpty() && sex.length() == 1) && (sex.equals("m") || sex.equals("f"));
    }

    /**
     * Validate user phone
     *
     * @param phone
     * @return true if phone is valid
     */
    public static boolean validatePhone(String phone) {
        return phone != null && !phone.isEmpty() && phone.length() == 18 && phone.matches(PHONE_REGEX);
    }

    /**
     * Validate user birth date using Apache Common DateValidator
     *
     * @param birthDate
     * @return true if birth date is valid
     */
    public static boolean validateBirthDate(String birthDate) {
        return dateValidator.isValid(birthDate);
    }

    /**
     * Validate login form
     *
     * @param email
     * @param password
     * @return valid information code if valid
     */
    public static MessageProvider validateLogin(String email, String password) {
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
     * @return valid information code if valid
     */
    public static MessageProvider validateRegistration(String email, String firstName, String lastName, String password, String repeatPassword) {
        MessageProvider loginResult = validateLogin(email, password);
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

    /**
     * Validate change password form
     *
     * @param oldPassword
     * @param newPassword
     * @param repeatPassword
     * @return valid information code if valid
     */
    public static MessageProvider validatePasswordChange(String oldPassword, String newPassword, String repeatPassword) {
        if (!validatePassword(oldPassword) || !validatePassword(newPassword) || !validatePassword(repeatPassword)) {
            return PASSWORD_INVALID_ERROR;
        }

        if (!repeatPassword.equals(newPassword)) {
            return REPEAT_PASSWORD_INVALID_ERROR;
        }

        return VALID;
    }

    /**
     * Validate profile settings form
     *
     * @param email
     * @param firstName
     * @param lastName
     * @param sex
     * @param phone
     * @param birthDate
     * @return valid information code if valid
     */
    public static MessageProvider validateProfileChange(String email, String firstName, String lastName, String sex, String phone, String birthDate) {
        if (!validateEmail(email)) {
            return EMAIL_INVALID_ERROR;
        }

        if (!validateName(firstName)) {
            return FIRSTNAME_INVALID_ERROR;
        }

        if (!validateName(lastName)) {
            return LASTNAME_INVALID_ERROR;
        }

        if (!validateSex(sex)) {
            return SEX_INVALID_ERROR;
        }

        if (!validatePhone(phone)) {
            return PHONE_INVALID_ERROR;
        }

        if (!validateBirthDate(birthDate)) {
            return BIRTHDATE_INVALID_ERROR;
        }

        return VALID;
    }
}
