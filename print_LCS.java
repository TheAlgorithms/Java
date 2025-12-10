import java.util.*;
class print_LCS{

  static void solve(String a,String b){
      int n=a.length(),m=b.length();
      int[][] dp=new int[n+1][m+1];

      for(int i=0;i<=n;i++){
         for(int j=0;j<=m;j++){
             if(i==0 || j==0){
                dp[i][j]=0;
	     }
	     else if(a.charAt(i-1)==b.charAt(j-1)){
                dp[i][j]= 1 + dp[i-1][j-1];
	     }
	     else{
                dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
	     }
	 }
      }
      display(a,b,n,m,dp);
  }

  static void display(String a,String b,int i,int j,int[][] dp){
         StringBuilder sb=new StringBuilder();
	  while(i>0 && j>0){
           if(a.charAt(i-1)==b.charAt(j-1)){
		   sb.append(a.charAt(i-1));
		   i--;
		   j--;
	   }
	   else{
               if(dp[i-1][j] > dp[i][j-1]) i--;
	       else j--;
	   }
      }
      System.out.println(sb.toString());
  }

  public static void main(String[] args){
     String a="abccdjif";
     String b="njfabccdino";
     // given two strings , need to find Longest Common Subsequence and print it 
     
     solve(a,b);
   }
}
