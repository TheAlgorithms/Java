import java.util.*;
public class IPL
{
    public static void main (String args[])
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int i;
        int arr[] = new int[n];
        for(i=0;i<n;i++)
            arr[i] = sc.nextInt();
        int dp[][] = new int[n][2];
        dp[0][0] = arr[0];
        dp[0][1] = 0;
        dp[1][0] = dp[0][0]+ arr[1];
        dp[1][1] = dp[0][0];
        int c;
        for(i=2;i<n;i++)
        {
            if(arr[i-1] > arr[i-2])
                dp[i][0] = arr[i] + dp[i-1][0];
            else
                dp[i][0] = arr[i] + dp[i-2][0];
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]);
        }

        System.out.println(Math.max(dp[n-1][0],dp[n-1][1]));
    }
}
