package com.thealgorithms.searches;

import java.util.Scanner;

/*
 * The algorithm will find the square root of x. If x is a perfect square, it's value
 * is returned, else, the floor of it's root is determined using binary search and 
 * then the decimal value is added to the determined floor value which will give the
 * root of x.
 * 
 * The square root of a number must be calculated without using the method Math.sqrt().
 */

public class SquareRootBinarySearch {

    public SquareRootBinarySearch() {
    }

    /**
     * This is the driver method.
     *
     * @param args Command line arguments
     */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number you want to calculate square root of : ");
        int num = sc.nextInt();

        int precision = 5;

        double ans = squareRoot(num, precision);
        System.out.println("The square root of " + num + " is : " + ans);
    }

    /**
     * This function calculates the floor of square root of a number. We use
     * Binary Search algorithm to calculate the square root in a more optimised
     * way.
     *
     * @param num Number
     * @return answer
     */
    private static double squareRoot(long num, int precision) {
        if (num == 0 || num == 1) {
            return num;
        }
        long l = 0, r = num;

        while (l <= r) {

            long mid = l + (r - l)/2;

            if (mid * mid == num) {
                return mid;
            }

            else if (mid * mid >= num)
                r = mid - 1;

            else
                l = mid + 1;
        }

        return decimal(num, (double)r, precision);
    }

    /*
     * This function takes the calculated floor value and calculates the decimal value
     * of the root as per the provided precision value.
     */

    private static double decimal(long n, double root, int precision) {

        double inc = 0.1;   //inc - increment

        for (int i = 0; i < precision; i++) {
            while (root*root <= n) {

                root += inc;
            }
            root -= inc;
            inc = inc / 10;
        }

        return root;
    }
}
