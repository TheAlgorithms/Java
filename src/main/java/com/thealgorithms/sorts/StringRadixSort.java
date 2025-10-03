package com.thealgorithms.sorts;

import java.util.Arrays;

/**
 * String Radix Sort implementation using MSD (Most Significant Digit) approach.
 * 
 * This algorithm sorts an array of strings by examining characters from left to right.
 * It's particularly efficient for sorting strings with similar prefixes or when dealing
 * with large datasets of strings.
 * 
 * Key features:
 * - In-place sorting (when considering the auxiliary space proportional to input)
 * - Stable sorting algorithm
 * - Does not require comparison between strings
 * - Works with any character set (ASCII, Unicode, etc.)
 * 
 * Time Complexity: O(d * (n + k)) where:
 * - d = maximum length of strings
 * - n = number of strings
 * - k = size of character set (256 for extended ASCII)
 * 
 * Space Complexity: O(n + k) for the auxiliary arrays
 * 
 * @author TheAlgorithms Contributors
 * @see <a href="https://en.wikipedia.org/wiki/Radix_sort">Radix Sort</a>
 */
public final class StringRadixSort {
    
    /**
     * The radix (base) for the sorting algorithm.
     * Using 256 to cover extended ASCII character set.
     */
    private static final int RADIX = 256;
    
    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private StringRadixSort() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    
    /**
     * Sorts an array of strings using MSD Radix Sort algorithm.
     * 
     * @param strings the array of strings to be sorted
     * @return the sorted array of strings
     * @throws IllegalArgumentException if the input array is null
     * 
     * Example usage:
     * <pre>
     * String[] words = {"banana", "apple", "cherry", "date"};
     * String[] sorted = StringRadixSort.sort(words);
     * // Result: ["apple", "banana", "cherry", "date"]
     * </pre>
     */
    public static String[] sort(String[] strings) {
        if (strings == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        
        if (strings.length <= 1) {
            return Arrays.copyOf(strings, strings.length);
        }
        
        // Create a copy to avoid modifying the original array
        String[] sortedArray = Arrays.copyOf(strings, strings.length);
        String[] auxiliary = new String[strings.length];
        
        // Find the maximum length to determine the number of digits
        int maxLength = findMaxLength(sortedArray);
        
        // Sort from the most significant digit to the least significant digit
        msdSort(sortedArray, auxiliary, 0, strings.length - 1, 0, maxLength);
        
        return sortedArray;
    }
    
    /**
     * Recursively sorts the array using MSD approach.
     * 
     * @param strings the array to sort
     * @param auxiliary the auxiliary array for stable sorting
     * @param low the starting index of the subarray
     * @param high the ending index of the subarray
     * @param digit the current digit position (0 is the leftmost character)
     * @param maxLength the maximum length among all strings
     */
    private static void msdSort(String[] strings, String[] auxiliary, int low, int high, int digit, int maxLength) {
        // Base case: if subarray has <= 1 element or we've processed all digits
        if (low >= high || digit >= maxLength) {
            return;
        }
        
        // Count array to store frequency of each character
        int[] count = new int[RADIX + 2]; // +2 for handling end-of-string and offset
        
        // Count frequency of each character at the current digit position
        for (int i = low; i <= high; i++) {
            count[charAt(strings[i], digit) + 2]++;
        }
        
        // Transform counts to indices (cumulative sum)
        for (int r = 0; r < RADIX + 1; r++) {
            count[r + 1] += count[r];
        }
        
        // Distribute strings into auxiliary array based on current digit
        for (int i = low; i <= high; i++) {
            auxiliary[count[charAt(strings[i], digit) + 1]++] = strings[i];
        }
        
        // Copy back from auxiliary array to original array
        for (int i = low; i <= high; i++) {
            strings[i] = auxiliary[i - low];
        }
        
        // Recursively sort each character subarray
        for (int r = 0; r < RADIX; r++) {
            int subLow = low + count[r];
            int subHigh = low + count[r + 1] - 1;
            if (subLow < subHigh) {
                msdSort(strings, auxiliary, subLow, subHigh, digit + 1, maxLength);
            }
        }
    }
    
    /**
     * Gets the character at the specified position in the string.
     * Returns -1 if the position is beyond the string length (representing end-of-string).
     * 
     * @param str the input string
     * @param index the character position
     * @return the character at the given position, or -1 if beyond string length
     */
    private static int charAt(String str, int index) {
        if (index >= str.length()) {
            return -1; // Represents end-of-string, sorts before any character
        }
        return str.charAt(index);
    }
    
    /**
     * Finds the maximum length among all strings in the array.
     * 
     * @param strings the array of strings
     * @return the maximum length found
     */
    private static int findMaxLength(String[] strings) {
        int maxLength = 0;
        for (String str : strings) {
            if (str != null && str.length() > maxLength) {
                maxLength = str.length();
            }
        }
        return maxLength;
    }
    
    /**
     * Sorts an array of strings in-place using MSD Radix Sort algorithm.
     * This method modifies the original array.
     * 
     * @param strings the array of strings to be sorted in-place
     * @throws IllegalArgumentException if the input array is null
     */
    public static void sortInPlace(String[] strings) {
        if (strings == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        
        if (strings.length <= 1) {
            return;
        }
        
        String[] auxiliary = new String[strings.length];
        int maxLength = findMaxLength(strings);
        msdSort(strings, auxiliary, 0, strings.length - 1, 0, maxLength);
    }
}