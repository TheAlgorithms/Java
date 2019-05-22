package Conversions;

import java.util.Scanner;

public class HexaDecimalToDecimal {

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

    public static void main(String args[]) {
        String hexa_Input;
        int dec_output;
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter Hexadecimal Number : ");
        hexa_Input = scan.nextLine();

        // convert hexadecimal to decimal

        dec_output = getHexaToDec(hexa_Input);
        /*
        Pass the string to the getHexaToDec function
        and it returns the decimal form in the variable dec_output.
        */
        System.out.println("Number in Decimal: " + dec_output);


    }
}
