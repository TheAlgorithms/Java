package com.thealgorithms.conversions;

import java.util.Scanner;

/**
 * Converts any Binary Number to a Decimal Number
 *
 * @author Pritam Dash
 */
class BinToDec {
    /**
     * This method converts a binary number to a decimal number.
     *
     * @param st The binary number
     * @param l  The length of the whole-number part of the Double type binary
     *           number
     * @return The decimal number
     */

    static String convert(String st, int l) {
        long x = 0;
        double y = 0.0;
        String st1;
        for (int i = 0; i < st.length(); i++) {
            char ch = st.charAt(i);
            int a = l - i - 1;
            if (ch == '1') {
                if (a >= 0)
                    x += (int) Math.pow(2, a);
                else
                    y += Math.pow(2, a);
            }
        }
        if (st.length() == l)
            st1 = Long.toString(x);
        else {
            double z = x + y;
            st1 = Double.toString(z);
        }
        return st1;
    }

    /**
     * Main method
     *
     * @param args Command line arguments
     */

    public static void main(String args[]) {
        String s = "", s1 = "", s2 = "";
        Scanner sc = new Scanner(System.in);
        System.out.print("Binary number: ");
        String bin = sc.nextLine();
        int f = 0;
        if (bin.charAt(0) == '-')
            f = 1;
        if (bin.contains(".") == true) {
            s = bin.substring(f, bin.indexOf('.'));
            s1 = s + bin.substring(bin.indexOf('.') + 1);
            s2 = convert(s1, s.length());
        } else
            s2 = convert(bin.substring(f), bin.length());
        if (f == 1)
            System.out.println("Decimal equivalent: -" + s2);
        else
            System.out.println("Decimal equivalent: " + s2);
        sc.close();
    }
}