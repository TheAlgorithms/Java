package com.thealgorithms.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for finding the lowest base in which a given integer is a
 * palindrome.
 * <p>
 * A number is a palindrome in a given base if its representation in that base
 * reads the same
 * forwards and backwards. For example, 15 in base 2 is 1111, which is
 * palindromic.
 * This class provides methods to check palindromic properties and find the
 * smallest base
 * where a number becomes palindromic.
 * </p>
 * <p>
 * Example: The number 15 in base 2 is represented as [1,1,1,1], which is
 * palindromic.
 * The number 10 in base 3 is represented as [1,0,1], which is also palindromic.
 * </p>
 *
 * @see <a href="https://oeis.org/A016026">OEIS A016026 - Smallest base in which
 *      n is palindromic</a>
 * @author TheAlgorithms Contributors
 */
public final class LowestBasePalindrome {
    private LowestBasePalindrome() {
    }

    /**
     * Validates the base, ensuring it is greater than 1.
     *
     * @param base the base to be checked
     * @throws IllegalArgumentException if the base is less than or equal to 1
     */
    private static void checkBase(int base) {
        if (base <= 1) {
            throw new IllegalArgumentException("Base must be greater than 1.");
        }
    }

    /**
     * Validates the number, ensuring it is non-negative.
     *
     * @param number the number to be checked
     * @throws IllegalArgumentException if the number is negative
     */
    private static void checkNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative.");
        }
    }

    /**
     * Computes the digits of a given number in a specified base.
     * <p>
     * The digits are returned in reverse order (least significant digit first).
     * For example, the number 13 in base 2 produces [1,0,1,1] representing 1101 in
     * binary.
     * </p>
     *
     * @param number the number to be converted (must be non-negative)
     * @param base   the base to be used for the conversion (must be greater than 1)
     * @return a list of digits representing the number in the given base, with the
     *         least significant digit at the beginning of the list
     * @throws IllegalArgumentException if the number is negative or the base is
     *                                  less than 2
     */
    public static List<Integer> computeDigitsInBase(int number, int base) {
        checkNumber(number);
        checkBase(base);

        List<Integer> digits = new ArrayList<>();
        while (number > 0) {
            digits.add(number % base);
            number /= base;
        }
        return digits;
    }

    /**
     * Checks if a list of integers is palindromic.
     * <p>
     * A list is palindromic if it reads the same forwards and backwards.
     * For example, [1,2,1] is palindromic, but [1,2,3] is not.
     * </p>
     *
     * @param list the list of integers to be checked
     * @return {@code true} if the list is a palindrome, {@code false} otherwise
     */
    public static boolean isPalindromic(List<Integer> list) {
        int size = list.size();
        for (int i = 0; i < size / 2; i++) {
            if (!list.get(i).equals(list.get(size - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the representation of a given number in a specified base is
     * palindromic.
     * <p>
     * This method first validates the input, then applies optimization: if the
     * number
     * ends with 0 in the given base (i.e., divisible by the base), it cannot be
     * palindromic
     * as palindromes cannot start with 0.
     * </p>
     * <p>
     * Examples:
     * - 101 in base 10 is palindromic (101)
     * - 15 in base 2 is palindromic (1111)
     * - 10 in base 3 is palindromic (101)
     * </p>
     *
     * @param number the number to be checked (must be non-negative)
     * @param base   the base in which the number will be represented (must be
     *               greater than 1)
     * @return {@code true} if the number is palindromic in the specified base,
     *         {@code false} otherwise
     * @throws IllegalArgumentException if the number is negative or the base is
     *                                  less than 2
     */
    public static boolean isPalindromicInBase(int number, int base) {
        checkNumber(number);
        checkBase(base);

        if (number <= 1) {
            return true;
        }

        if (number % base == 0) {
            // If the last digit of the number in the given base is 0, it can't be
            // palindromic
            return false;
        }

        return isPalindromic(computeDigitsInBase(number, base));
    }

    /**
     * Finds the smallest base in which the representation of a given number is
     * palindromic.
     * <p>
     * This method iteratively checks bases starting from 2 until it finds one where
     * the number is palindromic. For any number n ≥ 2, the number is always
     * palindromic
     * in base n-1 (represented as [1, 1]), so this algorithm is guaranteed to
     * terminate.
     * </p>
     * <p>
     * Time Complexity: O(n * log(n)) in the worst case, where we check each base
     * and
     * convert the number to that base.
     * </p>
     * <p>
     * Examples:
     * - lowestBasePalindrome(15) returns 2 (15 in base 2 is 1111)
     * - lowestBasePalindrome(10) returns 3 (10 in base 3 is 101)
     * - lowestBasePalindrome(11) returns 10 (11 in base 10 is 11)
     * </p>
     *
     * @param number the number to be checked (must be non-negative)
     * @return the smallest base in which the number is a palindrome (base ≥ 2)
     * @throws IllegalArgumentException if the number is negative
     */
    public static int lowestBasePalindrome(int number) {
        checkNumber(number);

        int base = 2;
        while (!isPalindromicInBase(number, base)) {
            base++;
        }
        return base;
    }
}
