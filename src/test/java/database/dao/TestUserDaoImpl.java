package database.dao;

import database.dao.jdbc.UserDaoImpl;
import exception.ConnectionPoolException;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alexeev on 03.10.2016.
 */
public class TestUserDaoImpl {
    UserDaoImpl userDao;

    User testUser;

    @Before
    public void init() {
        try {
            userDao = new UserDaoImpl();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        testUser = new User.Builder()
                .setEmail("test@example.com")
                .setHash("hash")
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setGender('m')
                .setBirthDate(Date.valueOf("1986-07-05"))
                .setPhone("88001234567")
                .setRoleId(1)
                .build();
    }

    @After
    public void cleanUp() {
        try {
            userDao.delete(testUser);
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreate() {
        try {
            assertEquals(true, userDao.create(testUser));
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRead() {
        try {
            userDao.create(testUser);
            long id = userDao.getId(testUser);
            User user = userDao.read(id);
            assertEquals("Ivan", user.getFirstName());
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        try {
            userDao.create(testUser);
            testUser.setGender('f');
            testUser.setUserId(userDao.getId(testUser));
            assertEquals(true, userDao.update(testUser));

            User user = userDao.read(testUser.getUserId());
            assertEquals('f', user.getGender());
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            userDao.create(testUser);
            assertEquals(true, userDao.delete(testUser));
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAll() {
        try {
            Collection<User> users = userDao.getAll();
            assertTrue(users.size() >= 1);
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}
