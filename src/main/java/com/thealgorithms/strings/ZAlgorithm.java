/*
 * https://en.wikipedia.org/wiki/Z-algorithm
 */
package com.thealgorithms.strings;

public final class ZAlgorithm {

    private ZAlgorithm() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static int[] zFunction(String s) {
        int n = s.length();
        int[] zArray = new int[n];
        int leftBound = 0;
        int rightBound = 0;

        for (int i = 1; i < n; i++) {
            if (i <= rightBound) {
                zArray[i] = Math.min(rightBound - i + 1, zArray[i - leftBound]);
            }

            while (i + zArray[i] < n && s.charAt(zArray[i]) == s.charAt(i + zArray[i])) {
                zArray[i]++;
            }

            if (i + zArray[i] - 1 > rightBound) {
                leftBound = i;
                rightBound = i + zArray[i] - 1;
            }
        }

        return zArray;
    }

    public static int search(String text, String pattern) {
        String s = pattern + "$" + text;
        int[] zArray = zFunction(s);
        int p = pattern.length();

        for (int i = 0; i < zArray.length; i++) {
            if (zArray[i] == p) {
                return i - p - 1;
            }
        }
        return -1;
    }
}
