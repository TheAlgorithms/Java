package com.thealgorithms.others;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief Class for finding the lowest base in which a given integer is a palindrome.
     cf. https://oeis.org/A016026
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
     *
     * @param number the number to be converted
     * @param base the base to be used for the conversion
     * @return a list of digits representing the number in the given base, with the most
     *         significant digit at the end of the list
     * @throws IllegalArgumentException if the number is negative or the base is less than 2
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
     * Checks if the representation of a given number in a specified base is palindromic.
     *
     * @param number the number to be checked
     * @param base the base in which the number will be represented
     * @return {@code true} if the number is palindromic in the specified base, {@code false} otherwise
     * @throws IllegalArgumentException if the number is negative or the base is less than 2
     */
    public static boolean isPalindromicInBase(int number, int base) {
        checkNumber(number);
        checkBase(base);

        if (number <= 1) {
            return true;
        }

        if (number % base == 0) {
            // If the last digit of the number in the given base is 0, it can't be palindromic
            return false;
        }

        return isPalindromic(computeDigitsInBase(number, base));
    }

    /**
     * Finds the smallest base in which the representation of a given number is palindromic.
     *
     * @param number the number to be checked
     * @return the smallest base in which the number is a palindrome
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
