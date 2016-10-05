package database.dao;

import database.dao.jdbc.CourseDaoImpl;
import exception.ConnectionPoolException;
import model.Course;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alexeev on 04.10.2016.
 */
public class TestCourseDaoImpl {
    CourseDaoImpl courseDao;

    Course testCourse;

    @Before
    public void init() {
        try {
            courseDao = new CourseDaoImpl();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        testCourse = new Course.Builder()
                .setName("Test Course")
                .setOwnerId(2)
                .setDescription("Basic course for testing purposes")
                .build();
    }

    @After
    public void cleanUp() {
        try {
            courseDao.delete(testCourse);
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreate() {
        try {
            assertTrue(courseDao.create(testCourse));
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRead() {
        try {
            courseDao.create(testCourse);
            long id = courseDao.getId(testCourse);
            Course course = courseDao.read(id);

            assertEquals("Test Course", course.getName());
            assertEquals(2, course.getOwnerId());
            assertEquals("Basic course for testing purposes", course.getDescription());
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        try {
            courseDao.create(testCourse);
            testCourse.setCourseId(courseDao.getId(testCourse));
            testCourse.setDescription("New Description");
            assertTrue(courseDao.update(testCourse));

            Course course = courseDao.read(testCourse.getCourseId());
            assertEquals("Test Course", course.getName());
            assertEquals(2, course.getOwnerId());
            assertEquals("New Description", course.getDescription());
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            courseDao.create(testCourse);
            assertTrue(courseDao.delete(testCourse));
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetId() {
        try {
            courseDao.create(testCourse);
            assertTrue(courseDao.getId(testCourse) != 0);
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAll() {
        try {
            Collection<Course> courses = courseDao.getAll();
            assertTrue(courses.size() >= 1);

            courses.forEach(Assert::assertNotNull);
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}
