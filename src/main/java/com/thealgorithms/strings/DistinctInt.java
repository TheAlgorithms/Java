/* package codechef; // don't place package name! 

You are given an integer X. Find the smallest number 

Y such that : Y is greater than X. All digits of Y are pairwise different.

Input Format
First-line will contain T, the number of test cases. Then the test cases follow.
Each test case contains a single line of input, a single integer X.

Output Format
For each test case, output in a single line the smallest integer Y which satisfies the given conditions. 
*/





import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class DistinctInt
{
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc=new Scanner(System.in);
        long n=sc.nextLong();
       for(int j=0;j<n;j++)
        {
            long no=sc.nextLong();
            no++;
            while(!isDistinct(String.valueOf(no)))
            {
                no++;
            }
                System.out.println(no);
        }
	}

    static Boolean isDistinct(String s)
    {
        HashSet<Character> set=new HashSet<>();
        for(int i=0;i<s.length();i++)
        {
            if(set.contains(s.charAt(i)))
                return false;
            else
            {
                set.add(s.charAt(i));
            }
        }
        return true;
    }
}
