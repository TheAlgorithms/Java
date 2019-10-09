import java.util.*;
class rainwater
{
    static int max(int i,int n,int arr[])
    {
        int max = 0;
        for(int j = i;j<n;j++)
          if(arr[j] > max)
            max = arr[j];
        return max;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        int dp[] = new int[n];
        int i;
        for(i=0;i<n;i++)
          arr[i] = sc.nextInt();
        dp[0] = 0;
        int lmax = arr[0],rmax=0;
        rmax = max(1,n,arr);
        for(i=1;i<n;i++)
        {
            dp[i] = dp[i-1] + Math.max(Math.min(lmax,rmax)-arr[i],0);
            if(arr[i] == rmax )
              rmax = max(i+1,n,arr);
            if(arr[i] > lmax)
              lmax = arr[i];
        }
        System.out.println(dp[n-1]);
    }
}
