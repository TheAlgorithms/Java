package com.thealgorithms.strings;

import java.util.HashMap;

class longestNonRepeativeSubstring {

    public static int lengthOfLongestSubstring(String s) {
        int max = 0, start = 0, i = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        while (i < s.length()) {
            char temp = s.charAt(i);

            // adding key to map if not present
            if (!map.containsKey(temp)) map.put(temp, 0);
            // checking if the first value is the dublicate value
            else if (s.charAt(start) == temp)
                start++;
            // checking if the previous value is dublicate value
            else if (s.charAt(i - 1) == temp) {
                if (max < map.size()) max = map.size();
                map = new HashMap<>();
                start = i;
                i--;
            }
            // last possible place where dublicate value can be is between start and i
            else {
                if (max < map.size()) max = map.size();
                while (s.charAt(start) != temp) {
                    map.remove(s.charAt(start));
                    start++;
                }
                start++;
            }

            i++;
        }
        if (max < map.size()) max = map.size();
        return max;
    }
}
