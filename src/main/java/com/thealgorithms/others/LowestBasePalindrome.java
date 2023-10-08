package com.thealgorithms.others;

import java.util.ArrayList;

/**
 * @brief Class for finding the lowest base in which a given integer is a palindrome.
     cf. https://oeis.org/A016026
 */
final public class LowestBasePalindrome {
    private LowestBasePalindrome() {
    }

    private static void checkBase(int base) {
        if (base <= 1) {
            throw new IllegalArgumentException("base must be greater than 1.");
        }
    }

    private static void checkNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("number must be nonnegative.");
        }
    }

    /**
     * @brief computes the representation of the input number in given base
     * @param number the input number
     * @param base the given base
     * @exception IllegalArgumentException number is negative or base is less than 2
     * @return the list containing the digits of the input number in the given base, the most
     *     significant digit is at the end of the array
     */
    public static ArrayList<Integer> computeDigitsInBase(int number, int base) {
        checkNumber(number);
        checkBase(base);
        var result = new ArrayList<Integer>();
        while (number > 0) {
            result.add(number % base);
            number /= base;
        }
        return result;
    }

    /**
     * @brief checks if the input array is a palindrome
     * @brief list the input array
     * @return true, if the input array is a palindrome, false otherwise
     */
    public static boolean isPalindromic(ArrayList<Integer> list) {
        for (int pos = 0; pos < list.size() / 2; ++pos) {
            if (list.get(pos) != list.get(list.size() - 1 - pos)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @brief checks if representation of the input number in given base is a palindrome
     * @param number the input number
     * @param base the given base
     * @exception IllegalArgumentException number is negative or base is less than 2
     * @return true, if the input number represented in the given base is a palindrome, false
     *     otherwise
     */
    public static boolean isPalindromicInBase(int number, int base) {
        checkNumber(number);
        checkBase(base);

        if (number <= 1) {
            return true;
        }

        if (number % base == 0) {
            // the last digit of number written in base is 0
            return false;
        }

        return isPalindromic(computeDigitsInBase(number, base));
    }

    /**
     * @brief finds the smallest base for which the representation of the input number is a
     * palindrome
     * @param number the input number
     * @exception IllegalArgumentException number is negative
     * @return the smallest base for which the representation of the input number is a palindrome
     */
    public static int lowestBasePalindrome(int number) {
        int base = 2;
        while (!isPalindromicInBase(number, base)) {
            ++base;
        }
        return base;
    }
}
