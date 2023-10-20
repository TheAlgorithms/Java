package com.thealgorithms.dynamicprogramming;

import java.util.List;

/**
 * This file contains an implementation of Word Break Problem using dynamic programming
 * Word Break Problem - Determine if a given string can be segmented into space-separated
 *                      words from a dictionary.
 *
 * Time Complexity: O(n*n) if n>l or O(n*l) if l>n
 *                  l - length of the largest string in the dictionary
 *                  n - length of the given string
 *
 * @author Sachin Baghel (https://github.com/sachin10fi/)
 */
public class WordBreakProblem {

    public static boolean wordBreak(String inputString, List<String> dictionary) {
        // dp will store results of subproblems
        // dp[i] will be true if string s can be segmented
        boolean[] dp = new boolean[inputString.length() + 1];

        // dp[0] as empty string can always be segmented.
        dp[0] = true;

        // Iterating through Input String
        for(int i = 0; i <= inputString.length(); i++){
            for(int j = 0; j < i; j++){
                if(dp[j] && dictionary.contains(inputString.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[inputString.length()];
    }
}
