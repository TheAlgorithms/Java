package com.thealgorithms.maths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

final class KeithNumber {
    private KeithNumber() {
    }

    // user-defined function that checks if the given number is Keith or not
    static boolean isKeith(int x) {
        // List stores all the digits of the X
        ArrayList<Integer> terms = new ArrayList<>();
        // n denotes the number of digits
        int temp = x;
        int n = 0;
        // executes until the condition becomes false
        while (temp > 0) {
            // determines the last digit of the number and add it to the List
            terms.add(temp % 10);
            // removes the last digit
            temp = temp / 10;
            // increments the number of digits (n) by 1
            n++;
        }
        // reverse the List
        Collections.reverse(terms);
        int nextTerm = 0;
        int i = n;
        // finds next term for the series
        // loop executes until the condition returns true
        while (nextTerm < x) {
            nextTerm = 0;
            // next term is the sum of previous n terms (it depends on number of digits the number
            // has)
            for (int j = 1; j <= n; j++) {
                nextTerm = nextTerm + terms.get(i - j);
            }
            terms.add(nextTerm);
            i++;
        }
        // when the control comes out of the while loop, there will be two conditions:
        // either nextTerm will be equal to x or greater than x
        // if equal, the given number is Keith, else not
        return (nextTerm == x);
    }

    // driver code
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (isKeith(n)) {
            System.out.println("Yes, the given number is a Keith number.");
        } else {
            System.out.println("No, the given number is not a Keith number.");
        }
        in.close();
    }
}
