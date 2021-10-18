import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class coinChange2{
    public static void main(String[] args) throws Exception { 
        Scanner scanner = new Scanner(System.in);
        int amount=scanner.nextInt();
        int n=scanner.nextInt();
        int coins[]=new int[n];
        for(int i=0; i<n; i++) coins[i]=scanner.nextInt();
      Solution sol=new Solution();
      sol.change(amount, coins);
      
    }
   // Two key points:
// create memoization with respect to 0 (thus initialize memo with null)
// in a recursive call using index i without increment
// Last key respects cases 1 + 2 == 2 + 1 and should be counted as one way of amount decompositions.

class Solution {
    public int change(int amount, int[] coins) {

        return change(amount, coins, 0, new Integer[amount + 1][coins.length]);

    }
    
    public int change(int amount, int[] coins, int pos, Integer[][] memo) {
        if (amount == 0) return 1;
        if (amount < 0) return 0;

        if (memo[amount][pos] != null) return memo[amount][pos]; // first key

        int ret = 0;

        for (int i = pos; i < coins.length; i++) {
            ret += change(amount - coins[i], coins, i, memo); // second key - "i" without increment
        }

        memo[amount][pos] = ret;
        return ret;
    }
}
}
