package com.thealgorithms.bitmanipulation;

/**
 * This class provides a method to compute the Two's Complement of a given binary number.
 *
 * <p>In two's complement representation, a binary number's negative value is obtained
 * by taking the one's complement (inverting all bits) and then adding 1 to the result.
 * This method handles both small and large binary strings and ensures the output is
 * correct for all binary inputs, including edge cases like all zeroes and all ones.
 *
 * <p>For more information on Two's Complement:
 * @see <a href="https://en.wikipedia.org/wiki/Two%27s_complement">Wikipedia - Two's Complement</a>
 *
 * <p>Algorithm originally suggested by Jon von Neumann.
 *
 * @author Abhinay Verma (https://github.com/Monk-AbhinayVerma)
 */
public final class TwosComplement {
    private TwosComplement() {
    }

    /**
     * Computes the Two's Complement of the given binary string.
     * Steps:
     * 1. Compute the One's Complement (invert all bits).
     * 2. Add 1 to the One's Complement to get the Two's Complement.
     * 3. Iterate from the rightmost bit to the left, adding 1 and carrying over as needed.
     * 4. If a carry is still present after the leftmost bit, prepend '1' to handle overflow.
     *
     * @param binary The binary number as a string (only '0' and '1' characters allowed).
     * @return The two's complement of the input binary string as a new binary string.
     * @throws IllegalArgumentException If the input contains non-binary characters.
     */
    public static String twosComplement(String binary) {
        if (!binary.matches("[01]+")) {
            throw new IllegalArgumentException("Input must contain only '0' and '1'.");
        }

        StringBuilder onesComplement = new StringBuilder();
        for (char bit : binary.toCharArray()) {
            onesComplement.append(bit == '0' ? '1' : '0');
        }

        StringBuilder twosComplement = new StringBuilder(onesComplement);
        boolean carry = true;

        for (int i = onesComplement.length() - 1; i >= 0 && carry; i--) {
            if (onesComplement.charAt(i) == '1') {
                twosComplement.setCharAt(i, '0');
            } else {
                twosComplement.setCharAt(i, '1');
                carry = false;
            }
        }

        if (carry) {
            twosComplement.insert(0, '1');
        }

        return twosComplement.toString();
    }
}
