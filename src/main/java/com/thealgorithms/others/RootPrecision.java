package com.thealgorithms.others;

import java.util.Scanner;

public final class RootPrecision {
    private RootPrecision() {
    }

    public static void main(String[] args) {
        // take input
        Scanner scn = new Scanner(System.in);

        // n is the input number
        int n = scn.nextInt();

        // p is precision value for eg - p is 3 in 2.564 and 5 in 3.80870.
        int p = scn.nextInt();
        System.out.println(squareRoot(n, p));

        scn.close();
    }

    public static double squareRoot(int n, int p) {
        // rv means return value
        double rv;

        double root = Math.pow(n, 0.5);

        // calculate precision to power of 10 and then multiply it with root value.
        int precision = (int) Math.pow(10, p);
        root = root * precision;
        /*typecast it into integer then divide by precision and again typecast into double
    so as to have decimal points upto p precision */

        rv = (int) root;
        return rv / precision;
    }
}
