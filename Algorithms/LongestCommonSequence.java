/*
Given two strings text1 and text2, return the length of their longest common subsequence.
A subsequence of a string is a new string generated from the original string with some 
characters(can be none) deleted without changing the relative order of the remaining characters. 
(eg, "ace" is a subsequence of "abcde" while "aec" is not). 
A common subsequence of two strings is a subsequence that is common to both strings.
If there is no common subsequence, return 0.

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.

*/

SOLUTION 1:
class Solution {
   public int longestCommonSubsequence(String t1, String t2) {
        if (t1.length() > t2.length())
            return longestCommonSubsequence(t2, t1);
        final int n1 = t1.length(), n2 = t2.length();
        int[] dp = new int[n1+1];
        for (int i = n2-1; i >= 0; i--) {
            int pre = 0;
            for (int j = n1-1; j >= 0; j--) {
                int tmp = dp[j];
                dp[j] = t2.charAt(i) == t1.charAt(j)
                    ? 1 + pre
                    : Math.max(dp[j], dp[j+1]);
                pre = tmp;
            }
        }
        return dp[0];
    }
}

SOLUTION 2:
class Solution {
   public int longestCommonSubsequence(String t1, String t2) {
        int m = t1.length(), n = t2.length();       
        int L[][] = new int[2][n+1]; 
        int index=0; 
      
        for (int i = 0; i <= m; i++) 
        {  
            index = i & 1; 
      
            for (int j = 0; j <= n; j++) 
            { 
                if (i == 0 || j == 0) 
                    L[index][j] = 0; 
      
                else if (t1.charAt(i - 1) ==  
                         t2.charAt(j - 1)) 
                    L[index][j] = L[1 - index][j - 1] + 1; 
      
                else
                    L[index][j] = Math.max(L[1 - index][j],  
                                        L[index][j - 1]); 
            } 
        } 
      
        return L[index][n]; 
    } 

}
