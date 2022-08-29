package com.thealgorithms.bitmanipulation;

import java.util.Scanner;

/*
A nth magic number is calculated via the binary representation of n.
Ex:         n             5³            5²            5¹            MagicNum
            1      -      0             0             1       =        5
            2      -      0             1             0       =        25
            3      -      0             1             1       =        30
            4      -      1             0             0       =        125
            5      -      1             0             1       =        130

            The magic no. for n = 5, can be calculated as : (5) base 10 = (101) base 2
                                                                        = (5³ x 1) + (5² x 0) + (5¹ x 1) = 130
 */

public class MagicNumber {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter n to get nth magic number (n>0): ");
        int n = in.nextInt();

        if (n > 0) {
            System.out.println("Magic number is: " + magicNum(n));
        }
        else
            System.out.println("Invalid input!!!");
    }

    private static int magicNum(int n) {

        int ans = 0, pow = 1;

        while (n != 0) {
            int lastBit = n & 1;                        //any no. say 5 & 1 will give the last bit of 5 = (101) i.e. 1.
            ans += (lastBit * Math.pow(5, pow++));      //formula to calculate magic no.
            n = n >> 1;                                 //discarding the last bit of n by right shifting n.
        }
        return ans;
    }
}
