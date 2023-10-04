package com.thealgorithms.strings;

import java.util.Arrays;

//Easiest way of finding longest comman prefix in an array of string
//First sort the array by Arrays.sort() function
//Now, take first and last strings in sorted array in variable name s1 and s2
//Declare a varible j=0 for keeping index of string traversal
//Now, traverse through both strings and check if character at index "j" in s1 and s2 are not equal, break the loop
//After breaking "j" has the index of string for which they have comman prefix
//At last return substring starting from 0th index to j-1 index
//By substring method for string
public class LongestCommanPrefix {
    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        assert findLongestCommanPrefix(strs).equals("fl");
    }
    public static String findLongestCommanPrefix(String[] strs) {
        Arrays.sort(strs);
        String s1 = strs[0];
        String s2 = strs[strs.length-1];
        int j=0;
        while(j < s1.length() && j<s2.length()) {
            if(s1.charAt(j) != s2.charAt(j)) break;
            j++;
        }
        return s1.substring(0,j);
    }
}
