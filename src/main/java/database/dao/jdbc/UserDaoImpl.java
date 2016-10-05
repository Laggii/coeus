package database.dao.jdbc;

import database.connectionpool.ConnectionPool;
import database.dao.interfaces.GenericDao;
import exception.ConnectionPoolException;
import model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexeev on 30.09.2016.
 */
public class UserDaoImpl implements GenericDao<User> {

    private ConnectionPool connectionPool;

    private Connection connection;

    private PreparedStatement statement;

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    private static final String CREATE_QUERY =
            "INSERT INTO users (email,hash,firstname,lastname,gender,birthdate,phone,role_id) " +
                    "VALUES (?,?,?,?,?,?,?,?)";

    private static final String READ_QUERY =
            "SELECT * FROM users WHERE user_id = ?";

    private static final String UPDATE_QUERY =
            "UPDATE users SET email = ?,hash = ?,gender = ?,birthdate = ?,phone = ?,role_id = ? " +
                    "WHERE user_id = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM users WHERE user_id = ?";

    private static final String GET_ID_QUERY =
            "SELECT user_id FROM users WHERE email = ?";

    private static final String GET_ALL_QUERY =
            "SELECT * FROM users";

    public UserDaoImpl() throws ConnectionPoolException {
        this.connectionPool = ConnectionPool.getInstance();
    }

    @Override
    public boolean create(final User user) throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(CREATE_QUERY);

        statement.setString(1, user.getEmail());
        statement.setString(2, user.getHash());
        statement.setString(3, user.getFirstName());
        statement.setString(4, user.getLastName());
        statement.setString(5, String.valueOf(user.getGender()));
        statement.setDate(6, user.getBirthDate());
        statement.setString(7, user.getPhone());
        statement.setInt(8, user.getRoleId());
        statement.execute();

        connectionPool.closeConnection(connection, statement);

        logger.info("Successfully added new User");
        return true;
    }

    @Override
    public User read(long id) throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(READ_QUERY);

        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            User user = buildUser(resultSet);
            connectionPool.closeConnection(connection, statement, resultSet);
            logger.info("Successfully read User");
            return user;
        }
        connectionPool.closeConnection(connection, statement, resultSet);
        return null;
    }

    @Override
    public boolean update(final User user) throws SQLException, ConnectionPoolException {
        long userId = user.getUserId();
        if (userId == 0) {
            userId = getId(user);
        }

        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(UPDATE_QUERY);

        statement.setString(1, user.getEmail());
        statement.setString(2, user.getHash());
        statement.setString(3, String.valueOf(user.getGender()));
        statement.setDate(4, user.getBirthDate());
        statement.setString(5, user.getPhone());
        statement.setInt(6, user.getRoleId());
        statement.setLong(7, userId);

        boolean result = statement.executeUpdate() > 0;
        connectionPool.closeConnection(connection, statement);
        logger.info("Successfully updated User");
        return result;
    }

    @Override
    public boolean delete(final User user) throws SQLException, ConnectionPoolException {
        long userId = user.getUserId();
        if (userId == 0) {
            userId = getId(user);
        }

        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(DELETE_QUERY);
        statement.setLong(1, userId);

        boolean result = statement.executeUpdate() > 0;
        connectionPool.closeConnection(connection, statement);
        logger.info("Successfully deleted User");
        return result;
    }

    @Override
    public long getId(final User user) throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(GET_ID_QUERY);

        statement.setString(1, user.getEmail());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            long result = resultSet.getLong(1);
            connectionPool.closeConnection(connection, statement, resultSet);
            logger.info("Successfully got User id");
            return result;
        }
        connectionPool.closeConnection(connection, statement, resultSet);
        return 0;
    }

    @Override
    public Collection<User> getAll() throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        Set<User> users = new HashSet<>();
        statement = connection.prepareStatement(GET_ALL_QUERY);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            users.add(buildUser(resultSet));
        }
        connectionPool.closeConnection(connection, statement);
        logger.info("Successfully got all Users");
        return users;
    }

    /**
     * Build user from ResultSet parameters
     *
     * @param resultSet
     * @return User
     * @throws SQLException
     */
    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User.Builder()
                .setUserId(resultSet.getLong("user_id"))
                .setEmail(resultSet.getString("email"))
                .setHash(resultSet.getString("hash"))
                .setFirstName(resultSet.getString("firstname"))
                .setLastName(resultSet.getString("lastname"))
                .setGender(resultSet.getString("gender").charAt(0))
                .setBirthDate(resultSet.getDate("birthdate"))
                .setPhone(resultSet.getString("phone"))
                .setRoleId(resultSet.getInt("role_id"))
                .setRegDate(resultSet.getTimestamp("regdate"))
                .build();
    }
}
