package com.string;

import java.util.Arrays;

public class CheckAnagrams {

    /**
     * Check if two strings are anagrams or not
     *
     * @param s1 the first string
     * @param s2 the second string
     * @return {@code true} if two string are anagrams, otherwise {@code false}
     */
    public static boolean isAnagrams(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        char[] values1 = s1.toCharArray();
        char[] values2 = s2.toCharArray();
        Arrays.sort(values1);
        Arrays.sort(values2);
        return new String(values1).equals(new String(values2));
    }
}
