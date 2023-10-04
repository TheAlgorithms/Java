package com.thealgorithms.dynamicprogramming;

/*

 * Problem Statement: -
 * Find Longest Alternating Subsequence

 * A sequence {x1, x2, .. xn} is alternating sequence if its elements satisfy one of the following
 relations :

   x1 < x2 > x3 < x4 > x5 < …. xn or
   x1 > x2 < x3 > x4 < x5 > …. xn
 */
public class LongestAlternatingSubsequence {

    /* Function to return longest alternating subsequence length*/
    static int AlternatingLength(int[] arr, int n) {
        /*

                las[i][0] = Length of the longest
                        alternating subsequence ending at
                        index i and last element is
                        greater than its previous element

                las[i][1] = Length of the longest
                        alternating subsequence ending at
                        index i and last element is
                        smaller than its previous
                        element

         */
        int[][] las = new int[n][2]; // las = LongestAlternatingSubsequence

        for (int i = 0; i < n; i++) {
            las[i][0] = las[i][1] = 1;
        }

        int result = 1; // Initialize result

        /* Compute values in bottom up manner */
        for (int i = 1; i < n; i++) {
            /* Consider all elements as previous of arr[i]*/
            for (int j = 0; j < i; j++) {
                /* If arr[i] is greater, then check with las[j][1] */
                if (arr[j] < arr[i] && las[i][0] < las[j][1] + 1) {
                    las[i][0] = las[j][1] + 1;
                }

                /* If arr[i] is smaller, then check with las[j][0]*/
                if (arr[j] > arr[i] && las[i][1] < las[j][0] + 1) {
                    las[i][1] = las[j][0] + 1;
                }
            }

            /* Pick maximum of both values at index i */
            if (result < Math.max(las[i][0], las[i][1])) {
                result = Math.max(las[i][0], las[i][1]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {10, 22, 9, 33, 49, 50, 31, 60};
        int n = arr.length;
        System.out.println("Length of Longest "
            + "alternating subsequence is " + AlternatingLength(arr, n));
    }
}
