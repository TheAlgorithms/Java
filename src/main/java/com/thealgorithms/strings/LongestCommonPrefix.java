package com.thealgorithms.strings;
import java.util.*;
// To find the longest Common Prefix of String array
// geeksforgeeks explaination: https://www.geeksforgeeks.org/longest-common-prefix-using-sorting/
/* The Longest Common Prefix (LCP) of a set of strings is the longest substring that appears at the beginning of each of the strings in the set. For example, given the strings:
"flower"
"flow"
"flight"
The longest common prefix is "fl", as it is the longest substring that is common at the start of all three strings. 
Approach:-
Sort the Array: Sort the array of strings to bring strings with common prefixes adjacent to each other.
Identify Extremes: Select the first and last strings from the sorted array for comparison, as they will have the longest common prefix.
Character Comparison: Compare the characters of the first and last strings until a mismatch is found, tracking the index of the last matching character.
Return Prefix: Return the substring of the first string from the start to the index of the last matching character, which represents the longest common prefix.
*/
public class LongestCommonPrefix {

    // Method to find the longest common prefix
    public static String longestPrefix(String[] str) {
        int n = str.length;
        if (n == 0) {
            return "";
        }

        // Sort the array to bring similar prefixes closer
        Arrays.sort(str);

        // Compare the first and last strings after sorting
        String first = str[0];
        String last = str[n - 1];
        int len = Math.min(first.length(), last.length());

        // Find the longest common prefix
        int i;
        for (i = 0; i < len; i++) {
            if (first.charAt(i) != last.charAt(i)) {
                break;
            }
        }

        return first.substring(0, i);
    }

    // Main method to run test cases
    public static void main(String[] args) {
        // Test Case 1: Normal input
        String[] input1 = {"flower", "flow", "flight"};
        System.out.println("Test Case 1: " + (longestPrefix(input1).equals("fl") ? "Passed" : "Failed"));

        // Test Case 2: No common prefix
        String[] input2 = {"dog", "racecar", "car"};
        System.out.println("Test Case 2: " + (longestPrefix(input2).equals("") ? "Passed" : "Failed"));

        // Test Case 3: Empty array
        String[] input3 = {};
        System.out.println("Test Case 3: " + (longestPrefix(input3).equals("") ? "Passed" : "Failed"));

        // Test Case 4: Single element
        String[] input4 = {"alone"};
        System.out.println("Test Case 4: " + (longestPrefix(input4).equals("alone") ? "Passed" : "Failed"));

        // Test Case 5: Identical strings
        String[] input5 = {"same", "same", "same"};
        System.out.println("Test Case 5: " + (longestPrefix(input5).equals("same") ? "Passed" : "Failed"));

        // Test Case 6: Empty strings
        String[] input6 = {"", "", ""};
        System.out.println("Test Case 6: " + (longestPrefix(input6).equals("") ? "Passed" : "Failed"));
    }
}
/* 
Time and Space Complexity:
Time Complexity:O(n log n + m)

Sorting the array takes 𝑂(𝑛 log 𝑛)
O(nlogn), where n is the number of strings.
Comparing the first and last string takes 𝑂(𝑚)
O(m), where m is the length of the shortest string.
Overall, the time complexity is 
𝑂(log 𝑛 + 𝑚 )


Space Complexity:O(n)

Sorting requires 𝑂(𝑛)
O(n) space for the array.
The space complexity for storing the prefix result is 
𝑂(1)
O(1) since it depends on the length of the prefix, which is part of the input.
Therefore, the space complexity is 𝑂(𝑛)
*/