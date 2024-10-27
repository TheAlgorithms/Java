// 1st question using sliding window technique
// 1st 
// MAXIMUM SUM SUB ARRAY OF SIZE K : using sliding window technique
class GFG {
    // Returns maximum sum in
    // a subarray of size k.
    static int maxSum(int arr[], int n, int k)
    {
        // Initialize result
        int max_sum = Integer.MIN_VALUE;

        // Consider all blocks starting with i.
        for (int i = 0; i < n - k + 1; i++) {
            int current_sum = 0;
            for (int j = 0; j < k; j++)
                current_sum = current_sum + arr[i + j];

            // Update result if required.
            max_sum = Math.max(current_sum, max_sum);
        }

        return max_sum;
    }

    // Driver code
    public static void main(String[] args)
    {
        int arr[] = { 1, 4, 2, 10, 2, 3, 1, 0, 20 };
        int k = 4;
        int n = arr.length;
        System.out.println(maxSum(arr, n, k));
    }
}

// 2nd question using sliding window technique
// 2nd
// Longest Substring Without Repeating Characters: using sliding window technique
import java.util.*;

public class GfG {
    
    // Function to find the length of the longest 
      // substring without repeating characters
    static int longestUniqueSubstr(String s) {
        int n = s.length();
        int res = 0;

        for (int i = 0; i < n; i++) {

            // Initializing all characters as not visited
            boolean[] visited = new boolean[256];

            for (int j = i; j < n; j++) {

                // If current character is visited, 
                  // Break the loop
                if (visited[s.charAt(j)]) {
                    break;
                } 
                  else {
                  
                    // Else update the result if this 
                    // window is larger, and mark current 
                      // character as visited.
                    res = Math.max(res, j - i + 1);
                    visited[s.charAt(j)] = true;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "geeksforgeeks";
        System.out.println(longestUniqueSubstr(s));
    }
}
