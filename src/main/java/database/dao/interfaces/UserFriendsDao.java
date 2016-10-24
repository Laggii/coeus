package database.dao.interfaces;

import exception.DaoException;
import model.User;

import java.util.Collection;

/**
 * Created by Alexeev on 24.10.2016.
 */

/**
 * UserFriendsDao interface with basic operations add friend, delete friend and get all user friends
 */
public interface UserFriendsDao {

    /**
     * Add user to friends list
     *
     * @param user
     * @param friend
     * @return true if operation successful
     * @throws DaoException
     */
    boolean addFriend(final User user, final User friend) throws DaoException;

    /**
     * Delete user from friends list
     *
     * @param user
     * @param friend
     * @return true if operation successful
     * @throws DaoException
     */
    boolean delFriend(final User user, final User friend) throws DaoException;

    /**
     * Get all user friends
     *
     * @param user
     * @return Colection of users
     * @throws DaoException
     */
    Collection<User> getFriends(final User user) throws DaoException;
}
