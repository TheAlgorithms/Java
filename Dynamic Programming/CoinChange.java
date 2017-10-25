/* Author:SUBHAM SANGHAI
Dynamic Programming Java implementation of Coin Change problem  */

/*Description of Coin Change Problem with Examples

Description: Given a value N, if we want to make change for an amount of money N, 
and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins, 
how many ways can we make the change? The order of coins doesn’t matter.

Example:
N = 4 & S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. 
So output should be 4. 
N = 10 and S = {2, 5, 3, 6}, there are five solutions: 
{2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. So the output should be 5. */

import java.util.Arrays;
import java.util.Scanner;
public class CoinChange
{
    
   private static long countWays(int S[], int m, int n)
   {
        /* table[i] will be storing the number of solutions
         for value i. We need n+1 rows as the table is
         constructed in bottom up manner using the base
         case (n = 0)*/
        long[] table = new long[n+1];
 
        // Initialize all table values as 0
        Arrays.fill(table, 0);   
 
        // Base case (If given value is 0)
        table[0] = 1;
 
        /* Pick all coins one by one and update the table[]
         values after the index greater than or equal to
         the value of the picked coin*/
        for (int i=0; i<m; i++)
            for (int j=S[i]; j<=n; j++)
                table[j] += table[j-S[i]];
 
        return table[n];
    }
    // Driver Function to test above function
    public static void main(String args[])
    {
	Scanner input = new Scanner(System.in);
	System.out.println("Enter the number of various currencies available");
	int m =input.nextInt();
	int[] arr=new int[m];
	for(int i=0;i<m;i++)
	{
 	   System.out.println("Enter the value of the currency changes available in infinite supply");
	   arr[i]=input.nextInt();
	}
	System.out.println("Enter the amount of money for which the change is required");
        int n =input.nextInt();
	System.out.print("The various ways to change an amount of money ="+n+" is :");
        System.out.println(countWays(arr, m, n));
    }
}
