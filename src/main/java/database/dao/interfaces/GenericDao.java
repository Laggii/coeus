package database.dao.interfaces;

import exception.ConnectionPoolException;
import model.User;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Alexeev on 29.09.2016.
 */
public interface GenericDao<T> {
    /**
     * Add object to database
     *
     * @param object to be added
     * @return true if object is successfully added
     */
    boolean create(final T object) throws SQLException, ConnectionPoolException;

    /**
     * Get object from database by id
     *
     * @param id of the object
     * @return Object
     */
    T read(long id) throws SQLException, ConnectionPoolException;

    /**
     * Update object in database
     *
     * @param object to be updated
     * @return true if object is successfully updated
     */
    boolean update(final T object) throws SQLException, ConnectionPoolException;

    /**
     * Delete object from database
     *
     * @param object to be deleted
     * @return true if object is successfully deleted
     */
    boolean delete(final T object) throws SQLException, ConnectionPoolException;

    /**
     * Get id of the object from database
     *
     * @param object whose id you need to get
     * @return id of the object
     * @throws SQLException
     */
    long getId(final T object) throws SQLException, ConnectionPoolException;


    /**
     * Get all objects from database
     *
     * @return collection of objects
     */
    Collection<User> getAll() throws SQLException, ConnectionPoolException;
}
