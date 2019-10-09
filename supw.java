import java.util.*;
class supw
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        int dp[][] = new int[n][2];
        int i;
        for(i=0;i<n;i++)
          arr[i] = sc.nextInt();
        dp[0][0] = arr[0];
        dp[1][0] = arr[1];
        dp[0][1] = dp[1][1] = 0;
        for(i=2;i<n;i++)
        {
            dp[i][0] = arr[i] + Math.min(dp[i-1][0],dp[i-1][1]);
            dp[i][1] = Math.min(dp[i-1][0],dp[i-2][0]);
        }
        System.out.println(Math.min(dp[n-1][0],dp[n-1][1]));
    }
}
