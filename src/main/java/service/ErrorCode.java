package service;

/**
 * Created by Alexeev on 13.10.2016.
 */

/**
 * ErrorCode maps constants to ResourceBundle parameters with current language
 */
public enum ErrorCode {
    EMAIL_INVALID_ERROR("error.validator.email_invalid"),
    FIRSTNAME_INVALID_ERROR("error.validator.firstname_invalid"),
    LASTNAME_INVALID_ERROR("error.validator.lastname_invalid"),
    PASSWORD_INVALID_ERROR("error.validator.password_invalid"),
    REPEAT_PASSWORD_INVALID_ERROR("error.validator.repeat_password_invalid"),
    USER_INCORRECT_ERROR("error.servlet.user_incorrect"),
    USER_EXISTS_ERROR("error.servlet.user_exists"),
    DATABASE_ERROR("error.servlet.database"),
    STATUS_403_ERROR("error.servlet.403"),
    STATUS_404_ERROR("error.servlet.404"),
    STATUS_500_ERROR("error.servlet.500"),
    STATUS_UNKNOWN_ERROR("error.servlet.default"),
    USER_NOT_FOUND_ERROR("error.command.user_not_found"),
    USER_ID_ERROR("error.command.user_id_incorrect"),
    VALID("error.validator.valid");

    private LocaleManager localeManager = LocaleManager.getInstance();

    private final String propertyKey;

    ErrorCode(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    @Override
    public String toString() {
        return localeManager.getValue(propertyKey);
    }
}
