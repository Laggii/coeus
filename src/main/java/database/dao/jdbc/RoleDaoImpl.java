package database.dao.jdbc;

import database.connectionpool.ConnectionPool;
import database.dao.interfaces.GenericDao;
import exception.ConnectionPoolException;
import model.Role;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexeev on 03.10.2016.
 */
public class RoleDaoImpl implements GenericDao<Role> {

    private ConnectionPool connectionPool;

    private Connection connection;

    private PreparedStatement statement;

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

    public RoleDaoImpl() throws ConnectionPoolException {
        this.connectionPool = ConnectionPool.getInstance();
    }


    @Override
    public boolean create(final Role role) throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(CREATE_QUERY);

        statement.setLong(1, role.getRoleId());
        statement.setString(2, role.getName());
        statement.setString(3, role.getDescription());
        statement.execute();

        connectionPool.closeConnection(connection, statement);

        logger.info("Successfully added new Role");
        return true;
    }

    @Override
    public Role read(long id) throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(READ_QUERY);

        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Role role = buildRole(resultSet);
            connectionPool.closeConnection(connection, statement, resultSet);
            logger.info("Successfully read Role");
            return role;
        }
        connectionPool.closeConnection(connection, statement, resultSet);
        return null;
    }

    @Override
    public boolean update(final Role role) throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(UPDATE_QUERY);

        statement.setLong(1, role.getRoleId());
        statement.setString(2, role.getName());
        statement.setString(3, role.getDescription());
        statement.setLong(4, role.getRoleId());

        boolean result = statement.executeUpdate() > 0;
        connectionPool.closeConnection(connection, statement);
        logger.info("Successfully updated Role");
        return result;
    }

    @Override
    public boolean delete(final Role role) throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(DELETE_QUERY);
        statement.setLong(1, role.getRoleId());

        boolean result = statement.executeUpdate() > 0;
        connectionPool.closeConnection(connection, statement);
        logger.info("Successfully deleted Role");
        return result;
    }

    @Override
    public long getId(final Role role) throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        statement = connection.prepareStatement(GET_ID_QUERY);

        statement.setString(1, role.getName());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            long result = resultSet.getLong(1);
            connectionPool.closeConnection(connection, statement, resultSet);
            logger.info("Successfully got Role id");
            return result;
        }
        connectionPool.closeConnection(connection, statement, resultSet);
        return 0;
    }

    @Override
    public Collection<Role> getAll() throws SQLException, ConnectionPoolException {
        connection = connectionPool.takeConnection();
        Set<Role> roles = new HashSet<>();
        statement = connection.prepareStatement(GET_ALL_QUERY);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            roles.add(buildRole(resultSet));
        }
        connectionPool.closeConnection(connection, statement);
        logger.info("Successfully got all Roles");
        return roles;
    }

    /**
     * Build role from ResultSet parameters
     *
     * @param resultSet
     * @return Role
     * @throws SQLException
     */
    private Role buildRole(ResultSet resultSet) throws SQLException {
        return new Role.Builder()
                .setRoleId(resultSet.getLong("role_id"))
                .setName(resultSet.getString("name"))
                .setDescription(resultSet.getString("description"))
                .build();
    }
}
