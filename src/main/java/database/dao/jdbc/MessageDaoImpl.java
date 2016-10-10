package database.dao.jdbc;

import database.connectionpool.ConnectionPool;
import database.dao.interfaces.GenericDao;
import exception.ConnectionPoolException;
import model.Message;
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
//TODO: getting id using body is bad idea
public class MessageDaoImpl implements GenericDao<Message> {

    private ConnectionPool connectionPool;

    private Connection connection;

    private PreparedStatement statement;

    private static final Logger logger = Logger.getLogger(MessageDaoImpl.class);

    private static final String CREATE_QUERY =
            "INSERT INTO messages (id_from,id_to,body) VALUES (?,?,?)";

    private static final String READ_QUERY =
            "SELECT * FROM messages WHERE message_id = ?";

    private static final String UPDATE_QUERY =
            "UPDATE messages SET id_from = ?,id_to = ?,body = ?  " +
                    "WHERE message_id = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM messages WHERE message_id = ?";

    private static final String GET_ID_QUERY =
            "SELECT message_id FROM messages WHERE body = ?";

    private static final String GET_ALL_QUERY =
            "SELECT * FROM messages";

    public MessageDaoImpl() throws ConnectionPoolException {
        this.connectionPool = ConnectionPool.getInstance();
    }


    @Override
    public boolean create(final Message message) throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(CREATE_QUERY);

        statement.setLong(1, message.getIdFrom());
        statement.setLong(2, message.getIdTo());
        statement.setString(3, message.getBody());
        statement.execute();

        connectionPool.closeConnection(connection, statement);

        logger.info("Successfully added new Message");
        return true;
    }

    @Override
    public Message read(long id) throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(READ_QUERY);

        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Message message = buildMessage(resultSet);
            connectionPool.closeConnection(connection, statement, resultSet);
            logger.info("Successfully read Message");
            return message;
        }
        connectionPool.closeConnection(connection, statement, resultSet);
        return null;
    }

    @Override
    public boolean update(final Message message) throws SQLException, ConnectionPoolException {
        long messageId = message.getMessageId();
        if (messageId == 0) {
            messageId = getId(message.getBody());
        }

        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(UPDATE_QUERY);

        statement.setLong(1, message.getIdFrom());
        statement.setLong(2, message.getIdTo());
        statement.setString(3, message.getBody());
        statement.setLong(4, messageId);

        boolean result = statement.executeUpdate() > 0;
        connectionPool.closeConnection(connection, statement);
        logger.info("Successfully updated Message");
        return result;
    }

    @Override
    public boolean delete(final Message message) throws SQLException, ConnectionPoolException {
        long messageId = message.getMessageId();
        if (messageId == 0) {
            messageId = getId(message.getBody());
        }

        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(DELETE_QUERY);
        statement.setLong(1, messageId);

        boolean result = statement.executeUpdate() > 0;
        connectionPool.closeConnection(connection, statement);
        logger.info("Successfully deleted Message");
        return result;
    }

    @Override
    public long getId(final String body) throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(GET_ID_QUERY);

        statement.setString(1, body);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            long result = resultSet.getLong(1);
            connectionPool.closeConnection(connection, statement, resultSet);
            logger.info("Successfully got Message id");
            return result;
        }
        connectionPool.closeConnection(connection, statement, resultSet);
        return 0;
    }

    @Override
    public Collection<Message> getAll() throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        Set<Message> messages = new HashSet<>();
        statement = connection.prepareStatement(GET_ALL_QUERY);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            messages.add(buildMessage(resultSet));
        }
        connectionPool.closeConnection(connection, statement);
        logger.info("Successfully got all Messages");
        return messages;
    }

    /**
     * Build message from ResultSet parameters
     *
     * @param resultSet
     * @return Message
     * @throws SQLException
     */
    private Message buildMessage(ResultSet resultSet) throws SQLException {
        return new Message.Builder()
                .setMessageId(resultSet.getLong("message_id"))
                .setIdFrom(resultSet.getLong("id_from"))
                .setIdTo(resultSet.getLong("id_to"))
                .setBody(resultSet.getString("body"))
                .setTime(resultSet.getTimestamp("time"))
                .build();
    }
}
