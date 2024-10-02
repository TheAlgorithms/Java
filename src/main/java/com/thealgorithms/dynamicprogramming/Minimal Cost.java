// This is a famoush problem asked in interview rounds.
// It was Asked to me also

Ques: 
There is an array arr of heights of stone and Geek is standing at the first stone and can jump to one of the following: Stone i+1, i+2, ... i+k stone, where k is the maximum number of steps that can be jumped and cost will be |hi-hj| is incurred, where j is the stone to land on. Find the minimum possible total cost incurred before the Geek reaches the last stone.

Example:

Input: k = 3, arr[]= [10, 30, 40, 50, 20]
Output: 30
Explanation: Geek will follow the path 1->2->5, the total cost would be | 10-30| + |30-20| = 30, which is minimum
Input: k = 1, arr[]= [10, 20, 10]
Output: 20
Explanation: Geek will follow the path 1->2->3, the total cost would be |10 - 20| + |20 - 10| = 20.
Expected Time Complexity: O(n*k)
Expected Auxilary Space: O(n)

Constraint:
1<= arr.size() <=104
1 <= k <= 100
1 <= arr[i] <= 104

//I have written this solution acc to given contraintand tried to make it as short as possible for easy to understand
CODE:
public int minimizeCost(int k, int arr[]) {
        // code here
        int[] dp = new int[arr.length];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        
        for(int i=0; i<arr.length; i++) {
            for(int j=1;j<=k && i+j<arr.length; j++) {
                
                dp[i+j] = Math.min(dp[i+j], dp[i] + Math.abs(arr[i]-arr[i+j]));
            }
        }
        
        return dp[arr.length-1];
    }
