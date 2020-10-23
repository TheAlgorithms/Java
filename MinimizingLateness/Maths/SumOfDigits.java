package Maths;

public class SumOfDigits {
    public static void main(String[] args) {
        assert
                sumOfDigits(-123) == 6
                        && sumOfDigitsRecursion(-123) == 6
                        && sumOfDigitsFast(-123) == 6;

        assert sumOfDigits(0) == 0
                && sumOfDigitsRecursion(0) == 0
                && sumOfDigitsFast(0) == 0;


        assert sumOfDigits(12345) == 15
                && sumOfDigitsRecursion(12345) == 15
                && sumOfDigitsFast(12345) == 15;
    }

    /**
     * Calculate the sum of digits of a number
     *
     * @param number the number contains digits
     * @return sum of digits of given {@code number}
     */
    public static int sumOfDigits(int number) {
        number = number < 0 ? -number : number; /* calculate abs value */
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    /**
     * Calculate the sum of digits of a number using recursion
     *
     * @param number the number contains digits
     * @return sum of digits of given {@code number}
     */
    public static int sumOfDigitsRecursion(int number) {
        number = number < 0 ? -number : number; /* calculate abs value */
        return number < 10 ? number : number % 10 + sumOfDigitsRecursion(number / 10);
    }

    /**
     * Calculate the sum of digits of a number using char array
     *
     * @param number the number contains digits
     * @return sum of digits of given {@code number}
     */
    public static int sumOfDigitsFast(int number) {
        number = number < 0 ? -number : number; /* calculate abs value */
        char[] digits = (number + "").toCharArray();
        int sum = 0;
        for (int i = 0; i < digits.length; ++i) {
            sum += digits[i] - '0';
        }
        return sum;
    }
}
