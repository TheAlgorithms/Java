package com.thealgorithms.maths;

import java.util.Scanner;

/*
 * Find the 2 elements which are non repeating in an array
 * Reason to use bitwise operator: It makes our program faster as we are operating on bits and not on
 * actual numbers.
 */
public class NonRepeatingElement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i, res = 0;
        System.out.println("Enter the number of elements in the array");
        int n = sc.nextInt();
        if ((n & 1) == 1) {
            //Not allowing odd number of elements as we are expecting 2 non repeating numbers
            System.out.println("Array should contain even number of elements");
            return;
        }
        int arr[] = new int[n];

        System.out.println(
            "Enter " +
            n +
            " elements in the array. NOTE: Only 2 elements should not repeat"
        );
        for (i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        try {
            sc.close();
        } catch (Exception e) {
            System.out.println("Unable to close scanner" + e);
        }

        //Find XOR of the 2 non repeating elements
        for (i = 0; i < n; i++) {
            res ^= arr[i];
        }

        //Finding the rightmost set bit
        res = res & (-res);
        int num1 = 0, num2 = 0;

        for (i = 0; i < n; i++) {
            if ((res & arr[i]) > 0) { //Case 1 explained below
                num1 ^= arr[i];
            } else {
                num2 ^= arr[i]; //Case 2 explained below
            }
        }

        System.out.println(
            "The two non repeating elements are " + num1 + " and " + num2
        );
    }
    /* 
  Explanation of the code:
  let us assume we have an array [1,2,1,2,3,4]
  Property of XOR: num ^ num = 0. 
  If we XOR all the elemnets of the array we will be left with 3 ^ 4 as 1 ^ 1 and 2 ^ 2 would give 0.
  Our task is to find num1 and num2 from the result of 3 ^ 4 = 7.
  We need to find two's complement of 7 and find the rightmost set bit. i.e. (num & (-num))
  Two's complement of 7 is 001 and hence res = 1.
  There can be 2 options when we Bitise AND this res with all the elements in our array
  1. Result will come non zero number
  2. Result will be 0.
  In the first case we will XOR our element with the first number (which is initially 0)
  In the second case we will XOR our element with the second number(which is initially 0)
  This is how we will get non repeating elements with the help of bitwise operators. 
     */
}
