import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * This class provides methods to convert a decimal number to a binary number.
 */
final class DecimalToBinary {
    private static final int BINARY_BASE = 2;
    private static final int DECIMAL_MULTIPLIER = 10;

    private DecimalToBinary() {
        // Prevent instantiation of utility class
    }

    /**
     * Converts a decimal number to a binary number using a conventional algorithm.
     * 
     * @param decimalNumber the decimal number to convert
     * @return the binary representation of the decimal number
     */
    public static int convertUsingConventionalAlgorithm(int decimalNumber) {
        int binaryNumber = 0;
        int position = 1;

        while (decimalNumber > 0) {
            int remainder = decimalNumber % BINARY_BASE;
            binaryNumber += remainder * position;
            position *= DECIMAL_MULTIPLIER;
            decimalNumber /= BINARY_BASE;
        }

        return binaryNumber;
    }

    /**
     * Converts a decimal number to a binary number using a bitwise algorithm.
     * 
     * @param decimalNumber the decimal number to convert
     * @return the binary representation of the decimal number
     */
    public static int convertUsingBitwiseAlgorithm(int decimalNumber) {
        int binaryNumber = 0;
        int position = 1;

        while (decimalNumber > 0) {
            int leastSignificantBit = decimalNumber & 1; // Extract LSB using bitwise AND
            binaryNumber += leastSignificantBit * position;
            position *= DECIMAL_MULTIPLIER;
            decimalNumber >>= 1; // Right shift the decimal number to move to the next bit
        }

        return binaryNumber;
    }

    /**
     * Unit tests for DecimalToBinary conversions.
     */
    public static class DecimalToBinaryTest {

        @Test
        void testConvertUsingConventionalAlgorithm() {
            // Testing conversion using conventional method
            assertEquals(1101, DecimalToBinary.convertUsingConventionalAlgorithm(13)); // 13 in binary is 1101
            assertEquals(1010, DecimalToBinary.convertUsingConventionalAlgorithm(10)); // 10 in binary is 1010
            assertEquals(1, DecimalToBinary.convertUsingConventionalAlgorithm(1)); // 1 in binary is 1
            assertEquals(0, DecimalToBinary.convertUsingConventionalAlgorithm(0)); // 0 in binary is 0
            assertEquals(10010, DecimalToBinary.convertUsingConventionalAlgorithm(18)); // 18 in binary is 10010
        }

        @Test
        void testConvertUsingBitwiseAlgorithm() {
            // Testing conversion using bitwise method
            assertEquals(1101, DecimalToBinary.convertUsingBitwiseAlgorithm(13)); // 13 in binary is 1101
            assertEquals(1010, DecimalToBinary.convertUsingBitwiseAlgorithm(10)); // 10 in binary is 1010
            assertEquals(1, DecimalToBinary.convertUsingBitwiseAlgorithm(1)); // 1 in binary is 1
            assertEquals(0, DecimalToBinary.convertUsingBitwiseAlgorithm(0)); // 0 in binary is 0
            assertEquals(10010, DecimalToBinary.convertUsingBitwiseAlgorithm(18)); // 18 in binary is 10010
        }
    }
}
