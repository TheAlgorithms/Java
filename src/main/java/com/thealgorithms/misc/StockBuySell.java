
/*
 * Problem Statement: The cost of a stock on each day is given in an array.
 * Find the maximum profit that you can make by buying and selling on those days.
 * Example:
 * Input: arr[] = {100, 180, 260, 310, 40, 535, 695}
 * Output: 865
 * Explanation: Buy the stock on day 0 and sell it on day 3 => 310 – 100 = 210
 * Buy the stock on day 4 and sell it on day 6 => 695 – 40 = 655
 * Maximum Profit  = 210 + 655 = 865
 */

 /*
 Solution:
  * Stock Buy Sell to Maximize Profit using Valley Peak Approach:
    In this approach, we just need to find the next greater element and
    subtract it from the current element so that the difference keeps
    increasing until we reach a minimum. If the sequence is a decreasing
    sequence, so the maximum profit possible is 0.

    For More approaches: https://www.geeksforgeeks.org/stock-buy-sell/

  */

  import java.util.*;
 
  class StockBuySell {   
      static int maxProfit(int prices[], int size)
      {   
          int maxProfit = 0;
          for (int i = 1; i < size; i++)
              if (prices[i] > prices[i - 1])
                  maxProfit += prices[i] - prices[i - 1];
          return maxProfit;
      }
   
      public static void main(String[] args)
      {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Number of days");
        int n = sc.nextInt();
        int[] price = new int[n];
        System.out.println("Enter the Prices on each day");
        for(int i=0;i<n;i++){
            price[i] = sc.nextInt();
        }
        sc.close();
        System.out.println(maxProfit(price, n));
      }
  }