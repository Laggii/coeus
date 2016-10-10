package database.dao;

import database.dao.jdbc.UserDaoImpl;
import exception.ConnectionPoolException;
import model.User;
import org.junit.After;
import org.junit.Assert;
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
            assertTrue(userDao.create(testUser));
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRead() {
        try {
            userDao.create(testUser);
            long id = userDao.getId(testUser.getEmail());
            User user = userDao.read(id);
            assertEquals("test@example.com", user.getEmail());
            assertEquals("hash", user.getHash());
            assertEquals("Ivan", user.getFirstName());
            assertEquals("Ivanov", user.getLastName());
            assertEquals('m', user.getGender());
            assertEquals("1986-07-05", user.getBirthDate().toString());
            assertEquals("88001234567", user.getPhone());
            assertEquals(1, user.getRoleId());
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        try {
            userDao.create(testUser);
            testUser.setGender('f');
            testUser.setUserId(userDao.getId(testUser.getEmail()));
            assertTrue(userDao.update(testUser));

            User user = userDao.read(testUser.getUserId());
            assertEquals("test@example.com", user.getEmail());
            assertEquals("hash", user.getHash());
            assertEquals("Ivan", user.getFirstName());
            assertEquals("Ivanov", user.getLastName());

            assertEquals('f', user.getGender());

            assertEquals("1986-07-05", user.getBirthDate().toString());
            assertEquals("88001234567", user.getPhone());
            assertEquals(1, user.getRoleId());
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            userDao.create(testUser);
            assertTrue(userDao.delete(testUser));
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetId() {
        try {
            userDao.create(testUser);
            assertTrue(userDao.getId(testUser.getEmail()) != 0);
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAll() {
        try {
            Collection<User> users = userDao.getAll();
            assertTrue(users.size() >= 1);

            users.forEach(Assert::assertNotNull);
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadByEmail() {
        try {
            userDao.create(testUser);
            User user = userDao.read("test@example.com");

            assertEquals("test@example.com", user.getEmail());
            assertEquals("hash", user.getHash());
            assertEquals("Ivan", user.getFirstName());
            assertEquals("Ivanov", user.getLastName());
            assertEquals('m', user.getGender());
            assertEquals("1986-07-05", user.getBirthDate().toString());
            assertEquals("88001234567", user.getPhone());
            assertEquals(1, user.getRoleId());

        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIsExists() {
        try {
            userDao.create(testUser);
            assertTrue(userDao.isExists(testUser));
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}
