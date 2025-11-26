/*
 * https://en.wikipedia.org/wiki/Z-algorithm
 */

package com.thealgorithms.strings;

public class ZAlgorithm {

    public static int[] zFunction(String s) {
        int n = s.length();
        int[] z = new int[n];
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r)
                z[i] = Math.min(r - i + 1, z[i - l]);
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i]))
                z[i]++;
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }

    public static int search(String text, String pattern) {
        String s = pattern + "$" + text;
        int[] z = zFunction(s);
        int p = pattern.length();
        for (int i = 0; i < z.length; i++) {
            if (z[i] == p)
                return i - p - 1;
        }
        return -1;
    }
}
