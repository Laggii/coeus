package database.dao;

import database.dao.jdbc.MessageDaoImpl;
import exception.ConnectionPoolException;
import model.Message;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alexeev on 04.10.2016.
 */
public class TestMessageDaoImpl {
    MessageDaoImpl messageDao;

    Message testMessage;

    @Before
    public void init() {
        try {
            messageDao = new MessageDaoImpl();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }

        testMessage = new Message.Builder()
                .setIdFrom(1)
                .setIdTo(2)
                .setBody("Hello!")
                .setTime(Timestamp.valueOf("2016-10-04 12:47:10"))
                .build();
    }

    @After
    public void cleanUp() {
        try {
            messageDao.delete(testMessage);
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreate() {
        try {
            assertTrue(messageDao.create(testMessage));
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRead() {
        try {
            messageDao.create(testMessage);
            long id = messageDao.getId(testMessage);
            Message message = messageDao.read(id);

            assertEquals(1, message.getIdFrom());
            assertEquals(2, message.getIdTo());
            assertEquals("Hello!", message.getBody());
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        try {
            messageDao.create(testMessage);
            testMessage.setMessageId(messageDao.getId(testMessage));
            testMessage.setBody("Welcome!");
            assertTrue(messageDao.update(testMessage));

            Message message = messageDao.read(testMessage.getMessageId());
            assertEquals(1, message.getIdFrom());
            assertEquals(2, message.getIdTo());
            assertEquals("Welcome!", message.getBody());
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            messageDao.create(testMessage);
            assertTrue(messageDao.delete(testMessage));
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetId() {
        try {
            messageDao.create(testMessage);
            assertTrue(messageDao.getId(testMessage) != 0);
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAll() {
        try {
            Collection<Message> messages = messageDao.getAll();
            assertTrue(messages.size() >= 1);

            messages.forEach(Assert::assertNotNull);
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}
