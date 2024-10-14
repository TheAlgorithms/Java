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

    // Function to get the 1's complement of a binary number
    public static String onesComplement(String binary) {
        StringBuilder complement = new StringBuilder();
        // Invert each bit to get the 1's complement
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '0') {
                complement.append('1');
            } else {
                complement.append('0');
            }
        }
        return complement.toString();
    }
}
