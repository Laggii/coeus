package database.dao.mysql;

import database.connectionpool.ConnectionPool;
import database.dao.interfaces.UserFriendsDao;
import exception.ConnectionPoolException;
import exception.DaoException;
import model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static database.dao.utils.DaoUtils.buildUser;

/**
 * Created by Alexeev on 24.10.2016.
 */
public class UserFriendsDaoImpl implements UserFriendsDao {

    private ConnectionPool connectionPool;

    private Connection connection;

    private PreparedStatement statement;

    private ResultSet resultSet;

    private static final Logger logger = Logger.getLogger(UserFriendsDaoImpl.class);

    private static final String ADD_FRIEND = "INSERT INTO userfriends (user_id,friend_id) VALUES (?,?)";

    private static final String GET_USER_FRIENDS = "SELECT * FROM users " +
            "INNER JOIN userfriends on userfriends.friend_id = users.user_id " +
            "WHERE userfriends.user_id = ?";

    private static final String DELETE_FRIEND = "DELETE FROM userfriends WHERE user_id = ? AND friend_id = ?;";

    public UserFriendsDaoImpl() throws DaoException {
        try {
            this.connectionPool = ConnectionPool.getInstance();
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean addFriend(User user, User friend) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(ADD_FRIEND);
            statement.setLong(1, user.getUserId());
            statement.setLong(2, friend.getUserId());
            statement.execute();

            logger.info("Successfully added new Friend");
            return true;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public boolean delFriend(User user, User friend) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(DELETE_FRIEND);
            statement.setLong(1, user.getUserId());
            statement.setLong(2, friend.getUserId());
            statement.execute();

            logger.info("Successfully deleted Friend");
            return true;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public Collection<User> getFriends(final User user) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            Set<User> users = new HashSet<>();
            statement = connection.prepareStatement(GET_USER_FRIENDS);
            statement.setLong(1, user.getUserId());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            logger.info("Successfully got all Friends for specified User");
            return users;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }
}
