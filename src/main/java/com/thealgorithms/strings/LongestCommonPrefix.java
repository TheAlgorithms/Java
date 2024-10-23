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
package com.thealgorithms.strings;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;

public final class LongestCommonPrefix {
    // Private constructor to prevent instantiation of utility class
    private LongestCommonPrefix() {
    }
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
    // JUnit Test cases
    @Test
    public void testLongestPrefix() {
        // Test case 1
        String[] input1 = { "flower", "flow", "flight" };
        assertEquals("fl", longestPrefix(input1));

        // Test case 2
        String[] input2 = { "dog", "racecar", "car" };
        assertEquals("", longestPrefix(input2));

        // Test case 3
        String[] input3 = {};
        assertEquals("", longestPrefix(input3));

        // Test case 4
        String[] input4 = { "alone" };
        assertEquals("alone", longestPrefix(input4));

        // Test case 5
        String[] input5 = { "same", "same", "same" };
        assertEquals("same", longestPrefix(input5));

        // Test case 6
        String[] input6 = { "", "", "" };
        assertEquals("", longestPrefix(input6));
    }
}
/* 
Time and Space Complexity:
Time Complexity: O(n log n + m)
- Sorting the array takes O(n log n), where n is the number of strings.
- Comparing the first and last string takes O(m), where m is the length of the shortest string.
- Overall, the time complexity is O(n log n + m).

Space Complexity: O(n)
- Sorting requires O(n) space for the array.
- The space complexity for storing the prefix result is O(1), since it depends on the length of the prefix, which is part of the input.
Therefore, the space complexity is O(n).
*/
