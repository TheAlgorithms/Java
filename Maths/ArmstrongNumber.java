package Maths;

/**
 * A positive integer is called an Armstrong number of order n if (abcd... = a^n + b^n + c^n + d^n + ...

 */

public class ArmstrongNumber {
    public static void main(String[] args) {
        System.out.println(isArmstrongNumber(153)); /* 1*1*1 + 5*5*5 + 3*3*3 == 153 */
        System.out.println(isArmstrongNumber(15)); /* 1*1 + 5*5 != 15 */
        System.out.println(isArmstrongNumber(1634)); /* 1*1*1*1 + 6*6*6*6 + 3*3*3*3 + 4*4*4*4 == 1634 */
    }

    /**
     * Check if {@code number} is armstrong number or not
     *
     * @param number the number
     * @return {@code true} if {@code number} is armstrong number, otherwise false
     */
    public static boolean isArmstrongNumber(int number) {
        int originalNumber, remainder, result = 0, n = 0;
        originalNumber = number;

        // Counting digits in the number.
        for (;originalNumber != 0; originalNumber /= 10, ++n);

        originalNumber = number;

        for (;originalNumber != 0; originalNumber /= 10)
        {
            remainder = originalNumber % 10;
            result += Math.pow(remainder, n);
        }
        return (result == number) ? true : false;
    }
}
