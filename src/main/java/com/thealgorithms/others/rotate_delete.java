
// Given an array arr integers. Assume sz to be the initial size of the array. Do the following operations exactly sz/2 times. In every kth (1<= k <= sz/2) operation:

// Right-rotate the array clockwise by 1.
// Delete the (n– k + 1)th element from begin.
// Now, Return the first element of the array.


import java.io.*;
import java.lang.*;
import java.util.*;


class Abhishek {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String line = br.readLine();
            String[] tokens = line.split(" ");

            // Create an ArrayList to store the integers
            ArrayList<Integer> arr = new ArrayList<>();

            // Parse the tokens into integers and add to the array
            for (String token : tokens) {
                arr.add(Integer.parseInt(token));
            }

            Solution obj = new Solution();
            int res = obj.rotateDelete(arr);

            System.out.println(res);
        }
    }
}


class Solution {
    public static int rotateDelete(ArrayList<Integer> arr) {
        // code here
        int n = arr.size();
        if(n == 1 || n == 2)return arr.get(n-1);
        
        int[] b = {3, 2, 3, 3};
        int a = 3, j = 0;
        while(a + 4 <= n){
            a += 4;
            j++;
        }
        
        for(int i = 0; i < 4; i++){
            if(a+i == n)return arr.get(b[i]+j-1);
        }
        return 0;
    }
}