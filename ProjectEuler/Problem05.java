/*
Problem 5
2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
Answer:232792560
*/
package ProjectEuler;

public class Problem05 
{
	public static void main(String[] args) 
	{
		long l=2520;
		for(long i=11; i<=20; i++)
		{
			l=lcm(l,i);
		}
		System.out.println(l);
		}
	public static long gcd(long a, long b) 
	{
	    return b == 0 ? a : gcd(b, a % b); 
	}
	public static long lcm(long a, long b) 
	{
	    return (a * b) / gcd(a, b);
	}
}
 		/* Alternative Solution
		int j=20000;
		while(j%11!=0 || j%12!=0 || j%13!=0 || j%14!=0 || j%15!=0 || j%16!=0 || j%17!=0 || j%18!=0 || j%19!=0 || j%20!=0)
	        j+=20; 
		System.out.println(j);
		*/
