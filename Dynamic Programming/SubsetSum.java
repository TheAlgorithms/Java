/*Author:SUBHAM SANGHAI
A Dynamic Programming solution for subset sum problem*/

/*Description of Coin Change Problem with Examples

Description:Given a set of non-negative integers, and a value sum, 
determine if there is a subset(non contiguous) of the given set with sum equal to given sum.

Example:
set[] = {3, 34, 4, 12, 5, 2}, sum = 9
Output:  True  //There is a subset (4,5) with sum 9.

*/
import java.util.Arrays;
import java.util.Scanner;
public class SubsetSum
{

    // Returns true if there is a subset of set[] with sum equal to given sum
    private static boolean isSubsetSum(int set[], int n, int sum)
    {
        /*The value of subset[i][j] will be true if there 
          is a subset of set[0..j-1] with sum equal to i*/
        boolean subset[][] = new boolean[sum+1][n+1];
      	// If sum is 0, then answer is true
        for (int i = 0; i <= n; i++)
          subset[0][i] = true;
        // If sum is not 0 and set is empty, then answer is false
        for (int i = 1; i <= sum; i++)
          subset[i][0] = false;
      	// Fill the subset table in botton up manner
         for (int i = 1; i <= sum; i++)
         {
           for (int j = 1; j <= n; j++)
           {
             subset[i][j] = subset[i][j-1];
             if (i >= set[j-1]) // if the sum we are looking for is greater than the value of that particlar element
               subset[i][j] = subset[i][j] || subset[i - set[j-1]][j-1];
           }
         }
      
         return subset[sum][n];
    }
    // Driver program to test above function

    public static void main (String args[])
    {
          Scanner input = new Scanner(System.in);
	  System.out.println("Enter the size of the set");
	  int n=input.nextInt();
	  int[] arr=new int[n];
	  for(int i=0;i<n;i++)
	   {
 	   System.out.println("Enter the "+i +"th value of the subset");
	   arr[i]=input.nextInt();
	   }
          System.out.println("Enter the Sum for which we want to check");
	  int sum=input.nextInt();
          if (isSubsetSum(arr, n, sum) == true)
             System.out.println("Found a subset with given sum");
          else
             System.out.println("No subset with given sum");
    }
}
