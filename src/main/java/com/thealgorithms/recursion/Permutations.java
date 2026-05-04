package com.thealgorithms.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods to generate all permutations
 * of a given integer array or string using recursion.
 *
 * Reference:
 * https://en.wikipedia.org/wiki/Permutation
 */
public final class Permutations {

    private Permutations() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Generates all permutations of an integer array.
     *
     * @param nums the input array
     * @return list of all permutations
     * @throws NullPointerException if nums is null
     */
    public static List<List<Integer>> permutations(int[] nums) {
        if (nums == null) {
            throw new NullPointerException("Input array cannot be null");
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {
            list.add(num);
        }

        generateIntegerPermutations(0, list, result);
        return result;
    }

    private static void generateIntegerPermutations(int index, List<Integer> list, List<List<Integer>> result) {
        if (index == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < list.size(); i++) {
            swap(list, index, i);
            generateIntegerPermutations(index + 1, list, result);
            swap(list, index, i); // backtrack
        }
    }

    private static void swap(List<Integer> list, int i, int j) {
        Integer temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * Generates all permutations of a string.
     *
     * @param s the input string
     * @return list of all permutations
     * @throws NullPointerException if s is null
     */
    public static List<String> permutations(String s) {
        if (s == null) {
            throw new NullPointerException("Input string cannot be null");
        }

        List<String> result = new ArrayList<>();
        generateStringPermutations("", s, result);
        return result;
    }

    private static void generateStringPermutations(String prefix, String remaining, List<String> result) {
        if (remaining.isEmpty()) {
            result.add(prefix);
            return;
        }

        for (int i = 0; i < remaining.length(); i++) {
            char ch = remaining.charAt(i);
            String next = remaining.substring(0, i) + remaining.substring(i + 1);
            generateStringPermutations(prefix + ch, next, result);
        }
    }
}
