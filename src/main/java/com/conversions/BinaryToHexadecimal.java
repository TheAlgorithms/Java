package src.main.java.com.conversions;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;


public class BinaryToHexadecimal {

    /**
     * hm to store hexadecimal codes for binary numbers
     * within the range: 0000 to 1111 i.e. for decimal numbers 0 to 15
     */
   private static Map<Integer, String> hmHexadecimal = new HashMap<>(16);

    static {
        int i;
        for (i = 0; i < 10; i++)
            hmHexadecimal.put(i, String.valueOf(i));

        for (i = 10; i < 16; i++)
            hmHexadecimal.put(i, String.valueOf((char) ('A' + i - 10)));
    }

    /**
     * This method converts a binary number to
     * a hexadecimal number.
     *
     * @param binStr The binary number
     * @return The hexadecimal number
     */

    public String binToHex(String binStr) {
        BigInteger binary = new BigInteger(binStr);
        // String to store hexadecimal code
        String hex = "";

        int currentBit;
        BigInteger tenValue = new BigInteger("10");
        while (binary.compareTo(BigInteger.ZERO) != 0) {
            // to store decimal equivalent of number formed by 4 decimal digits
            int code4 = 0;
            for (int i = 0; i < 4; i++) {
                currentBit = binary.mod(tenValue).intValueExact();
                binary = binary.divide(tenValue);
                code4 += currentBit * Math.pow(2, i);
            }
            hex = hmHexadecimal.get(code4) + hex;
        }
        return hex;
    }
}
