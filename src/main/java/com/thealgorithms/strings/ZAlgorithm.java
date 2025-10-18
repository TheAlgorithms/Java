package com.thealgorithms.strings;

/**
 * Z Algorithm for Pattern Matching
 * 
 * The Z algorithm finds all occurrences of a pattern in a text in linear time.
 * For a string S of length n, the Z array stores the length of the longest substring
 * starting from S[i] which is also a prefix of S.
 * 
 * Time Complexity: O(n + m) where n is text length and m is pattern length
 * Space Complexity: O(n + m)
 * 
 * Applications:
 * - Pattern matching
 * - String searching
 * - Preprocessing for other string algorithms
 * 
 * @author YourName
 */
public final class ZAlgorithm {
    
    private ZAlgorithm() {
        // Utility class
    }
    
    /**
     * Computes the Z array for given string
     * Z[i] = length of longest substring starting from i which is also prefix of string
     * 
     * @param str input string
     * @return Z array
     */
    public static int[] computeZArray(String str) {
        if (str == null || str.isEmpty()) {
            return new int[0];
        }
        
        int n = str.length();
        int[] z = new int[n];
        z[0] = n; // First element is always equal to string length
        
        int left = 0;
        int right = 0;
        
        for (int i = 1; i < n; i++) {
            if (i > right) {
                // Outside the current Z-box, compute from scratch
                left = right = i;
                while (right < n && str.charAt(right - left) == str.charAt(right)) {
                    right++;
                }
                z[i] = right - left;
                right--;
            } else {
                // Inside the Z-box, use previously computed values
                int k = i - left;
                if (z[k] < right - i + 1) {
                    z[i] = z[k];
                } else {
                    left = i;
                    while (right < n && str.charAt(right - left) == str.charAt(right)) {
                        right++;
                    }
                    z[i] = right - left;
                    right--;
                }
            }
        }
        
        return z;
    }
    
    /**
     * Searches for all occurrences of pattern in text using Z algorithm
     * 
     * @param text the text to search in
     * @param pattern the pattern to search for
     * @return array of starting indices where pattern is found
     */
    public static int[] search(String text, String pattern) {
        if (text == null || pattern == null || pattern.isEmpty() || text.length() < pattern.length()) {
            return new int[0];
        }
        
        // Concatenate pattern and text with a separator
        String concat = pattern + "$" + text;
        int[] z = computeZArray(concat);
        
        int patternLen = pattern.length();
        java.util.List<Integer> results = new java.util.ArrayList<>();
        
        // Find positions where Z value equals pattern length
        for (int i = patternLen + 1; i < z.length; i++) {
            if (z[i] == patternLen) {
                results.add(i - patternLen - 1);
            }
        }
        
        return results.stream().mapToInt(Integer::intValue).toArray();
    }
    
    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        // Test Z array computation
        String str = "aabcaabxaaaz";
        int[] z = computeZArray(str);
        System.out.println("Z array for '" + str + "':");
        for (int i = 0; i < z.length; i++) {
            System.out.print(z[i] + " ");
        }
        System.out.println("\n");
        
        // Test pattern matching
        String text = "ababcabcabababd";
        String pattern = "ababd";
        int[] matches = search(text, pattern);
        
        System.out.println("Searching for '" + pattern + "' in '" + text + "'");
        System.out.println("Pattern found at indices: ");
        for (int index : matches) {
            System.out.print(index + " ");
        }
    }
}