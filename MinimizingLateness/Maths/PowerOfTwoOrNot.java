package Maths;

/**
 * A utility to check if a given number is power of two or not.
 * For example 8,16 etc.
 */

public class PowerOfTwoOrNot {

    public static void main(String[] args) {
        assert !checkIfPowerOfTwoOrNot(0);
        assert checkIfPowerOfTwoOrNot(1);
        assert checkIfPowerOfTwoOrNot(8);
        assert checkIfPowerOfTwoOrNot(16);
        assert checkIfPowerOfTwoOrNot(1024);
    }


    /**
     * Checks whether given number is power of two or not.
     *
     * @param number the number to check
     * @return {@code true} if given number is power of two, otherwise {@code false}
     */
    public static boolean checkIfPowerOfTwoOrNot(int number) {
        return number != 0 && ((number & (number - 1)) == 0);
    }
}
