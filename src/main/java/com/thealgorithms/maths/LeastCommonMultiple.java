package com.thealgorithms.maths;

import java.util.*;

/**
 * Is a common mathematics concept to find the smallest value number
 * that can be divide using either number without having the remainder.
 * https://maticschool.blogspot.com/2013/11/find-least-common-multiple-lcm.html
 * @author LauKinHoong
 */

public class LeastCommonMultiple {

    /**
     * Driver Code
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter first number >> ");
        int num1 = input.nextInt();
        System.out.println("Please enter second number >> ");
        int num2 = input.nextInt();
        System.out.println(
            "The least common multiple of two numbers is >> " + lcm(num1, num2)
        );
    }

    /*
     * get least common multiple from two number
     */
    public static int lcm(int num1, int num2) {
        int high, num3;
        int cmv = 0;
        /*
         * value selection for the numerator
         */
        if (num1 > num2) {
            high = num3 = num1;
        } else {
            high = num3 = num2;
        }

        while (num1 != 0) {
            if (high % num1 == 0 && high % num2 == 0) {
                cmv = high;
                break;
            }
            high += num3;
        }
        return cmv;
    }
}
