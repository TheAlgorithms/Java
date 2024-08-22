package com.thealgorithms.strings;

import java.util.HashMap;
import java.util.Map;

final class LongestNonRepetitiveSubstring {
    private LongestNonRepetitiveSubstring() {
    }

    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        int start = 0;
        int i = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (i < s.length()) {
            char temp = s.charAt(i);

            // adding key to map if not present
            if (!map.containsKey(temp)) {
                map.put(temp, 0);
            } else if (s.charAt(start) == temp) {
                start++;
            } else if (s.charAt(i - 1) == temp) {
                if (max < map.size()) {
                    max = map.size();
                }
                map = new HashMap<>();
                start = i;
                i--;
            } else {
                if (max < map.size()) {
                    max = map.size();
                }
                while (s.charAt(start) != temp) {
                    map.remove(s.charAt(start));
                    start++;
                }
                start++;
            }

            i++;
        }
        if (max < map.size()) {
            max = map.size();
        }
        return max;
    }
}
