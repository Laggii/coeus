package database.dao.interfaces;

import exception.DaoException;
import model.Course;
import model.User;

import java.util.Collection;

/**
 * Created by Alexeev on 24.10.2016.
 */

/**
 * UserCoursesDao interface with basic operations: join, left, get Courses
 */
public interface UserCoursesDao {

    /**
     * Join specified Course
     *
     * @param user
     * @param course
     * @return true if operation successful
     * @throws DaoException
     */
    boolean joinCourse(final User user, final Course course) throws DaoException;

    /**
     * Leave specified Course
     *
     * @param user
     * @param course
     * @return true if operation successful
     * @throws DaoException
     */
    boolean leftCourse(final User user, final Course course) throws DaoException;

    /**
     * Get all Courses for specified User
     *
     * @param user
     * @return Collection of Courses
     * @throws DaoException
     */
    Collection<Course> getCourses(final User user) throws DaoException;

    /**
     * Get all Users who joined specified Course
     *
     * @param course
     * @return Collection of Users
     * @throws DaoException
     */
    Collection<User> getUsers(final Course course) throws DaoException;
}
