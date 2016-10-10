package database.dao.interfaces;

import exception.ConnectionPoolException;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Alexeev on 29.09.2016.
 */

/**
 * Generic Dao interface with basic CRUD operations
 *
 * @param <T>
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
     * Get id of the object from database using it's field
     *
     * @param field on of the object required fields (email, name, etc)
     * @return id of the object
     * @throws SQLException
     */
    long getId(final String field) throws SQLException, ConnectionPoolException;


    /**
     * Get all objects from database
     *
     * @return collection of objects
     */
    Collection<T> getAll() throws SQLException, ConnectionPoolException;
}
