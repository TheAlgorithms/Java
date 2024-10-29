package com.thealgorithms.strings;

import java.util.Arrays;

public final class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        Arrays.sort(strs);
        String shortest = strs[0];
        String longest = strs[strs.length - 1];

        int index = 0;
        while (index < shortest.length() && index < longest.length() && shortest.charAt(index) == longest.charAt(index)) {
            index++;
        }

        return shortest.substring(0, index);
    }
}
