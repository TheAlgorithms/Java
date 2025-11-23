package com.thealgorithms.strings;

import java.util.HashMap;

public class LongestSubstringKDistinct {

    /**
     * Returns the length of the longest substring that contains
     * at most k distinct characters.
     *
     * Sliding Window + HashMap
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */
    public static int longestSubstringKDistinct(String s, int k) {
        if (k == 0 || s == null || s.isEmpty()) {
            return 0;
        }

        int left = 0, maxLen = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);

                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
