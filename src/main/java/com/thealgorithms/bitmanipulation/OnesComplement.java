package com.thealgorithms.bitmanipulation;

/**
 * @author - https://github.com/Monk-AbhinayVerma
 * @Wikipedia - https://en.wikipedia.org/wiki/Ones%27_complement
 *            The class OnesComplement computes the complement of binary number
 *            and returns
 *            the complemented binary string.
 * @return the complimented binary string
 */
public final class OnesComplement {
    private OnesComplement() {
    }

    /**
     * Returns the 1's complement of a binary string.
     *
     * @param binary A string representing a binary number (e.g., "1010").
     * @return A string representing the 1's complement.
     * @throws IllegalArgumentException if the input is null or contains characters other than '0' or '1'.
     */
    public static String onesComplement(String binary) {
        if (binary == null || binary.isEmpty()) {
            throw new IllegalArgumentException("Input must be a non-empty binary string.");
        }

        StringBuilder complement = new StringBuilder(binary.length());
        for (char bit : binary.toCharArray()) {
            switch (bit) {
                case '0' -> complement.append('1');
                case '1' -> complement.append('0');
                default -> throw new IllegalArgumentException("Input must contain only '0' and '1'. Found: " + bit);
            }
        }
        return complement.toString();
    }
}
