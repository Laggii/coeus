package database.dao.mysql;

import database.connectionpool.ConnectionPool;
import database.dao.interfaces.UserCoursesDao;
import exception.ConnectionPoolException;
import exception.DaoException;
import model.Course;
import model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static database.dao.utils.DaoUtils.buildCourse;
import static database.dao.utils.DaoUtils.buildUser;

/**
 * Created by Alexeev on 24.10.2016.
 */

/**
 * Mysql implementation of the UserCoursesDao
 */
public class UserCoursesDaoImpl implements UserCoursesDao {

    private ConnectionPool connectionPool;

    private Connection connection;

    private PreparedStatement statement;

    private ResultSet resultSet;

    private static final String JOIN_COURSE_QUERY = "INSERT INTO usercourses (course_id,user_id) VALUES (?,?)";

    private static final String LEFT_COURSE_QUERY = "DELETE FROM usercourses WHERE course_id = ? AND user_id = ?;";

    private static final String GET_USER_COURSES_QUERY =
            "SELECT * FROM courses " +
                    "INNER JOIN users on courses.owner_id = users.user_id " +
                    "INNER JOIN usercourses ON usercourses.course_id = courses.course_id " +
                    "WHERE usercourses.user_id = ?;";

    private static final String GET_ALL_USERS_QUERY =
            "SELECT * FROM usercourses JOIN users ON usercourses.user_id = users.user_id WHERE course_id = ?;";

    private static final Logger logger = Logger.getLogger(UserCoursesDaoImpl.class);

    public UserCoursesDaoImpl() throws DaoException {
        try {
            this.connectionPool = ConnectionPool.getInstance();
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean joinCourse(final User user, final Course course) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(JOIN_COURSE_QUERY);
            statement.setLong(1, course.getCourseId());
            statement.setLong(2, user.getUserId());
            statement.execute();

            logger.info("User successfully joined Course");
            return true;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public boolean leftCourse(final User user, final Course course) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(LEFT_COURSE_QUERY);
            statement.setLong(1, course.getCourseId());
            statement.setLong(2, user.getUserId());
            statement.execute();

            logger.info("User successfully left Course");
            return true;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public Collection<Course> getCourses(final User user) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(GET_USER_COURSES_QUERY);
            statement.setLong(1, user.getUserId());
            resultSet = statement.executeQuery();

            Set<Course> courses = new HashSet<>();
            while (resultSet.next()) {
                courses.add(buildCourse(resultSet));
            }
            logger.info("Successfully got all Courses for specified User");
            return courses;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public Collection<User> getUsers(final Course course) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(GET_ALL_USERS_QUERY);
            statement.setLong(1, course.getCourseId());
            resultSet = statement.executeQuery();

            Set<User> users = new HashSet<>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }

            logger.info("Successfully got all Users for specified Course");
            return users;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }
}
