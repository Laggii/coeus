package database.dao.mysql;

import database.connectionpool.ConnectionPool;
import database.dao.interfaces.GenericDao;
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
 * Created by Alexeev on 04.10.2016.
 */
public class CourseDaoImpl implements GenericDao<Course> {

    private ConnectionPool connectionPool;

    private Connection connection;

    private PreparedStatement statement;

    private ResultSet resultSet;

    private static final Logger logger = Logger.getLogger(CourseDaoImpl.class);

    private static final String CREATE_QUERY =
            "INSERT INTO courses (name,owner_id,description) VALUES (?,?,?)";

    private static final String READ_QUERY =
            "SELECT * FROM courses JOIN users ON courses.owner_id = users.user_id WHERE course_id = ?";

    private static final String UPDATE_QUERY =
            "UPDATE courses SET name = ?,owner_id = ?,description = ?  " +
                    "WHERE course_id = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM courses WHERE course_id = ?";

    private static final String GET_ID_QUERY =
            "SELECT course_id FROM courses WHERE name = ?";

    private static final String GET_ALL_QUERY =
            "SELECT * FROM courses JOIN users ON courses.owner_id = users.user_id;";

    private static final String GET_ALL_USERS_QUERY =
            "SELECT * FROM usercourses JOIN users ON usercourses.user_id = users.user_id WHERE course_id = ?;";

    public CourseDaoImpl() throws DaoException {
        try {
            this.connectionPool = ConnectionPool.getInstance();
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean create(final Course course) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(CREATE_QUERY);

            statement.setString(1, course.getName());
            statement.setLong(2, course.getOwner().getUserId());
            statement.setString(3, course.getDescription());
            statement.execute();
            logger.info("Successfully added new Course");
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public Course read(long id) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(READ_QUERY);

            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Course course = buildCourse(resultSet);
                logger.info("Successfully read Course");
                return course;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public boolean update(final Course course) throws DaoException {
        long courseId = course.getCourseId();
        if (courseId == 0) {
            courseId = getId(course.getName());
        }

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(UPDATE_QUERY);

            statement.setString(1, course.getName());
            statement.setLong(2, course.getOwner().getUserId());
            statement.setString(3, course.getDescription());
            statement.setLong(4, courseId);

            boolean result = statement.executeUpdate() > 0;
            logger.info("Successfully updated Course");
            return result;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }

    }

    @Override
    public boolean delete(final Course course) throws DaoException {
        long courseId = course.getCourseId();
        if (courseId == 0) {
            courseId = getId(course.getName());
        }

        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, courseId);

            boolean result = statement.executeUpdate() > 0;
            logger.info("Successfully deleted Course");
            return result;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public long getId(final String name) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(GET_ID_QUERY);

            statement.setString(1, name);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                long result = resultSet.getLong(1);
                logger.info("Successfully got Course id");
                return result;
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return 0;
    }

    @Override
    public Collection<Course> getAll() throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            Set<Course> courses = new HashSet<>();
            statement = connection.prepareStatement(GET_ALL_QUERY);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                courses.add(buildCourse(resultSet));
            }

            logger.info("Successfully got all Courses");
            return courses;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    //TODO: javadoc, test
    public Collection<User> getUsers(final Course course) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            Set<User> users = new HashSet<>();
            statement = connection.prepareStatement(GET_ALL_USERS_QUERY);
            statement.setLong(1, course.getCourseId());
            resultSet = statement.executeQuery();

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
