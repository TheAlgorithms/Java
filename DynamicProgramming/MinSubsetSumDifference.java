/*

This DP-algorithm finds the value of the minimum difference between 2 subsets of the array 

Here, arr refers to the array for which we need to find the minimum difference, sum refers to sum of all the elements of the array, and n refers to size of the array





*/



public static int subset_min_diff(int arr[], int sum , int n){
        boolean dp[][]= new boolean[n+1][sum+1];
        for (int i = 0; i <n+1 ; i++) {
            for (int j = 0; j <sum+1 ; j++) {
                if(j==0){
                    dp[i][j]=true;
                }
                else if(i==0){
                    dp[i][j]=false;
                }
            }
        }
        for (int i = 1; i <n+1 ; i++) {
            for (int j = 1; j <sum+1 ; j++) {
                if(arr[i-1]<=j){
                    dp[i][j]= dp[i-1][j-arr[i-1]] || dp[i-1][j];
                }

                else if(arr[i-1]>j){
                    dp[i][j]= dp[i-1][j];
                }

            }
        }

        //return dp[n][sum];
        int min= Integer.MAX_VALUE;
        for (int j = 0; j <sum+1 ; j++) {
            if(dp[n][sum]){
                min= Math.min(min, Math.abs(sum-2*j));
            }
        }

        return min;
        
    }


