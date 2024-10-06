package com.thealgorithms.bitmanipulation;

/**
 * @wikipedia - https://en.wikipedia.org/wiki/Two%27s_complement
 *            This Algorithm was first suggested by Jon Von Neumann
 * @author - https://github.com/Monk-AbhinayVerma
 * @return the two's complement of any binary number
 */
public final class TwosComplement {
    private TwosComplement() {
    }

    // Function to get the 2's complement of a binary number
    public static String twosComplement(String binary) {
        StringBuilder onesComplement = new StringBuilder();
        // Step 1: Find the 1's complement (invert the bits)
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '0') {
                onesComplement.append('1');
            } else {
                onesComplement.append('0');
            }
        }
        // Step 2: Add 1 to the 1's complement
        StringBuilder twosComplement = new StringBuilder(onesComplement);
        boolean carry = true;
        for (int i = onesComplement.length() - 1; i >= 0; i--) {
            if (onesComplement.charAt(i) == '1' && carry) {
                twosComplement.setCharAt(i, '0');
            } else if (onesComplement.charAt(i) == '0' && carry) {
                twosComplement.setCharAt(i, '1');
                carry = false;
            }
        }
        // If there is still a carry, append '1' at the beginning
        if (carry) {
            twosComplement.insert(0, '1');
        }
        return twosComplement.toString();
    }
}
