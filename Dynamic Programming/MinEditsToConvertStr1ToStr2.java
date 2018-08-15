package DynamicProgramming;

import java.util.Scanner;

public class MinEditsToConvertStr1ToStr2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
	    int t = s.nextInt();
	    while(t-->0)
	    {
	        int p = s.nextInt();
	        int q = s.nextInt();
	        String str1 = s.next();
	        String str2 = s.next();
	        int[][] dp = new int[p+1][q+1];
	        for(int i=0;i<=p;i++)
	            dp[i][q] = p-i;
	        for(int j=0;j<=q;j++)
	            dp[p][j] = q-j;
	        for(int i=p-1;i>=0;i--)
	            for(int j=q-1;j>=0;j--)
	            {
	                if(str1.charAt(i)==str2.charAt(j))
	                    dp[i][j] = dp[i+1][j+1];
	                else
	                    dp[i][j] = 1+Math.min(dp[i+1][j+1],Math.min(dp[i+1][j],dp[i][j+1]));
	            }
	        System.out.println(dp[0][0]);
	    }
	}

}
