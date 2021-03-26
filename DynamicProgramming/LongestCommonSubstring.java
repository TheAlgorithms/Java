public class LongestCommonSubstring {

  public static String string1, string2;
  public static int[][] dp;
  public static void main(String[] args) {
      int res = 0;
      string1 = "abbaf";
      string2 = "abcdef";
      int n = string1.length();
      int m = string2.length();
      dp = new int[n+1][m+1];
      for(int i = 0;i <= n;i++) {
        dp[0][i] = 0;
        dp[i][0] = 0;
      }

      for(int i = 1;i <= n;i++) {
          for(int j = 1;j <= m;j++) {
            if(string1.charAt(i-1) == string2.charAt(j-1)) {
              dp[i][j] = 1 + dp[i-1][j-1];
              res = Math.max(res, dp[i][j]);
            }else dp[i][j] = 0;
          }
      }
      System.out.println(res);
  }
}
