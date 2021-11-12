// Wikipedia for Harshad Number : https://en.wikipedia.org/wiki/Harshad_number
package com.thealgorithms.maths;

import java.util.Scanner;

public class HarshadNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number : ");
        long a = sc.nextLong();

        checkHarshadNumber(a);
    }

    /**
     * A function to check if a number is Harshad number or not
     *
     * @param a The number which should be checked
     */
    public static void checkHarshadNumber(long a) {

        long b = a;
        int sum = 0;

        // this is just for showing the explanation else it's of no use you can ommit it
        int[] each = new int[Long.toString(a).length()];

        int c = 0;

        while (b > 0) {
            sum += b % 10;
            each[c] = (int) (b % 10);
            b /= 10;
            c++;
        }

        if (a % sum == 0) {
            System.out.println(a + " is a Harshad Number");

            // For you better explanation how is that a Harshad Number
            System.out.println("\nExplaination :");

            for (int i = each.length - 1; i >= 0; i--) {
                System.out.print(each[i] + " ");
                if (i != 0) {
                    System.out.print("+ ");
                }
            }

            System.out.println("= " + sum);
            System.out.println(sum + " × " + (a / sum) + " = " + a);
        } else {
            System.out.println(a + " is not a Harshad Number");
        }
    }
}
