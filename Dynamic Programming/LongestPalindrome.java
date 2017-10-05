class LongestPalindrome{

	public static int lpal(String str){
		int n =str.length();
		if(n == 0 )return 0;
		StringBuilder str1= new StringBuilder(str);
		str1=str1.reverse();
		int dp[][] = new int[n+1][n+1];

		for(int i =0;i<=n;i++){dp[i][0]=0;dp[0][i]=0;}
			for(int i =1;i<=n;i++){
				for(int j =1;j<=n;j++){
					if(str.charAt(i-1)==str1.charAt(j-1))dp[i][j]=dp[i-1][j-1]+1;
					else dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
				}
			}
			return dp[n][n];
	}


	public static void main(String args[]){
		String str = "ZILLIOUSOUILZIL";
		System.out.println(lpal(str));
	}
}
