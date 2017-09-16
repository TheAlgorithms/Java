package conversions;

/**
 * Converts any Binary number to an Octal Number
 *
 * @author Zachary Jones
 */
public class BinaryToOctal {

    /**
     * This method converts a binary number to
     * an octal number.
     *
     * @param binary The binary number
     * @return The octal number
     */
    public static int convertBinaryToOctal(int binary) {
        int o = 0, r, j = 1;
        while (binary != 0) {
            r = binary % 10;
            o = o + r * j;
            j = j * 2;
            binary = binary / 10;
        }
        return o;
    }
}
