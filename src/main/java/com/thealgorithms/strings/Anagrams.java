package com.thealgorithms.strings;

import java.util.Arrays;
import java.util.HashMap;

/**
 * An anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.[1]
 * For example, the word anagram itself can be rearranged into nag a ram,
 * also the word binary into brainy and the word adobe into abode.
 * Reference from https://en.wikipedia.org/wiki/Anagram
 */
public final class Anagrams {
    private Anagrams() {
    }

    /**
     * Checks if two strings are anagrams by sorting the characters and comparing them.
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     *
     * @param s the first string
     * @param t the second string
     * @return true if the strings are anagrams, false otherwise
     */
    public static boolean areAnagramsBySorting(String s, String t) {
        s = s.toLowerCase().replaceAll("[^a-z]", "");
        t = t.toLowerCase().replaceAll("[^a-z]", "");
        if (s.length() != t.length()) {
            return false;
        }
        char[] c = s.toCharArray();
        char[] d = t.toCharArray();
        Arrays.sort(c);
        Arrays.sort(d);
        return Arrays.equals(c, d);
    }

    /**
     * Checks if two strings are anagrams by counting the frequency of each character.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param s the first string
     * @param t the second string
     * @return true if the strings are anagrams, false otherwise
     */
    public static boolean areAnagramsByCountingChars(String s, String t) {
        s = s.toLowerCase().replaceAll("[^a-z]", "");
        t = t.toLowerCase().replaceAll("[^a-z]", "");
        int[] dict = new int[128];
        for (char ch : s.toCharArray()) {
            dict[ch]++;
        }
        for (char ch : t.toCharArray()) {
            dict[ch]--;
        }
        for (int e : dict) {
            if (e != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if two strings are anagrams by counting the frequency of each character
     * using a single array.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param s the first string
     * @param t the second string
     * @return true if the strings are anagrams, false otherwise
     */
    public static boolean areAnagramsByCountingCharsSingleArray(String s, String t) {
        s = s.toLowerCase().replaceAll("[^a-z]", "");
        t = t.toLowerCase().replaceAll("[^a-z]", "");
        if (s.length() != t.length()) {
            return false;
        }
        int[] charCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i) - 'a']++;
            charCount[t.charAt(i) - 'a']--;
        }
        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if two strings are anagrams using a HashMap to store character frequencies.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param s the first string
     * @param t the second string
     * @return true if the strings are anagrams, false otherwise
     */
    public static boolean areAnagramsUsingHashMap(String s, String t) {
        s = s.toLowerCase().replaceAll("[^a-z]", "");
        t = t.toLowerCase().replaceAll("[^a-z]", "");
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> charCountMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            if (!charCountMap.containsKey(c) || charCountMap.get(c) == 0) {
                return false;
            }
            charCountMap.put(c, charCountMap.get(c) - 1);
        }
        return charCountMap.values().stream().allMatch(count -> count == 0);
    }

    /**
     * Checks if two strings are anagrams using an array to track character frequencies.
     * This approach optimizes space complexity by using only one array.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param s the first string
     * @param t the second string
     * @return true if the strings are anagrams, false otherwise
     */
    public static boolean areAnagramsBySingleFreqArray(String s, String t) {
        s = s.toLowerCase().replaceAll("[^a-z]", "");
        t = t.toLowerCase().replaceAll("[^a-z]", "");
        if (s.length() != t.length()) {
            return false;
        }
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }
        for (int count : freq) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
