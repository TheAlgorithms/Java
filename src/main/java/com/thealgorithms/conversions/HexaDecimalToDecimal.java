package com.thealgorithms.conversions;

import java.util.Scanner;

public final class HexaDecimalToDecimal {
    private HexaDecimalToDecimal() {
    }

    // convert hexadecimal to decimal
    public static int getHexaToDec(String hex) {
        String digits = "0123456789ABCDEF";
        hex = hex.toUpperCase();
        int val = 0;
        for (int i = 0; i < hex.length(); i++) {
            int d = digits.indexOf(hex.charAt(i));
            val = 16 * val + d;
        }
        return val;
    }

    // Main method gets the hexadecimal input from user and converts it into Decimal output.
    public static void main(String[] args) {
        String hexaInput;
        int decOutput;
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter Hexadecimal Number : ");
        hexaInput = scan.nextLine();

        // convert hexadecimal to decimal
        decOutput = getHexaToDec(hexaInput);
        /*
    Pass the string to the getHexaToDec function
    and it returns the decimal form in the variable decOutput.
         */
        System.out.println("Number in Decimal: " + decOutput);
        scan.close();
    }
}
