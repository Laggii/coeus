package database.dao.mysql;

import database.connectionpool.ConnectionPool;
import database.dao.interfaces.GenericDao;
import exception.ConnectionPoolException;
import exception.DaoException;
import model.Role;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static database.dao.utils.DaoUtils.buildRole;

/**
 * Created by Alexeev on 03.10.2016.
 */

/**
 * Mysql implementation of the RoleDao
 */
public class RoleDaoImpl implements GenericDao<Role> {

    private ConnectionPool connectionPool;

    private Connection connection;

    private PreparedStatement statement;

    private ResultSet resultSet;

    private static final Logger logger = Logger.getLogger(RoleDaoImpl.class);

    private static final String CREATE_QUERY =
            "INSERT INTO roles (role_id,name,description) VALUES (?,?,?)";

    private static final String READ_QUERY =
            "SELECT * FROM roles WHERE role_id = ?";

    private static final String UPDATE_QUERY =
            "UPDATE roles SET role_id = ?,name = ?,description = ?  " +
                    "WHERE role_id = ?";

    private static final String DELETE_QUERY =
            "DELETE FROM roles WHERE role_id = ?";

    private static final String GET_ID_QUERY =
            "SELECT role_id FROM roles WHERE name = ?";

    private static final String GET_ALL_QUERY =
            "SELECT * FROM roles";

    public RoleDaoImpl() throws DaoException {
        try {
            this.connectionPool = ConnectionPool.getInstance();
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean create(final Role role) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(CREATE_QUERY);

            statement.setLong(1, role.getRoleId());
            statement.setString(2, role.getName());
            statement.setString(3, role.getDescription());
            statement.execute();

            logger.info("Successfully added new Role");
            return true;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public Role read(long id) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(READ_QUERY);

            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Role role = buildRole(resultSet);
                logger.info("Successfully read Role");
                return role;
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }


        return null;
    }

    @Override
    public boolean update(final Role role) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(UPDATE_QUERY);

            statement.setLong(1, role.getRoleId());
            statement.setString(2, role.getName());
            statement.setString(3, role.getDescription());
            statement.setLong(4, role.getRoleId());

            boolean result = statement.executeUpdate() > 0;
            logger.info("Successfully updated Role");
            return result;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public boolean delete(final Role role) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, role.getRoleId());

            boolean result = statement.executeUpdate() > 0;
            logger.info("Successfully deleted Role");
            return result;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement);
        }
    }

    @Override
    public long getId(final String name) throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(GET_ID_QUERY);

            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long result = resultSet.getLong(1);
                logger.info("Successfully got Role id");
                return result;
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return 0;
    }

    @Override
    public Collection<Role> getAll() throws DaoException {
        try {
            connection = connectionPool.takeConnection();
            Set<Role> roles = new HashSet<>();
            statement = connection.prepareStatement(GET_ALL_QUERY);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                roles.add(buildRole(resultSet));
            }
            logger.info("Successfully got all Roles");
            return roles;
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }
}
