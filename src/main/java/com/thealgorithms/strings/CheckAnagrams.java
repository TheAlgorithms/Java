package com.thealgorithms.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Two strings are anagrams if they are made of the same letters arranged
 * differently (ignoring the case).
 */
public final class CheckAnagrams {
    private CheckAnagrams() {
    }
    /**
     * Check if two strings are anagrams or not
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return {@code true} if two string are anagrams, otherwise {@code false}
     */
    public static boolean isAnagrams(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        Map<Character, Integer> charAppearances = new HashMap<>();

        for (int i = 0; i < l1; i++) {
            char c = s1.charAt(i);
            int numOfAppearances = charAppearances.getOrDefault(c, 0);
            charAppearances.put(c, numOfAppearances + 1);
        }

        for (int i = 0; i < l2; i++) {
            char c = s2.charAt(i);
            if (!charAppearances.containsKey(c)) {
                return false;
            }
            charAppearances.put(c, charAppearances.get(c) - 1);
        }

        for (int cnt : charAppearances.values()) {
            if (cnt != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * If given strings contain Unicode symbols.
     * The first 128 ASCII codes are identical to Unicode.
     * This algorithm is case-sensitive.
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return true if two string are anagrams, otherwise false
     */
    public static boolean isAnagramsUnicode(String s1, String s2) {
        int[] dict = new int[128];
        for (char ch : s1.toCharArray()) {
            dict[ch]++;
        }
        for (char ch : s2.toCharArray()) {
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
     * If given strings contain only lowercase English letters.
     * <p>
     * The main "trick":
     * To map each character from the first string 's1' we need to subtract an integer value of 'a' character
     * as 'dict' array starts with 'a' character.
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return true if two string are anagrams, otherwise false
     */
    public static boolean isAnagramsOptimised(String s1, String s2) {
        // 26 - English alphabet length
        int[] dict = new int[26];
        for (char ch : s1.toCharArray()) {
            checkLetter(ch);
            dict[ch - 'a']++;
        }
        for (char ch : s2.toCharArray()) {
            checkLetter(ch);
            dict[ch - 'a']--;
        }
        for (int e : dict) {
            if (e != 0) {
                return false;
            }
        }
        return true;
    }

    private static void checkLetter(char ch) {
        int index = ch - 'a';
        if (index < 0 || index >= 26) {
            throw new IllegalArgumentException("Strings must contain only lowercase English letters!");
        }
    }
}
