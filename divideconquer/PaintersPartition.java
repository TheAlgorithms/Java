import java.util.*; 
import java.io.*; 
  
class PPartition
{ 
// To calculate sum between two indices 
// in array 
static int sum(int arr[], int from, int to) 
{ 
    int total = 0; 
    for (int i = from; i <= to; i++) 
        total += arr[i]; 
    return total; 
} 
   
// bottom up tabular dp 
static int findMax(int arr[], int n, int k) 
{ 
    // initialize table 
    int A[][] = new int[k+1][n+1]; 
   
    // base cases 
    // k=1 
    for (int i = 1; i <= n; i++) 
        A[1][i] = sum(arr, 0, i - 1); 
   
    // n=1 
    for (int i = 1; i <= k; i++) 
        A[i][1] = arr[0]; 
   
    // 2 to k partitions 
    for (int i = 2; i <= k; i++) { // 2 to n boards 
        for (int j = 2; j <= n; j++) { 
   
            // track minimum 
            int best = Integer.MAX_VALUE; 
   
            // i-1 th separator before position arr[p=1..j] 
            for (int p = 1; p <= j; p++)  
                best = Math.min(best, Math.max(A[i - 1][p], 
                              sum(arr, p, j - 1)));        
   
            A[i][j] = best; 
        } 
    } 
    
    return A[k][n]; 
} 
   
public static void main(String args[]) 
{ 
 int arr[] = { 10, 20, 60, 50, 30, 40 }; 
    
    int n = arr.length; 
        int k = 3; 
 System.out.println(findMax(arr, n, k)); 
} 
} 
