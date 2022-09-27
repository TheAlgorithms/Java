//package com.thealgorithms.dynamicprogramming;

import java.util.*;

/*
 * @author Durga Prasad (https://github.com/Durgaprasad101)
 */
/*
 Fibonacci recursion
 recursion is a programming technique using function or algorithm that calls 
 itself one or more times until a specified condition is met at which time the 
 rest of each repetition is processed from the last one called to the first.
 TIME COMPLIXITY: O(n)
 SPACE COMPLIXITY: O(n)[stack space]+ O(n)[array space]
 */
public class Main{
  public static int recursion_1(int n){
    if(n<=1){
        return n;
    }
    return recursion_1(n-1)+recursion_1(n-2);
}
  /* 
  Fibonacci memoization 
 Memoization is a technique that is used to implement the DP algorithms. 
 Memoization is also known as a top-down approach. It starts from solving
 the highest-level sub-problems. Initially, it solves the highest-level 
 subproblem and then solve the next sub-problem recursively and the next.
 TIME COMPLIXITY: O(n)
 SPACE COMPLIXITY: O(n)[stack space]+ O(n)[array space]
 */
  public static int memoization(int n,int dp[]){
     if(n<=1){
        return n;
    }
    if(dp[n]!=0)
    return dp[n];
    int temp=memoization(n-1,dp)+memoization(n-2,dp);
    dp[n]=temp;
    return dp[n];
}
  /*
  In the tabulation approach to DP (also known as the table-filling method) we solve 
  all sub-problems and store their results on a matrix. these results are then used to 
  solve larger problems that depend on the previously computed results. Because of this, 
  the tabulation approach is also known as a bottom-up approach.
  TIME COMPLIXITY: O(n)
  SPACE COMPLIXITY: O(n)
  */
  public static void tabulation(int n){
    int dp[]=new int[n+1];
    dp[0]=0;
    dp[1]=1;
    for(int i=2;i<=n;i++){
        dp[i]=dp[i-1]+dp[i-2];
    }
    System.out.println(dp[n]);
}
  /*
  Space optimization is a algorithm in dp which is used to solve the problem in constant amount of time or without using any extra space.
  TIME COMPLIXITY: O(n)
  SPACE COMPLIXITY: O(1)
  */
  public static void space_optimization(int n){
    int prev2=0;
    int prev=1;
    int curr=0;
    for(int i=2;i<=n;i++){
        curr=prev+prev2;
        prev2=prev;
        prev=curr;
    }
   System.out.println(prev);
}
 public static void main (String[] args) {
     Scanner sc=new Scanner(System.in);
     int n=sc.nextInt();
     int dp[]=new int[n+1];
     int re=recursion_1(n);
     int me=memoization(n,dp);
     System.out.println(re);
     System.out.println(me);
     tabulation(n);
    space_optimization(n);
 }
}
