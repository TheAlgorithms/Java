package com.thealgorithms.dynamicprogramming;

/**
 * Imagine you have a collection of N wines placed next to each other on the
 * shelf. The price of ith wine is pi(Prices of different wines are different).
 * Because wine gets better every year supposing today is year 1, on year y the
 * price would be y*pi i.e y times the value of the initial year. You want to
 * sell all wines but you have to sell one wine per year. One more constraint on
 * each year you are allowed to sell either leftmost or rightmost wine on the
 * shelf. You are not allowed to reorder. You have to find the maximum profit
 *
 */
public class WineProblem {

    // Method 1: Using Recursion
    // Time Complexity=0(2^N) Space Complexity=Recursion extra space
    public static int WPRecursion(int[] arr, int si, int ei) {
        int n = arr.length;
        int year = (n - (ei - si + 1)) + 1;
        if (si == ei) {
            return arr[si] * year;
        }

        int start = WPRecursion(arr, si + 1, ei) + arr[si] * year;
        int end = WPRecursion(arr, si, ei - 1) + arr[ei] * year;

        int ans = Math.max(start, end);

        return ans;
    }

    // Method 2: Top-Down DP(Memoization)
    // Time Complexity=0(N*N) Space Complexity=0(N*N)+Recursion extra space
    public static int WPTD(int[] arr, int si, int ei, int[][] strg) {
        int n = arr.length;
        int year = (n - (ei - si + 1)) + 1;
        if (si == ei) {
            return arr[si] * year;
        }

        if (strg[si][ei] != 0) {
            return strg[si][ei];
        }
        int start = WPTD(arr, si + 1, ei, strg) + arr[si] * year;
        int end = WPTD(arr, si, ei - 1, strg) + arr[ei] * year;

        int ans = Math.max(start, end);

        strg[si][ei] = ans;

        return ans;
    }

    // Method 3: Bottom-Up DP(Tabulation)
    // Time Complexity=0(N*N/2)->0(N*N) Space Complexity=0(N*N)
    public static int WPBU(int[] arr) {
        int n = arr.length;
        int[][] strg = new int[n][n];

        for (int slide = 0; slide <= n - 1; slide++) {
            for (int si = 0; si <= n - slide - 1; si++) {
                int ei = si + slide;
                int year = (n - (ei - si + 1)) + 1;
                if (si == ei) {
                    strg[si][ei] = arr[si] * year;
                } else {
                    int start = strg[si + 1][ei] + arr[si] * year;
                    int end = strg[si][ei - 1] + arr[ei] * year;

                    strg[si][ei] = Math.max(start, end);
                }
            }
        }
        return strg[0][n - 1];
    }

    public static void main(String[] args) {
        int[] arr = { 2, 3, 5, 1, 4 };
        System.out.println("Method 1: " + WPRecursion(arr, 0, arr.length - 1));
        System.out.println(
            "Method 2: " +
            WPTD(arr, 0, arr.length - 1, new int[arr.length][arr.length])
        );
        System.out.println("Method 3: " + WPBU(arr));
    }
}
// Memoization vs Tabulation : https://www.geeksforgeeks.org/tabulation-vs-memoization/
// Question Link : https://www.geeksforgeeks.org/maximum-profit-sale-wines/
