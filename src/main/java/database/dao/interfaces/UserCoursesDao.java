package database.dao.interfaces;

import exception.DaoException;
import model.Course;
import model.User;

import java.util.Collection;

/**
 * Created by Alexeev on 24.10.2016.
 */

/**
 * UserCoursesDao interface with basic operations join, left, get all courses
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
     * Left specified Course
     *
     * @param user
     * @param course
     * @return true if operation successful
     * @throws DaoException
     */
    boolean leftCourse(final User user, final Course course) throws DaoException;

    /**
     * Get all user Courses
     *
     * @param user
     * @return Collection of Courses
     * @throws DaoException
     */
    Collection<Course> getCourses(final User user) throws DaoException;

    /**
     * Get all users who joined specified course
     *
     * @param course
     * @return Collection of Users
     * @throws DaoException
     */
    Collection<User> getUsers(final Course course) throws DaoException;
}
