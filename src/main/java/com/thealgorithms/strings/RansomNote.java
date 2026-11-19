package com.thealgorithms.strings;

/**
 * Determines if a ransom note can be constructed using letters from a magazine.
 * Each letter in the magazine can only be used once.
 *
 * <p>Example: ransomNote = "aa", magazine = "aab" -> true
 *
 * <p>Time complexity: O(n), Space complexity: O(1) (fixed 26-char alphabet)
 *
 * @see <a href="https://leetcode.com/problems/ransom-note/">LeetCode #383</a>
 */
public final class RansomNote {
    private RansomNote() {
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];
        for (char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (--count[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
