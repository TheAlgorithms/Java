package strings;

import java.util.*;

/**
 * Length of the longest substring with no repeating charaters
 * 
 */
public class LongestStringWithNoRepeatingCharacters{
    public static void main(String[] args) {
        assert lengthOfLongestSubstring("abcabcbb") == 3;
        assert lengthOfLongestSubstring("pwwkew") == 3;
    }

    /**
     * return length of longest substring with no repeating characters
     *
     * @param s is the string whose longest substring with no repeating characters
     * is to be found.
     * @return length of longest substring with no repeating characters.
     */
     public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}