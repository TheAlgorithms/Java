package com.thealgorithms.maths;
/**
 * More about the method in the link below
 * https://en.wikipedia.org/wiki/Bisection_method
 * 
 * This is a relatively quick method to get square root of a number
 * using binary search.
 * Returns integer values only!
 */
public class SquareRootBisection {
    public static int sqrtBisect(int n) {
        if (n == 0 || n == 1) return n;
        int start = 2, end = n, mid;
        while (start < end) {
            mid = start + (end - start) / 2;
            if (mid * mid == n) {
                return mid;
            } else if (mid * mid > n) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}