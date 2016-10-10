package database.dao;

import database.dao.jdbc.RoleDaoImpl;
import exception.ConnectionPoolException;
import model.Role;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alexeev on 03.10.2016.
 */
public class TestRoleDaoImpl {
    RoleDaoImpl roleDao;

    Role testRole;

    @Before
    public void init() {
        try {
            roleDao = new RoleDaoImpl();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        testRole = new Role.Builder()
                .setRoleId(4)
                .setName("TestRole")
                .setDescription("Role for testing purposes")
                .build();
    }

    @After
    public void cleanUp() {
        try {
            roleDao.delete(testRole);
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreate() {
        try {
            assertTrue(roleDao.create(testRole));
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRead() {
        try {
            roleDao.create(testRole);
            Role role = roleDao.read(4);
            assertEquals(4, role.getRoleId());
            assertEquals("TestRole", role.getName());
            assertEquals("Role for testing purposes", role.getDescription());
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        try {
            roleDao.create(testRole);
            testRole.setDescription("Testing description");
            assertTrue(roleDao.update(testRole));

            Role role = roleDao.read(4);

            assertEquals(4, role.getRoleId());
            assertEquals("TestRole", role.getName());
            assertEquals("Testing description", role.getDescription());
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            roleDao.create(testRole);
            assertTrue(roleDao.delete(testRole));
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetId() {
        try {
            roleDao.create(testRole);
            assertEquals(4, roleDao.getId(testRole.getName()));
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAll() {
        try {
            Collection<Role> roles = roleDao.getAll();
            assertTrue(roles.size() >= 1);

            roles.forEach(Assert::assertNotNull);
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}
