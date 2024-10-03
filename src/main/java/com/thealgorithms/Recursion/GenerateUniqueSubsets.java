package com.thealgorithms.Recursion;

// program to find unique power set of a string

import java.util.*;

/**
 * Finds all permutations of given array
 * @author Tuhin Mondal (<a href="https://github.com/tuhinm2002">Git-Tuhin Mondal</a>)
 */

public final class GenerateUniqueSubsets {

    private GenerateUniqueSubsets() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static List<String> subsetRecursion(String str) {
        Set<String> ans = doRecursion("", str);
        List<String> a = new ArrayList<>(ans.stream().toList());
        Collections.sort(a);
        return a;
    }

    private static Set<String> doRecursion(String p, String up) {
        if (up.isEmpty()) {
            Set<String> list = new HashSet<>();
            list.add(p);
            return list;
        }

        // Taking the character
        char ch = up.charAt(0);
        // Adding the character in the recursion
        Set<String> left = doRecursion(p + ch, up.substring(1));
        // Not adding the character in the recursion
        Set<String> right = doRecursion(p, up.substring(1));

        left.addAll(right);

        return left;
    }
}
