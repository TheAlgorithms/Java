Shortest Common Supersequence

Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  If multiple answers exist, you may return any of them.

(A string S is a subsequence of string T if deleting some number of characters from T (possibly 0, and the characters are chosen anywhere from T) results in the string S.)

 

Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
 

Note:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.

ALGORITHM:
To print LCS

1)First we build the LCS table.
2)We start from i=str1.length() and j = str2.length() and if the characters are equal then we append the char and move diagonally (i-- & j--)
3)Else we move in the direction which has maximum value in the dp table
3.1 If dp[i-1][j]> dp[i][j-1]) then move up (i--)
3.2 else move left (j--)

To print SCS using LCS Table'

Is there a way to print SCS using the LCS DP Table? Yes, there is.
If we observe keenly,
SCS is nothing but str1+str2 - LCS.

1)So here we print the common characters only once as usual.
2)But we also print the char in the else loop whose matrix value is greater (Because we need them to form a supersequence of str1 & str2)
3)The while loops ends if any of the string str1 or str2 is exhausted.
So we need to check and print the remaining chars either str1 or str2 whichever is not exhausted.
4)Finally we return the reversed string.

SOLUTION:

class Solution {
    public String shortestCommonSupersequence(String str1, String str2) 
    {
        StringBuilder sb = new StringBuilder();
        
        int m = str1.length();
        int n = str2.length();
        int dp[][] = new int[m+1][n+1];
        
        for(int i=1;i<=m;i++)
        {
            for(int j=1;j<=n;j++)
            {
                if(str1.charAt(i-1)==str2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1]+1;
                else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        
        int i=m;
        int j=n;
        
        while(i>0&&j>0)
        {
            if(str1.charAt(i-1)==str2.charAt(j-1))
            {
               sb.append(str1.charAt(i-1));
                i--;
                j--;
            }
                
            
            else
            {
                if(dp[i][j-1]>dp[i-1][j])
                {
                        sb.append(str2.charAt(j-1));
                        j--;
                }
                
                else
                {
                   sb.append(str1.charAt(i-1));
                   i--;
                }
            }
        }
        
        while(i>0)
        {
            sb.append(str1.charAt(i-1));
            i--;
        }
        
        while(j>0)
        {
            sb.append(str2.charAt(j-1));
            j--;
        }
        
        return sb.reverse().toString();
    }
}