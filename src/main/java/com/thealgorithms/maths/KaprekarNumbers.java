package com.thealgorithms.maths;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for identifying and working with Kaprekar Numbers.
 * <p>
 * A Kaprekar number is a positive integer with the following property:
 * If you square it, then split the resulting number into two parts (right part
 * has same number of
 * digits as the original number, left part has the remaining digits), and
 * finally add the two
 * parts together, you get the original number.
 * <p>
 * For example:
 * <ul>
 * <li>9: 9² = 81 → 8 + 1 = 9 (Kaprekar number)</li>
 * <li>45: 45² = 2025 → 20 + 25 = 45 (Kaprekar number)</li>
 * <li>297: 297² = 88209 → 88 + 209 = 297 (Kaprekar number)</li>
 * </ul>
 * <p>
 * Note: The right part can have leading zeros, but must not be all zeros.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Kaprekar_number">Kaprekar Number
 *      - Wikipedia</a>
 * @author TheAlgorithms (https://github.com/TheAlgorithms)
 */
public final class KaprekarNumbers {
    private KaprekarNumbers() {
    }

    /**
     * Finds all Kaprekar numbers within a given range (inclusive).
     *
     * @param start the starting number of the range (inclusive)
     * @param end   the ending number of the range (inclusive)
     * @return a list of all Kaprekar numbers in the specified range
     * @throws IllegalArgumentException if start is greater than end or if start is
     *                                  negative
     */
    public static List<Long> kaprekarNumberInRange(long start, long end) {
        if (start > end) {
            throw new IllegalArgumentException("Start must be less than or equal to end. Given start: " + start + ", end: " + end);
        }
        if (start < 0) {
            throw new IllegalArgumentException("Start must be non-negative. Given start: " + start);
        }

        ArrayList<Long> list = new ArrayList<>();
        for (long i = start; i <= end; i++) {
            if (isKaprekarNumber(i)) {
                list.add(i);
            }
        }

        return list;
    }

    /**
     * Checks whether a given number is a Kaprekar number.
     * <p>
     * The algorithm works as follows:
     * <ol>
     * <li>Square the number</li>
     * <li>Split the squared number into two parts: left and right</li>
     * <li>The right part has the same number of digits as the original number</li>
     * <li>Add the left and right parts</li>
     * <li>If the sum equals the original number, it's a Kaprekar number</li>
     * </ol>
     * <p>
     * Special handling is required for numbers whose squares contain zeros.
     *
     * @param num the number to check
     * @return true if the number is a Kaprekar number, false otherwise
     * @throws IllegalArgumentException if num is negative
     */
    public static boolean isKaprekarNumber(long num) {
        if (num < 0) {
            throw new IllegalArgumentException("Number must be non-negative. Given: " + num);
        }

        if (num == 0 || num == 1) {
            return true;
        }

        String number = Long.toString(num);
        BigInteger originalNumber = BigInteger.valueOf(num);
        BigInteger numberSquared = originalNumber.multiply(originalNumber);
        String squaredStr = numberSquared.toString();

        // Special case: if the squared number has the same length as the original
        if (number.length() == squaredStr.length()) {
            return number.equals(squaredStr);
        }

        // Calculate the split position
        int splitPos = squaredStr.length() - number.length();

        // Split the squared number into left and right parts
        String leftPart = squaredStr.substring(0, splitPos);
        String rightPart = squaredStr.substring(splitPos);

        // Parse the parts as BigInteger (handles empty left part as zero)
        BigInteger leftNum = leftPart.isEmpty() ? BigInteger.ZERO : new BigInteger(leftPart);
        BigInteger rightNum = new BigInteger(rightPart);

        // Check if right part is all zeros (invalid for Kaprekar numbers except 1)
        if (rightNum.equals(BigInteger.ZERO)) {
            return false;
        }

        // Check if the sum equals the original number
        return leftNum.add(rightNum).equals(originalNumber);
    }
}
