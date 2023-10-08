package com.thealgorithms.conversions;

import java.util.Scanner;

/**
 * Converts any Octal Number to HexaDecimal
 *
 * @author Tanmay Joshi
 */
public class OctalToHexadecimal {

    /**
     * This method converts a Octal number to a decimal number
     *
     * @param s The Octal Number
     * @return The Decimal number
     */
    public static int octToDec(String s) {
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            char num = s.charAt(j);
            num -= '0';
            i *= 8;
            i += num;
        }
        return i;
    }

    /**
     * This method converts a Decimal number to a Hexadecimal number
     *
     * @param d The Decimal Number
     * @return The Hexadecimal number
     */
    public static String decimalToHex(int d) {
        String digits = "0123456789ABCDEF";
        if (d <= 0) {
            return "0";
        }
        String hex = "";
        while (d > 0) {
            int digit = d % 16;
            hex = digits.charAt(digit) + hex;
            d = d / 16;
        }
        return hex;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the Octal number: ");
        // Take octal number as input from user in a string
        String oct = input.next();

        // Pass the octal number to function and get converted decimal form
        int decimal = octToDec(oct);

        // Pass the decimal number to function and get converted Hex form of the number
        String hex = decimalToHex(decimal);
        System.out.println("The Hexadecimal equivalant is: " + hex);
        input.close();
    }
}
