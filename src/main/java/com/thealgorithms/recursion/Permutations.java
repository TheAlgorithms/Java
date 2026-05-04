package com.thealgorithms.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Permutations class provides utility methods to generate all possible 
 * rearrangements of elements for both integer arrays and strings.
 * 
 * <p>This implementation uses a recursive approach and a {@link HashSet} 
 * internally to ensure that only unique permutations are returned.</p>
 */
public final class Permutations {

    private Permutations() {
        throw new UnsupportedOperationException("Utility Class");
    }

    /**
     * Generates all unique permutations of an integer array.
     *
     * @param nums The array of integers to permute.
     * @return A List of Lists, where each inner list is a unique permutation.
     * @throws NullPointerException if nums is null.
     */
    public static List<List<Integer>> permutation(int[] nums) {
        if (nums == null) throw new NullPointerException("Input array cannot be null");
        
        List<Integer> up = Arrays.stream(nums).boxed().collect(Collectors.toList());
        HashSet<List<Integer>> set = generatePermutations(new ArrayList<>(), up);
        return new ArrayList<>(set);
    }

    /**
     * Internal recursive helper to build permutations for integer lists.
     */
    private static HashSet<List<Integer>> generatePermutations(List<Integer> p, List<Integer> up) {
        HashSet<List<Integer>> result = new HashSet<>();
        if (up.isEmpty()) {
            result.add(new ArrayList<>(p));
            return result;
        }

        Integer num = up.get(0);
        List<Integer> remaining = up.subList(1, up.size());

        for (int i = 0; i <= p.size(); i++) {
            List<Integer> nextP = new ArrayList<>(p);
            nextP.add(i, num); // Insert num at every possible position
            result.addAll(generatePermutations(nextP, remaining));
        }

        return result;
    }

    /**
     * Generates all possible permutations of a given string.
     *
     * @param s The string to permute.
     * @return A List containing all permutations of the input string.
     * @throws NullPointerException if s is null.
     */
    public static List<String> permutation(String s) {
        if (s == null) throw new NullPointerException("Input string cannot be null");
        return generateStringPermutations("", s);
    }

    /**
     * Internal recursive helper to build permutations for strings.
     */
    private static List<String> generateStringPermutations(String p, String up) {
        List<String> list = new ArrayList<>();
        if (up.isEmpty()) {
            list.add(p);
            return list;
        }

        char ch = up.charAt(0);
        String remaining = up.substring(1);

        for (int i = 0; i <= p.length(); i++) {
            String first = p.substring(0, i);
            String second = p.substring(i);
            list.addAll(generateStringPermutations(first + ch + second, remaining));
        }
        return list;
    }
}
