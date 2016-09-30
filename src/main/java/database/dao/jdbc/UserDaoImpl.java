package database.dao.jdbc;

import database.connectionpool.ConnectionPool;
import database.dao.interfaces.UserDao;
import exception.ConnectionPoolException;
import model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Stream;

/**
 * Created by Alexeev on 30.09.2016.
 */
public class UserDaoImpl implements UserDao {

    private ConnectionPool connectionPool;

    private Connection connection;

    private static final Logger logger = Logger.getLogger(UserDao.class);

    private static final String CREATE_QUERY =
            "INSERT INTO users (email,hash,firstname,lastname,gender,birthdate,phone,role_id) " +
            "VALUES (?,?,?,?,?,?,?,?,?)";

    private static final String DELETE_QUERY =
            "";

    public UserDaoImpl() throws ConnectionPoolException {
        this.connectionPool = ConnectionPool.getInstance();
        this.connection = connectionPool.takeConnection();
    }

    @Override
    public boolean create(final User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);
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

        logger.info("Successfully added new user");
        return true;
    }

    @Override
    public User read(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(final User user) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(final User user) throws SQLException {
        return false;
    }

    @Override
    public Stream<User> getAll() throws SQLException {
        return null;
    }
}
