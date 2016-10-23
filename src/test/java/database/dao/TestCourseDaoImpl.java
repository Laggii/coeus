package database.dao;

import database.dao.mysql.CourseDaoImpl;
import database.dao.mysql.UserDaoImpl;
import exception.DaoException;
import model.Course;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alexeev on 04.10.2016.
 */
public class TestCourseDaoImpl {
    private CourseDaoImpl courseDao;

    private UserDaoImpl userDao;

    private Course testCourse;

    private User owner;

    @Before
    public void init() {
        try {
            courseDao = new CourseDaoImpl();
            userDao = new UserDaoImpl();

            //I don't want to repeat process of creating new User in database, so I will get one from DB
            owner = userDao.read(3);
        } catch (DaoException e) {
            e.printStackTrace();
        }


        testCourse = new Course.Builder()
                .setName("Test Course")
                .setOwner(owner)
                .setDescription("Basic course for testing purposes")
                .build();
    }

    @After
    public void cleanUp() {
        try {
            courseDao.delete(testCourse);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreate() {
        try {
            assertTrue(courseDao.create(testCourse));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRead() {
        try {
            courseDao.create(testCourse);
            long id = courseDao.getId(testCourse.getName());
            Course course = courseDao.read(id);

            assertEquals("Test Course", course.getName());
            assertEquals(3, course.getOwner().getUserId());
            assertEquals("Basic course for testing purposes", course.getDescription());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        try {
            courseDao.create(testCourse);
            testCourse.setCourseId(courseDao.getId(testCourse.getName()));
            testCourse.setDescription("New Description");
            assertTrue(courseDao.update(testCourse));

            Course course = courseDao.read(testCourse.getCourseId());
            assertEquals("Test Course", course.getName());
            assertEquals(3, course.getOwner().getUserId());
            assertEquals("New Description", course.getDescription());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            courseDao.create(testCourse);
            assertTrue(courseDao.delete(testCourse));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetId() {
        try {
            courseDao.create(testCourse);
            assertTrue(courseDao.getId(testCourse.getName()) != 0);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAll() {
        try {
            Collection<Course> courses = courseDao.getAll();
            assertTrue(courses.size() >= 1);

            courses.forEach(Assert::assertNotNull);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
