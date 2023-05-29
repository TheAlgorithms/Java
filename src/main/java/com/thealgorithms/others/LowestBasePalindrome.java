package com.thealgorithms.others;

import java.util.ArrayList;

/**
 * Class for finding the lowest base in which a given integer is a palindrome.
 * Includes auxiliary methods for converting between bases and reversing
 * strings.
 *
 * @author RollandMichael
 * @version 2017.09.28
 */
public class LowestBasePalindrome {
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

    public static boolean isPalindromic(ArrayList<Integer> list) {
        for (int pos = 0; pos < list.size()/2; ++pos) {
            if(list.get(pos) != list.get(list.size()-1-pos)) {
                return false;
            }
        }
        return true;
    }

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

    public static int lowestBasePalindrome(int number) {
        int base = 2;
        while(!isPalindromicInBase(number, base)) {
            ++base;
        }
        return base;
    }
}
