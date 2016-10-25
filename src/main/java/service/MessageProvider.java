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
    COURSE_NAME_INVALID_ERROR("validator.error.course_name_invalid"),
    COURSE_DESC_INVALID_ERROR("validator.error.course_desc_invalid"),
    USER_INCORRECT_ERROR("servlet.error.user_incorrect"),
    USER_EXISTS_ERROR("servlet.error.user_exists"),
    DATABASE_ERROR("servlet.error.database"),
    UNKNOWN_COMMAND("servlet.error.command"),
    STATUS_403_ERROR("servlet.error.403"),
    STATUS_404_ERROR("servlet.error.404"),
    STATUS_500_ERROR("servlet.error.500"),
    STATUS_UNKNOWN_ERROR("servlet.error.default"),
    INSUFFICIENT_RIGHTS_ERROR("command.error.insufficient.rights"),
    USER_NOT_FOUND_ERROR("command.error.user_not_found"),
    USER_ID_ERROR("command.error.user_id_incorrect"),
    COURSE_NOT_FOUND_ERROR("command.error.course_not_found"),
    COURSE_ID_ERROR("command.error.course_id_incorrect"),
    COURSE_NOT_JOINED_ERROR("command.error.course_not_joined"),
    COURSE_ALREADY_JOINED_ERROR("command.error.course_already_joined"),
    USER_NOT_FRIEND_ERROR("command.error.user_not_friend_error"),
    USER_IS_FRIEND_ERROR("command.error.user_is_friend_error"),
    PASSWORD_CHANGE_SUCCESSFUL("command.info.password_change_success"),
    PROFILE_CHANGE_SUCCESSFUL("command.info.profile_change_success"),
    USER_ADD_FRIEND_SUCCESS("command.info.user_friend_success"),
    USER_DEL_FRIEND_SUCCESS("command.info.user_delfriend_success"),
    COURSE_JOIN_SUCCESS("command.info.course_join_success"),
    COURSE_LEFT_SUCCESS("command.info.course_left_success"),
    COURSE_DELETED_SUCCESS("command.info.course_deleted_success"),
    COURSE_CREATED_SUCCESS("command.info.course_created_success"),
    COURSE_UPDATED_SUCCESS("command.info.course_updated_success"),
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
