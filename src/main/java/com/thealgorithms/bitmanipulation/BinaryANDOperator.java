package com.thealgorithms.bitmanipulation;

/**
 * Binary AND Operator
 * @author Bama Charan Chhandogi (https://github.com/BamaCharanChhandogi)
 */

public class BinaryANDOperator {
    public static String binaryAnd(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("the value of both inputs must be positive");
        }

        String aBinary = Integer.toBinaryString(a);
        String bBinary = Integer.toBinaryString(b);

        int maxLen = Math.max(aBinary.length(), bBinary.length());

        StringBuilder resultBuilder = new StringBuilder("0b");
        for (int i = 0; i < maxLen; i++) {
            char charA = (i < aBinary.length()) ? aBinary.charAt(aBinary.length() - 1 - i) : '0';
            char charB = (i < bBinary.length()) ? bBinary.charAt(bBinary.length() - 1 - i) : '0';
            int digitA = charA - '0';
            int digitB = charB - '0';
            int andResult = (digitA == 1 && digitB == 1) ? 1 : 0;
            resultBuilder.insert(2, andResult);  // Insert at index 2 to account for "0b" prefix
        }

        return resultBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(binaryAnd(0,2));
    }
}