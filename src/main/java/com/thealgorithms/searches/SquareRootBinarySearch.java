package com.thealgorithms.searches;

import java.util.Scanner;

/**
 * Given an integer x, find the square root of x. If x is not a perfect square,
 * then return floor(√x).
 * <p>
 * For example, if x = 5, The answer should be 2 which is the floor value of √5.
 * <p>
 * The approach that will be used for solving the above problem is not going to
 * be a straight forward Math.sqrt(). Instead we will be using Binary Search to
 * find the square root of a number in the most optimised way.
 *
 * @author sahil
 */
public class SquareRootBinarySearch {

    /**
     * This is the driver method.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number you want to calculate square root of : ");
        int num = sc.nextInt();
        long ans = squareRoot(num);
        System.out.println("The square root is : " + ans);
    }

    /**
     * This function calculates the floor of square root of a number. We use
     * Binary Search algorithm to calculate the square root in a more optimised
     * way.
     *
     * @param num Number
     * @return answer
     */
    private static long squareRoot(long num) {
        if (num == 0 || num == 1) {
            return num;
        }
        long l = 1;
        long r = num;
        long ans = 0;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (mid == num / mid) {
                return mid;
            } else if (mid < num / mid) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}
