package com.thealgorithms.maths;

import java.util.ArrayList;

/**
 * In number theory, a vampire number (or true vampire number) is a composite
 * natural number with an even number of digits, that can be factored into two
 * natural numbers each with half as many digits as the original number and not
 * both with trailing zeroes, where the two factors contain precisely all the
 * digits of the original number, in any order, counting multiplicity. The first
 * vampire number is 1260 = 21 × 60.
 *
 * @see <a href='https://en.wikipedia.org/wiki/Vampire_number'>Vampire number on Wikipedia</a>
 */
public final class VampireNumber {
    // Forbid instantiation.
    private VampireNumber() {
    }

    static boolean isVampireNumber(int a, int b, boolean ignorePseudoVampireNumbers) {
        // Pseudo vampire numbers don't have to be of n/2 digits. E.g., 126 = 6 x 21 is such a number.
        if (ignorePseudoVampireNumbers && String.valueOf(a).length() != String.valueOf(b).length()) {
            return false;
        }

        String mulDigits = splitIntoSortedDigits(a * b);
        String factorDigits = splitIntoSortedDigits(a, b);

        return mulDigits.equals(factorDigits);
    }

    // Method to split a pair of numbers to digits and sort them in the ascending order.
    static String splitIntoSortedDigits(int... nums) {
        // Collect all digits in a list.
        ArrayList<Integer> digits = new ArrayList<>();
        for (int num : nums) {
            while (num > 0) {
                digits.add(num % 10);
                num /= 10;
            }
        }

        // Sort all digits and convert to String.
        StringBuilder res = new StringBuilder();
        digits.stream().sorted().forEach(res::append);
        return res.toString();
    }
}
