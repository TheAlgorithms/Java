package com.thealgorithms.strings;

import java.util.Arrays;

/**
 * Suffix Array Implementation.
 *
 * This class constructs a Suffix Array for a given string.
 *
 */
public class SuffixArray {
    private String text;
    private int[] suffixArray;

    public SuffixArray(String text) {
        this.text = text;
        this.suffixArray = buildSuffixArray();
    }

    private int[] buildSuffixArray() {
        int n = text.length();
        Integer[] suffixIndices = new Integer[n];

        // Initialize suffix indices
        for (int i = 0; i < n; i++) {
            suffixIndices[i] = i;
        }

        // Sort the suffix indices based on the suffixes
        Arrays.sort(suffixIndices, (a, b) -> text.substring(a).compareTo(text.substring(b)));

        // Convert Integer[] to int[]
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = suffixIndices[i];
        }

        return result;
    }

    public int[] getSuffixArray() {
        return suffixArray;
    }

    public static void main(String[] args) {
        String text = "banana";
        SuffixArray suffixArray = new SuffixArray(text);
        System.out.println("Suffix Array: " + Arrays.toString(suffixArray.getSuffixArray()));
    }
}
