package com.thealgorithms.recursion;

// program to find power set of a string

import java.util.ArrayList;
import java.util.List;

public final class GenerateSubsets {

    private GenerateSubsets() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static List<String> subsetRecursion(String str) {
        return doRecursion("", str);
    }

    private static List<String> doRecursion(String p, String up) {
        if (up.isEmpty()) {
            List<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }

        // Taking the character
        char ch = up.charAt(0);
        // Adding the character in the recursion
        List<String> left = doRecursion(p + ch, up.substring(1));
        // Not adding the character in the recursion
        List<String> right = doRecursion(p, up.substring(1));

        left.addAll(right);

        return left;
    }
}
