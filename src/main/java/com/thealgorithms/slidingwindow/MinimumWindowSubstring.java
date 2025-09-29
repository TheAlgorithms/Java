package com.thealgorithms.slidingwindow;
import java.util.HashMap;
/**
 * The Longest Subarray with Sum Less Than or Equal to k algorithm finds the length
 * of the longest subarray whose sum is less than or equal to a given value k.
 *
 * <p>
 * Worst-case performance O(n)
 * Best-case performance O(n)
 * Average performance O(n)
 * Worst-case space complexity O(1)
 *
 * @author https://github.com/Chiefpatwal
 */
public final class MinimumWindowSubstring {
    // Prevent instantiation
    private MinimumWindowSubstring() {
    }

    /**
     * This method finds the minimum sum of a subarray of a given size k.
     *
     * @param arr is the input array where the minimum sum needs to be found
     * @param k   is the size of the subarray
     * @return the minimum sum of the subarray of size k
     */
 public static String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        HashMap<Character, Integer> tFreq = new HashMap<>();
        for (char c : t.toCharArray()) {
            tFreq.put(c, tFreq.getOrDefault(c, 0) + 1);
        }

        HashMap<Character, Integer> windowFreq = new HashMap<>();
        int left = 0, right = 0, minLen = Integer.MAX_VALUE, count = 0;
        String result = "";

        while (right < s.length()) {
            char c = s.charAt(right);
            windowFreq.put(c, windowFreq.getOrDefault(c, 0) + 1);

            if (tFreq.containsKey(c) && windowFreq.get(c).intValue() <= tFreq.get(c).intValue()) {
                count++;
            }

            while (count == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    result = s.substring(left, right + 1);
                }
                
                char leftChar = s.charAt(left);
                windowFreq.put(leftChar, windowFreq.get(leftChar) - 1);
                
                if (tFreq.containsKey(leftChar) && windowFreq.get(leftChar) < tFreq.get(leftChar)) {
                    count--;
                }
                
                left++;
            }
            right++;
        }
        return result;
    }
}
