package database.dao.utils;

import model.Course;
import model.Message;
import model.Role;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alexeev on 21.10.2016.
 */

/**
 * DaoUtils provides functionality of building different beans from Resultsets
 */
public class DaoUtils {

    /**
     * Build user from ResultSet parameters
     *
     * @param resultSet
     * @return User
     * @throws SQLException
     */
    public static User buildUser(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .setUserId(resultSet.getLong("user_id"))
                .setEmail(resultSet.getString("email"))
                .setHash(resultSet.getString("hash"))
                .setFirstName(resultSet.getString("firstname"))
                .setLastName(resultSet.getString("lastname"))
                .setGender(resultSet.getString("gender").charAt(0))
                .setBirthDate(resultSet.getDate("birthdate"))
                .setPhone(resultSet.getString("phone"))
                .setRoleId(resultSet.getInt("role_id"))
                .setRegDate(resultSet.getTimestamp("regdate"))
                .build();
    }


    /**
     * Build course from ResultSet parameters
     *
     * @param resultSet
     * @return Course
     * @throws SQLException
     */
    public static Course buildCourse(ResultSet resultSet) throws SQLException {
        User owner = buildUser(resultSet);
        return new Course.Builder()
                .setCourseId(resultSet.getLong("course_id"))
                .setName(resultSet.getString("name"))
                .setOwner(owner)
                .setDescription(resultSet.getString("description"))
                .setDateCreated(resultSet.getTimestamp("date_created"))
                .build();
    }

    /**
     * Build role from ResultSet parameters
     *
     * @param resultSet
     * @return Role
     * @throws SQLException
     */
    public static Role buildRole(ResultSet resultSet) throws SQLException {
        return new Role.Builder()
                .setRoleId(resultSet.getLong("role_id"))
                .setName(resultSet.getString("name"))
                .setDescription(resultSet.getString("description"))
                .build();
    }

    /**
     * Build message from ResultSet parameters
     *
     * @param resultSet
     * @return Message
     * @throws SQLException
     */
    public static Message buildMessage(ResultSet resultSet) throws SQLException {
        return new Message.Builder()
                .setMessageId(resultSet.getLong("message_id"))
                .setIdFrom(resultSet.getLong("id_from"))
                .setIdTo(resultSet.getLong("id_to"))
                .setBody(resultSet.getString("body"))
                .setTime(resultSet.getTimestamp("time"))
                .build();
    }
}
