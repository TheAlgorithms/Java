/*
You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. 
In how many distinct ways can you climb to the top?

Sample Input : n = 2
Sample Output : 2
Explanation : There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Sample Input : n = 3
Sample Output : 3
Explanation : There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 */

// We shall use DP to solve this
import java.util.*;
public class ClimbingStairs 
{
    public static void main(String args[])
    {
	Scanner sc= new Scanner(System.in);
	System.out.println("Enter the number of stairs (n) : ");
	int n= sc.nextInt();
        int ways = climbStairs(n);
        System.out.println("You can climb 1 or 2 steps at a time");
        System.out.println("The number of ways to climb the stairs are : "+ ways);
        
    }
    public static int climbStairs(int n) 
    {
        int a[]=new int [n+1];
        a[0]=1;
        a[1]=1;
        for (int i=2;i<=n;i++)
        {
            a[i]=a[i-1]+a[i-2];
        }
        return a[n];
    }
    
}
