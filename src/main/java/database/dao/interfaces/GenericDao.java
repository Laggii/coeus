package database.dao.interfaces;

import java.sql.SQLException;
import java.util.stream.Stream;

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
    boolean create(final T object) throws SQLException;

    /**
     * Get object from database by id
     *
     * @param id of the object
     * @return Object
     */
    T read(int id) throws SQLException;

    /**
     * Update object in database
     *
     * @param object to be updated
     * @return true if object is successfully updated
     */
    boolean update(final T object) throws SQLException;

    /**
     * Delete object from database
     *
     * @param object to be deleted
     * @return true if object is successfully deleted
     */
    boolean delete(final T object) throws SQLException;


    /**
     * Get all objects from database
     *
     * @return stream of objects
     */
    Stream<T> getAll() throws SQLException;
}
