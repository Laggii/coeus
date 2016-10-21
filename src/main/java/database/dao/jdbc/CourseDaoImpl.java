package database.dao.jdbc;

import database.connectionpool.ConnectionPool;
import database.dao.interfaces.GenericDao;
import exception.ConnectionPoolException;
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

/**
 * Created by Alexeev on 04.10.2016.
 */
public class CourseDaoImpl implements GenericDao<Course> {

    private ConnectionPool connectionPool;

    private Connection connection;

    private PreparedStatement statement;

    private UserDaoImpl userDao;

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

    public CourseDaoImpl() throws ConnectionPoolException {
        this.connectionPool = ConnectionPool.getInstance();
    }

    @Override
    public boolean create(final Course course) throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(CREATE_QUERY);

        statement.setString(1, course.getName());
        statement.setLong(2, course.getOwner().getUserId());
        statement.setString(3, course.getDescription());
        statement.execute();

        connectionPool.closeConnection(connection, statement);

        logger.info("Successfully added new Course");
        return true;
    }

    @Override
    public Course read(long id) throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(READ_QUERY);

        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Course course = buildCourse(resultSet);
            connectionPool.closeConnection(connection, statement, resultSet);
            logger.info("Successfully read Course");
            return course;
        }
        connectionPool.closeConnection(connection, statement, resultSet);
        return null;
    }

    @Override
    public boolean update(final Course course) throws SQLException, ConnectionPoolException {
        long courseId = course.getCourseId();
        if (courseId == 0) {
            courseId = getId(course.getName());
        }

        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(UPDATE_QUERY);

        statement.setString(1, course.getName());
        statement.setLong(2, course.getOwner().getUserId());
        statement.setString(3, course.getDescription());
        statement.setLong(4, courseId);

        boolean result = statement.executeUpdate() > 0;
        connectionPool.closeConnection(connection, statement);
        logger.info("Successfully updated Course");
        return result;
    }

    @Override
    public boolean delete(final Course course) throws SQLException, ConnectionPoolException {
        long courseId = course.getCourseId();
        if (courseId == 0) {
            courseId = getId(course.getName());
        }

        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(DELETE_QUERY);
        statement.setLong(1, courseId);

        boolean result = statement.executeUpdate() > 0;
        connectionPool.closeConnection(connection, statement);
        logger.info("Successfully deleted Course");
        return result;
    }

    @Override
    public long getId(final String name) throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(GET_ID_QUERY);

        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            long result = resultSet.getLong(1);
            connectionPool.closeConnection(connection, statement, resultSet);
            logger.info("Successfully got Course id");
            return result;
        }
        connectionPool.closeConnection(connection, statement, resultSet);
        return 0;
    }

    @Override
    public Collection<Course> getAll() throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        Set<Course> courses = new HashSet<>();
        statement = connection.prepareStatement(GET_ALL_QUERY);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            courses.add(buildCourse(resultSet));
        }
        connectionPool.closeConnection(connection, statement);
        logger.info("Successfully got all Courses");
        return courses;
    }

    /**
     * Build course from ResultSet parameters
     *
     * @param resultSet
     * @return Course
     * @throws SQLException
     */
    public Course buildCourse(ResultSet resultSet) throws SQLException, ConnectionPoolException {
        userDao = new UserDaoImpl();
        User owner = userDao.buildUser(resultSet);
        return new Course.Builder()
                .setCourseId(resultSet.getLong("course_id"))
                .setName(resultSet.getString("name"))
                .setOwner(owner)
                .setDescription(resultSet.getString("description"))
                .setDateCreated(resultSet.getTimestamp("date_created"))
                .build();
    }
}
