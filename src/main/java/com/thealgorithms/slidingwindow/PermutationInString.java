package com.thealgorithms.slidingwindow;


/**
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * In other words, return true if one of s1's permutations is the substring of s2.
 * 
 * Example:
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba"). 
 * 
 * Best Case Time Complexity: O(n)
 * Worst Case Time Complexity: O(m)
 * Space Complexity: O(1)
 * 
 * @author m-saurabh01
 *
 */

class PermutationInString {  
	
    public boolean checkInclusion(String s1, String s2) {  
        // Get the length of s1 and s2  
        int n = s1.length();  
        int m = s2.length();  

        // Create frequency arrays for characters in s1 and the current window in s2  
        int[] freq1 = new int[26]; // Frequency count for s1  
        int[] freq2 = new int[26]; // Frequency count for current substring of s2  

        // Fill the frequency array for s1  
        for (char c : s1.toCharArray()) {  
            freq1[c - 'a']++; 
        }  

        // Slide the window of size n across s2  
        for (int i = 0; i < m; i++) {  
            // Add current character to the frequency array of the window  
            freq2[s2.charAt(i) - 'a']++;  

            // Check if the window size exceeds s1's length  
            if (i >= n) {  
                // Remove the character that is no longer in the window  
                freq2[s2.charAt(i - n) - 'a']--;  
            }  

            // If the frequency arrays match, return true  
            if (i >= n - 1) { // Only check if we've processed enough characters  
                boolean match = true; // Assume they match  
                for (int j = 0; j < 26; j++) {  
                    if (freq1[j] != freq2[j]) { // If there's a mismatch  
                        match = false; // Set match to false  
                        break; // No need to check further  
                    }  
                }  
                if (match) return true; // If we found a match, return true  
            }  
        }  

        return false; // No permutation of s1 found in s2  
    }  
}