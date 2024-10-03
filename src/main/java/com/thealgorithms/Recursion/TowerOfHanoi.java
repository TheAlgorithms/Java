package com.thealgorithms.Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds all permutations of given array
 * @author Tuhin Mondal (<a href="https://github.com/tuhinm2002">Git-Tuhin Mondal</a>)
 */

public final class TowerOfHanoi {
    private TowerOfHanoi() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static List<String> towerOfHanoi(int n) {
        List<String> arr = new ArrayList<>();
        recursionApproach(n, 'A', 'B', 'C', arr);
        return arr;
    }

    public static void recursionApproach(int n, char a, char b, char c, List<String> list) {
        if (n == 1) {
            list.add("Take disk 1 from rod " + a + " to rod " + b);
            return;
        }

        recursionApproach(n - 1, a, c, b, list);
        list.add("Take disk " + n + " from rod " + a + " to rod " + b);
        recursionApproach(n - 1, c, b, a, list);
    }
}
