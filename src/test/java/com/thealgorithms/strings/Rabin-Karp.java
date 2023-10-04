package com.thealgorithms.strings;

import java.util.ArrayList;
import java.util.List;

/*
The Rabin-Karp algorithm is a string searching algorithm that efficiently finds all occurrences of a given substring within a longer string
The algorithm relies on a hash function that converts substrings of the text and the pattern into numerical hash values
The algorithm compares the hash value of the pattern to the hash values of substrings in the text. If the hash values match, it performs an additional character-by-character comparison to confirm a match.
Rabin-Karp slides a window of the pattern's length over the text, computing the hash value for each window. It compares this hash value with the hash value of the pattern. If they match, it performs a character-by-character comparison to confirm a match.

Time Complexity : O(n * m)
where n is the length of the text, and m is the length of the pattern.
*/

public class RabinKarp {
    private static final int PRIME = 101; // A prime number for the hash function

    // Function to search for occurrences of the pattern in the text
    public static List<Integer> search(String text, String pattern) {
        List<Integer> occurrences = new ArrayList<>();
        int m = pattern.length(); // Length of the pattern
        int n = text.length();   // Length of the text
        long patternHash = hash(pattern, m); // Compute the hash value of the pattern
        long textHash = hash(text, m);       // Compute the hash value of the initial substring of text

        for (int i = 0; i <= n - m; i++) {
            // If the hash values match and characters match, check for an exact match
            if (patternHash == textHash && checkEquals(text, pattern, i)) {
                occurrences.add(i); // Found a match, add the starting position to the list
            }
            if (i < n - m) {
                textHash = recalculateHash(text, i, m, textHash); // Calculate hash for the next substring
            }
        }

        return occurrences; // Return the list of starting positions where the pattern is found
    }

    // Function to compute the hash value of a string of a given length
    private static long hash(String str, int length) {
        long hashValue = 0;
        for (int i = 0; i < length; i++) {
            hashValue += str.charAt(i) * Math.pow(PRIME, i);
        }
        return hashValue;
    }

    // Function to check if characters in two strings are equal
    private static boolean checkEquals(String text, String pattern, int startIndex) {
        for (int i = 0; i < pattern.length(); i++) {
            if (text.charAt(startIndex + i) != pattern.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Function to recalculate the hash value for the next substring
    private static long recalculateHash(String text, int oldIndex, int m, long oldHash) {
        long newHash = oldHash - text.charAt(oldIndex); // Remove the contribution of the old character
        newHash /= PRIME; // Divide by the prime number
        newHash += text.charAt(oldIndex + m) * Math.pow(PRIME, m - 1); // Add the new character's contribution
        return newHash;
    }

    public static void main(String[] args) {
        String text = "AABAACAADAABAABA";
        String pattern = "AABA";
        List<Integer> occurrences = search(text, pattern);
        
        if (occurrences.isEmpty()) {
            System.out.println("Pattern not found in the text.");
        } else {
            System.out.println("Pattern found at positions: " + occurrences);
        }
    }
}


