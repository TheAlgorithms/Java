public class BinaryExponentiation {
    public static long binaryExponentiation(long base, long exponent) {
        long result = 1;

        while (exponent > 0) {
            // If the current exponent is odd, multiply result with base
            if (exponent % 2 == 1) {
                result *= base;
            }

            // Square the base and halve the exponent
            base *= base;
            exponent /= 2;
        }

        return result;
    }

    public static void main(String[] args) {
        long base = 2;
        long exponent = 10;

        long result = binaryExponentiation(base, exponent);
        System.out.println(base + " raised to the power of " + exponent + " is: " + result);
    }
}
