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
public class Anagrams {

    // 4 approaches are provided for anagram checking. approach 2 and approach 3 are similar but
    // differ in running time.
    public static void main(String[] args) {
        String first = "deal";
        String second = "lead";
        // All the below methods takes input but doesn't return any output to the main method.
        Anagrams nm = new Anagrams();
        System.out.println(nm.approach2(first, second)); /* To activate methods for different approaches*/
        System.out.println(nm.approach1(first, second)); /* To activate methods for different approaches*/
        System.out.println(nm.approach3(first, second)); /* To activate methods for different approaches*/
        System.out.println(nm.approach4(first, second)); /* To activate methods for different approaches*/
        /**
         * OUTPUT :
         * first string ="deal" second string ="lead"
         * Output: Anagram
         * Input and output is constant for all four approaches
         * 1st approach Time Complexity : O(n logn)
         * Auxiliary Space Complexity : O(1)
         * 2nd approach Time Complexity : O(n)
         * Auxiliary Space Complexity : O(1)
         * 3rd approach Time Complexity : O(n)
         * Auxiliary Space Complexity : O(1)
         * 4th approach Time Complexity : O(n)
         * Auxiliary Space Complexity : O(n)
         * 5th approach Time Complexity: O(n)
         * Auxiliary Space Complexity: O(1)
         */
    }

    boolean approach1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        } else {
            char[] c = s.toCharArray();
            char[] d = t.toCharArray();
            Arrays.sort(c);
            Arrays.sort(d); /* In this approach the strings are stored in the character arrays and
                               both the arrays are sorted. After that both the arrays are compared
                               for checking anangram */

            return Arrays.equals(c, d);
        }
    }

    boolean approach2(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        } else {
            int[] m = new int[26];
            int[] n = new int[26];
            for (char c : a.toCharArray()) {
                m[c - 'a']++;
            }
            // In this approach the frequency of both the strings are stored and after that the
            // frequencies are iterated from 0 to 26(from 'a' to 'z' ). If the frequencies match
            // then anagram message is displayed in the form of boolean format Running time and
            // space complexity of this algo is less as compared to others
            for (char c : b.toCharArray()) {
                n[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                if (m[i] != n[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    boolean approach3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        // this is similar to approach number 2 but here the string is not converted to character
        // array
        else {
            int[] a = new int[26];
            int[] b = new int[26];
            int k = s.length();
            for (int i = 0; i < k; i++) {
                a[s.charAt(i) - 'a']++;
                b[t.charAt(i) - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                if (a[i] != b[i]) return false;
            }
            return true;
        }
    }

    boolean approach4(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        // This approach is done using hashmap where frequencies are stored and checked iteratively
        // and if all the frequencies of first string match with the second string then anagram
        // message is displayed in boolean format
        else {
            HashMap<Character, Integer> nm = new HashMap<>();
            HashMap<Character, Integer> kk = new HashMap<>();
            for (char c : s.toCharArray()) {
                nm.put(c, nm.getOrDefault(c, 0) + 1);
            }
            for (char c : t.toCharArray()) {
                kk.put(c, kk.getOrDefault(c, 0) + 1);
            }
            // It checks for equal frequencies by comparing key-value pairs of two hashmaps
            return nm.equals(kk);
        }
    }

    boolean approach5(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        // Approach is different from above 4 aproaches.
        // Here we initialize an array of size 26 where each element corresponds to the frequency of
        // a character.
        int[] freq = new int[26];
        // iterate through both strings, incrementing the frequency of each character in the first
        // string and decrementing the frequency of each character in the second string.
        for (int i = 0; i < s.length(); i++) {
            int pos1 = s.charAt(i) - 'a';
            int pos2 = s.charAt(i) - 'a';
            freq[pos1]++;
            freq[pos2]--;
        }
        // iterate through the frequency array and check if all the elements are zero, if so return
        // true else false
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
