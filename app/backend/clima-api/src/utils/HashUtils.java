package utils;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtils {

    public static String createHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean compare(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
