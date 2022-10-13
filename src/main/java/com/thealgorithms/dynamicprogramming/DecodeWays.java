package com.thealgorithms.dynamicprogramming;
import java.util.*;
public class DecodeWays {
    /**
     * Problem Statement :
     * A message containing letters are encoded in the following manner {'A' = 1, 'B' = 2 , ... , 'Z' = 26}
     * Task is to decode the string containing the encoded digits and return the number of ways possible to decode the string
     * Example : 21012
     * Number of possible ways it could be decoded
     * 2 10 1 2 -> BJAB
     * 2 10 12  -> BJL
     * while 21 01 2 is invalid combination because the string "01" cannot be mapped to "A" because
     * "01" is different from "1"
     */
    public static int getWays(String s){
        // Initialisations
        int n = s.length();
        // Declaring a dp array
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0)!='0'?1:0;
        for(int i=2;i<=n;++i){
            int  small = Integer.parseInt(s.substring(i-1,i));
            int large = Integer.parseInt(s.substring(i-2,i));
            if(small>=1 && small<=9) dp[i] += dp[i-1];
            if(large>=10 && large<=26) dp[i] += dp[i-2];
        }
        return dp[s.length()];
    }
    /**
     * Main Method
     */
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        int ans = getWays(str);
        System.out.println("The number of valid ways to decode the string are "+ans);
    }
}
