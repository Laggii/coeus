package utils;

/**
 * Created by Alexeev on 23.10.2016.
 */

import org.mindrot.jbcrypt.BCrypt;

/**
 * Methods to process user hashes and passwords
 */
public class Encryption {

    /**
     * Check that plaintext password matches provided hash
     *
     * @param password
     * @param hash
     * @return true if matches
     */
    public static boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    /**
     * Encrypt password with salt
     *
     * @param password
     * @return hash
     */
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
