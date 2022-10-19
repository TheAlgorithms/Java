package com.thealgorithms.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Two strings are anagrams if they are made of the same letters arranged
 * differently (ignoring the case).
 */

/**
 * Time Complexity is O(n)
 * Space Complexity is O(1)
 */
public class CheckAnagrams {

    public static void main(String[] args) {
        assert isAnagrams("Silent", "Listen");
        assert isAnagrams("This is a string", "Is this a string");
        assert !isAnagrams("There", "Their");
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
        long count=0;
        for (int i = 0; i < l1; i++) {
            count += s1.charAt(i);
        }

        for (int i = 0; i < l2; i++) {
            count -= s2.charAt(i);
        }

        if(count == 0)
            return true;
        else
            return false;
    }
}
