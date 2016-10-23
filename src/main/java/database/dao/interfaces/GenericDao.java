package database.dao.interfaces;

import exception.DaoException;

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
     * @throws DaoException
     */
    boolean create(final T object) throws DaoException;

    /**
     * Get object from database by id
     *
     * @param id of the object
     * @return Object
     * @throws DaoException
     */
    T read(long id) throws DaoException;

    /**
     * Update object in database
     *
     * @param object to be updated
     * @return true if object is successfully updated
     * @throws DaoException
     */
    boolean update(final T object) throws DaoException;

    /**
     * Delete object from database
     *
     * @param object to be deleted
     * @return true if object is successfully deleted
     * @throws DaoException
     */
    boolean delete(final T object) throws DaoException;

    /**
     * Get id of the object from database using it's field
     *
     * @param field on of the object required fields (email, name, etc)
     * @return id of the object
     * @throws DaoException
     */
    long getId(final String field) throws DaoException;


    /**
     * Get all objects from database
     *
     * @return collection of objects
     * @throws DaoException
     */
    Collection<T> getAll() throws DaoException;
}
