// github.com/RodneyShag

import java.util.Scanner;

// O(n) runtime. O(1) space. Uses XOR. Keep in mind:
//   1) x ^ x = 0
//   2) x ^ 0 = x
//   3) XOR is commutative and associative
public class Solution {
    public static void main(String[] args) {
        /* Read input */
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int [] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = scan.nextInt();
        }
        scan.close();
        
        System.out.println(lonelyInteger(array));
    }
    
    /* XORs all numbers in array together */
    public static int lonelyInteger(int [] array) {
        int val = 0;
        for (int num : array) {
            val = val ^ num; // ^ is XOR operator
        }
        return val;
    }
}
