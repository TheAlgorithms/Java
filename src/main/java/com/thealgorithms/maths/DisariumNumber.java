package com.thealgorithms.maths;

/**
 * Disarium number is a number where the sum of its digits powered
 * with their respective positions is equal to the number itself.
 * Example: 135 = 1^1 + 3^2 + 5^3 = 1 + 9 + 125 = 135
 *
 * @see <a href="https://en.wikipedia.org/wiki/Disarium_number">Disarium Number</a>
 */
public final class DisariumNumber {

    private DisariumNumber() {
    }

    /**
     * Checks if a number is a Disarium number.
     *
     * @param number the number to check (must be positive)
     * @return true if number is Disarium, false otherwise
     * @throws IllegalArgumentException if number is not positive
     */
    public static boolean isDisarium(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Input must be a positive integer.");
        }
        int digits = String.valueOf(number).length();
        int temp = number;
        int sum = 0;
        while (temp > 0) {
            int lastDigit = temp % 10;
            sum += (int) Math.pow(lastDigit, digits);
            digits--;
            temp /= 10;
        }
        return sum == number;
    }
}
