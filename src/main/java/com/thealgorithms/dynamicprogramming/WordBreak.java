/**
 * In this implementation, the wordBreak function takes a string s and a List of words wordDict as arguments.
 * It returns a boolean value indicating whether the string can be segmented into words from the word dictionary.
 */

import java.util.*;

public class WordBreak {
    public static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        String s = "applepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen");

        boolean result = wordBreak(s, wordDict);
        System.out.println("Can the string be segmented? " + result);
    }
}
