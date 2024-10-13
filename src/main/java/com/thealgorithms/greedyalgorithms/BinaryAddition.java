package com.thealgorithms.greedyalgorithms;

import java.util.Collections;

/**
 * BinaryAddition class to perform binary addition of two binary strings.
 */
public class BinaryAddition {
    /**
     * Computes the sum of two binary characters and a carry.
     * @param a First binary character ('0' or '1').
     * @param b Second binary character ('0' or '1').
     * @param carry The carry from the previous operation ('0' or '1').
     * @return The sum as a binary character ('0' or '1').
     */
    public char sum(char a, char b, char carry) {
        int count = 0;
        if (a == '1') {
            count++;
        }
        if (b == '1') {
            count++;
        }
        if (carry == '1') {
            count++;
        }
        return count % 2 == 0 ? '0' : '1';
    }
    /**
     * Computes the carry for the next higher bit from two binary characters and a carry.
     * @param a First binary character ('0' or '1').
     * @param b Second binary character ('0' or '1').
     * @param carry The carry from the previous operation ('0' or '1').
     * @return The carry for the next bit ('0' or '1').
     */
    public char carry(char a, char b, char carry) {
        int count = 0;
        if (a == '1') {
            count++;
        }
        if (b == '1') {
            count++;
        }
        if (carry == '1') {
            count++;
        }
        return count >= 2 ? '1' : '0';
    }
    /**
     * Adds two binary strings and returns their sum as a binary string.
     * @param a First binary string.
     * @param b Second binary string.
     * @return Binary string representing the sum of the two binary inputs.
     */
    public String addBinary(String a, String b) {
        // Padding the shorter string with leading zeros
        int maxLength = Math.max(a.length(), b.length());
        a = String.join("", Collections.nCopies(maxLength - a.length(), "0")) + a;
        b = String.join("", Collections.nCopies(maxLength - b.length(), "0")) + b;
        StringBuilder result = new StringBuilder();
        char carry = '0';
        // Iterating over the binary strings from the least significant to the most significant bit
        for (int i = maxLength - 1; i >= 0; i--) {
            char sum = sum(a.charAt(i), b.charAt(i), carry);
            carry = carry(a.charAt(i), b.charAt(i), carry);
            result.append(sum);
        }
        // If there's a remaining carry, append it
        if (carry == '1') {
            result.append('1');
        }
        // Reverse the result as we constructed it from the least significant bit
        return result.reverse().toString();
    }
}
