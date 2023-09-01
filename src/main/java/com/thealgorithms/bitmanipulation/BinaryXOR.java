package com.thealgorithms.bitmanipulation;

/**
 * calculate Xor value of two Binary Number
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public class BinaryXOR {
    public static String binaryXOR(int a, int b) {
        // Check if both inputs are non-negative
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Both inputs must be positive");
        }

        // Convert integers to binary strings
        String aBinary = Integer.toBinaryString(a);
        String bBinary = Integer.toBinaryString(b);

        // Determine the maximum length of the binary strings
        int maxLength = Math.max(aBinary.length(), bBinary.length());

        // Perform binary XOR operation character by character
        StringBuilder result = new StringBuilder("0b");
        for (int i = 0; i < maxLength; i++) {
            char charA = (i < aBinary.length()) ? aBinary.charAt(aBinary.length() - 1 - i) : '0';
            char charB = (i < bBinary.length()) ? bBinary.charAt(bBinary.length() - 1 - i) : '0';

            // Append '1' if characters are different, '0' if they are the same
            result.insert(2, (charA != charB) ? '1' : '0');
        }

        return result.toString();
    }
}
