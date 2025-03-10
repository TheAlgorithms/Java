package com.thealgorithms.others.cn;

public final class HammingDistance {
    private HammingDistance() {
    }

    private static void checkChar(char inChar) {
        if (inChar != '0' && inChar != '1') {
            throw new IllegalArgumentException("Input must be a binary string.");
        }
    }

    public static int compute(char charA, char charB) {
        checkChar(charA);
        checkChar(charB);
        return charA == charB ? 0 : 1;
    }

    public static int compute(String bitsStrA, String bitsStrB) {
        if (bitsStrA.length() != bitsStrB.length()) {
            throw new IllegalArgumentException("Input strings must have the same length.");
        }

        int totalErrorBitCount = 0;

        for (int i = 0; i < bitsStrA.length(); i++) {
            totalErrorBitCount += compute(bitsStrA.charAt(i), bitsStrB.charAt(i));
        }

        return totalErrorBitCount;
    }
}
