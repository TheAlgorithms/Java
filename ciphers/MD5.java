import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Utils {

    public static String md5(String str) {
        try {
            // Generate an MD5 encrypted computation digest
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Calculating MD5 functions
            md.update(str.getBytes());
            // digest() returns the MD5 hash value, which is an 8-bit string, this is because the MD5 hash value is a 16 bit hex value, it's actually an 8-bit character
            // The function BigInteger converts an 8-bit string into a 16 bit hex value, which is represented by a string, and gets a hash value in the form of a string
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
