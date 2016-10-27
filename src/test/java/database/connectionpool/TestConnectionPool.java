package database.connectionpool;

import exception.ConnectionPoolException;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Alexeev on 27.09.2016.
 */

public class TestConnectionPool {
    private ConnectionPool connectionPool;

    private List<Connection> connections;

    @Before
    public void init() {
        try {
            connectionPool = ConnectionPool.getInstance();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        connections = new ArrayList<>();
    }

    @Test
    public void testTakeConnection() {
        try {
            Connection connection = connectionPool.takeConnection();

            assertNotNull(connection);

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            assertEquals(1, resultSet.getLong(1));

            connectionPool.closeConnection(connection, statement, resultSet);
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueuesSize() {
        assertEquals(10, connectionPool.getConnectionQueueSize());

        for (int i = 0; i < 10; i++) {
            try {
                connections.add(connectionPool.takeConnection());
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
        }

        assertEquals(0, connectionPool.getConnectionQueueSize());
        assertEquals(10, connectionPool.getGivenAwayConQueueSize());
        assertEquals(10, connections.size());


        connections.forEach(c -> {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        assertEquals(10, connectionPool.getConnectionQueueSize());
        assertEquals(0, connectionPool.getGivenAwayConQueueSize());
    }
}

