package com.thealgorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ClimbingStairsTest {

    public static int climbStairs(int n) {
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=1;
       
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
    
    @Test
    public void testclimbStairs(){
        assertEquals(13, climbStairs(6));
        assertEquals(34, climbStairs(8));
    }
}
