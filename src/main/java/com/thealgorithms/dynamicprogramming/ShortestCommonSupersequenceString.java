//[PROBLEM STATEMENT AND DESCRIPTION] (https://leetcode.com/problems/shortest-common-supersequence/description/)

//CODE STARTS BELOW//
class Solution {
    int m;
    int n;
    public String shortestCommonSupersequence(String str1, String str2) {
        m = str1.length();
        n = str2.length();
        
        return helper(str1, str2);
    }
    private String helper(String str1, String str2){
        int[][] dp = new int[m+1][n+1];
        for(int i=1; i<= m; i++){
            for(int j=1; j<= n; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = 1+ dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        String ans = "";
        int i=m, j=n;
        
        while(i>0 && j>0){
            if(str1.charAt(i-1) == str2.charAt(j-1)){
                ans += str1.charAt(i-1);
                i--;
                j--;
            }
            else if(dp[i-1][j] > dp[i][j-1]){
                ans += str1.charAt(i-1);
                i--;
            }
            else{
                ans += str2.charAt(j-1);
                j--;
            }
        }
        while(i > 0){
            ans += str1.charAt(i-1);
            i--;
        }
        while(j > 0){
            ans += str2.charAt(j-1);
            j--;
        }
        return new StringBuilder(ans).reverse().toString();
    }
}
