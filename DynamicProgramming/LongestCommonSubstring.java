/*
 *  In the given Example we take the input of the two strings s1 and s2 and we find out the length of the 
 *  Longest Common Substring
 */
/*
@Author : Amit Thakur
*/

package DynamicProgramming;

import java.lang.*;
import java.util.*;
import java.io.*;

public class LongestCommonSubstring 
{
	public static int LCS(String s1,String s2)
	{
		int i=0,j=0,row=0,col=0,max=0,ret=0;
		
		int dp[][];
		dp=new int [s1.length()+1][s2.length()+1];
		
		row=s1.length();
		col=s2.length();
		
		for(i=0;i<row+1;i++)
		{
			for(j=0;j<col+1;j++)
			{
				if(i==0 || j==0)
				{
					dp[i][j]=0;
				}
			}
		}
		
		for(i=1;i<row+1;i++)
		{
			for(j=1;j<col+1;j++)
			{
				if(s1.charAt(i-1)==s2.charAt(j-1))
				{
					dp[i][j]=1+dp[i-1][j-1];
				}
				else
				{
					dp[i][j]=0;
				}
			}
		}
		
		for(i=0;i<row+1;i++)
		{
			for(j=0;j<col+1;j++)
			{
				max=Math.max(dp[i][j], max);
			}
		}
		
		ret=max;
		return(ret);
	}

	public static void main(String[] args) 
	{
		int i=0,ret=0;
		String s1="abbaf";
		String s2="abcdef";
		
		ret=LCS(s1,s2);
		
		System.out.println("The Length of the Longest Common Substring is :");
		System.out.println(ret);

	}

}
