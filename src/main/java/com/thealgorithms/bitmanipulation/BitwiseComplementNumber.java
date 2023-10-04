package com.thealgorithms.bitmanipulation;

/**
 * For any n positive number in base 10, return the complement of its binary
 * representation as an integer in base 10.
 * Constraints:
 * 0 ≤ n ≤ 10^9
 *
 * @author Anjita Gargi Chandora (<a
 * href="https://github.com/anjitagargi">Git-anjitagargi</a>)
 */

public class BitwiseComplementNumber {
    public static int findBitwiseComplement(int num) {
        // if the value of num is 0, return 1
        if (num == 0) {
            return 1;
        }
        // converting the value into its binary representation and counting the
        // number of bits required by this number
        int bitCount = (int) Math.floor((int) (Math.log(num) / Math.log(2))) + 1;

        // computing the all bits set of the number
        int allBitsSet = (int) Math.pow(2, bitCount) - 1;
        // flipping all bits of number by taking xor with allBitsSet
        return num ^ allBitsSet;
    }

    public static void main(String[] args) {
        int[] decimalValues = {42, 233, 100, 999999, 54};

        for (int i = 0; i < decimalValues.length; i++) {
            System.out.print(i + 1);
            System.out.print(".\tInput: " + decimalValues[i]);
            System.out.print("\n\tBitwise complement of " + decimalValues[i] + " is: ");
            System.out.println(findBitwiseComplement(decimalValues[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
