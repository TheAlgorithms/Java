package com.thealgorithms.searches;

/* Problem Statement: we have 2 integers 'n' and 'm'. we have to return the 'nth' root of 'm'.
   If the nth root is not an integer, return -1.

   solution : We will find the nth root of a given integer m using binary search.

  example1: Input: 'n' = 3, 'm' = 27

            Output: 3

            3rd Root of 27 is 3, as (3)^3 equals 27.

  example2: Input: 'n' = 4, 'm' = 69

            Output: -1

            There is no 4th root of 69 present.

  @author - Amul Sharma (https://github.com/amul67142)
*/

public final class NthRoot {
    private NthRoot() {
    }
    public static int findNthRoot(int n, int m) {
        int low = 1;
        int high = m;
        // using binary search
        while (low <= high) {
            int mid = (low + high) / 2;
            if (Math.pow(mid, n) == m) {
                return mid;
            } else if (Math.pow(mid, n) > m) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        // returning -1 if the root isn't in integer form.
        return -1;
    }
}
