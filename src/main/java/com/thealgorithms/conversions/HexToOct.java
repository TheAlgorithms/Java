package com.thealgorithms.conversions;

import java.util.Scanner;

/**
 * Converts any Hexadecimal Number to Octal
 *
 * @author Tanmay Joshi
 */
public class HexToOct {

    /**
     * This method converts a Hexadecimal number to a decimal number
     *
     * @param s The Hexadecimal Number
     * @return The Decimal number
     */
    public static int hex2decimal(String s) {
        String str = "0123456789ABCDEF";
        s = s.toUpperCase();
        int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            int n = str.indexOf(a);
            val = 16 * val + n;
        }
        return val;
    }

    /**
     * This method converts a Decimal number to a octal number
     *
     * @param q The Decimal Number
     * @return The Octal number
     */
    public static int decimal2octal(int q) {
        int now;
        int i = 1;
        int octnum = 0;
        while (q > 0) {
            now = q % 8;
            octnum = (now * (int) (Math.pow(10, i))) + octnum;
            q /= 8;
            i++;
        }
        octnum /= 10;
        return octnum;
    }

    /**
     * Main method that gets the hex input from user and converts it into octal.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        String hexadecnum;
        int decnum, octalnum;
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter Hexadecimal Number : ");
        hexadecnum = scan.nextLine();

        // first convert hexadecimal to decimal
        decnum = hex2decimal(hexadecnum); // Pass the string to the hex2decimal function and get the decimal form in
        // variable decnum

        // convert decimal to octal
        octalnum = decimal2octal(decnum);
        System.out.println("Number in octal: " + octalnum);
        scan.close();
    }
}
