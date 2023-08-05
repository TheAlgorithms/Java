package com.thealgorithms.dynamicprogramming;

import java.util.Scanner;

/**
 * @file @brief Implements [Palindrome
 * Partitioning](https://leetcode.com/problems/palindrome-partitioning-ii/)
 * algorithm, giving you the minimum number of partitions you need to make
 *
 * @details palindrome partitioning uses dynamic programming and goes to all the
 * possible partitions to find the minimum you are given a string and you need
 * to give minimum number of partitions needed to divide it into a number of
 * palindromes [Palindrome Partitioning]
 * (https://www.geeksforgeeks.org/palindrome-partitioning-dp-17/) overall time
 * complexity O(n^2) For example: example 1:- String : "nitik" Output : 2 => "n
 * | iti | k" For example: example 2:- String : "ababbbabbababa" Output : 3 =>
 * "aba | b | bbabb | ababa"
 * @author [Syed] (https://github.com/roeticvampire)
 */
public class PalindromicPartitioning {

    public static int minimalpartitions(String word) {
        int len = word.length();
        /* We Make two arrays to create a bottom-up solution.
           minCuts[i] = Minimum number of cuts needed for palindrome partitioning of substring
           word[0..i] isPalindrome[i][j] = true if substring str[i..j] is palindrome Base Condition:
           C[i] is 0 if P[0][i]= true
         */
        int[] minCuts = new int[len];
        boolean[][] isPalindrome = new boolean[len][len];

        int i, j, L; // different looping variables

        // Every substring of length 1 is a palindrome
        for (i = 0; i < len; i++) {
            isPalindrome[i][i] = true;
        }

        /* L is substring length. Build the solution in bottom up manner by considering all
         * substrings of length starting from 2 to n. */
        for (L = 2; L <= len; L++) {
            // For substring of length L, set different possible starting indexes
            for (i = 0; i < len - L + 1; i++) {
                j = i + L - 1; // Ending index
                // If L is 2, then we just need to
                // compare two characters. Else need to
                // check two corner characters and value
                // of P[i+1][j-1]
                if (L == 2) {
                    isPalindrome[i][j] = (word.charAt(i) == word.charAt(j));
                } else {
                    isPalindrome[i][j] = (word.charAt(i) == word.charAt(j)) && isPalindrome[i + 1][j - 1];
                }
            }
        }

        // We find the minimum for each index
        for (i = 0; i < len; i++) {
            if (isPalindrome[0][i]) {
                minCuts[i] = 0;
            } else {
                minCuts[i] = Integer.MAX_VALUE;
                for (j = 0; j < i; j++) {
                    if (isPalindrome[j + 1][i] && 1 + minCuts[j] < minCuts[i]) {
                        minCuts[i] = 1 + minCuts[j];
                    }
                }
            }
        }

        // Return the min cut value for complete
        // string. i.e., str[0..n-1]
        return minCuts[len - 1];
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String word;
        System.out.println("Enter the First String");
        word = input.nextLine();
        // ans stores the final minimal cut count needed for partitioning
        int ans = minimalpartitions(word);
        System.out.println("The minimum cuts needed to partition \"" + word + "\" into palindromes is " + ans);
        input.close();
    }
}
