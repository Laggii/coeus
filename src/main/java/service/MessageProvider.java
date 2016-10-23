package service;

/**
 * Created by Alexeev on 13.10.2016.
 */

/**
 * MessageProvider provides error and information messages with current language from LocaleManager
 */
public enum MessageProvider {
    EMAIL_INVALID_ERROR("validator.error.email_invalid"),
    FIRSTNAME_INVALID_ERROR("validator.error.firstname_invalid"),
    LASTNAME_INVALID_ERROR("validator.error.lastname_invalid"),
    PASSWORD_INVALID_ERROR("validator.error.password_invalid"),
    REPEAT_PASSWORD_INVALID_ERROR("validator.error.repeat_password_invalid"),
    OLD_PASSWORD_INVALID_ERROR("command.error.old_password_incorrect"),
    SEX_INVALID_ERROR("validator.error.sex_invalid"),
    PHONE_INVALID_ERROR("validator.error.phone_invalid"),
    BIRTHDATE_INVALID_ERROR("validator.error.birthdate_invalid"),
    USER_INCORRECT_ERROR("servlet.error.user_incorrect"),
    USER_EXISTS_ERROR("servlet.error.user_exists"),
    DATABASE_ERROR("servlet.error.database"),
    UNKNOWN_COMMAND("servlet.error.command"),
    STATUS_403_ERROR("servlet.error.403"),
    STATUS_404_ERROR("servlet.error.404"),
    STATUS_500_ERROR("servlet.error.500"),
    STATUS_UNKNOWN_ERROR("servlet.error.default"),
    USER_NOT_FOUND_ERROR("command.error.user_not_found"),
    USER_ID_ERROR("command.error.user_id_incorrect"),
    PASSWORD_CHANGE_SUCCESSFUL("command.info.password_change_success"),
    PROFILE_CHANGE_SUCCESSFUL("command.info.profile_change_success"),
    VALID("validator.error.valid");

    private LocaleManager localeManager = LocaleManager.getInstance();

    private final String propertyKey;

    MessageProvider(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    @Override
    public String toString() {
        return localeManager.getValue(propertyKey);
    }
}
