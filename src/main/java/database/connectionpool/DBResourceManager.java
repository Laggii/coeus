package database.connectionpool;

import java.util.ResourceBundle;

/**
 * Created by Alexeev on 26.09.2016.
 */

/**
 * DBResourceManager provides database connection settings from ResourceBundle
 */
public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle("connectionpool.dbtest");

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
