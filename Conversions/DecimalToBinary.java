package Conversions;

import java.util.Scanner;

/**
 * This class converts a Decimal number to a Binary number
 *
 * @author Unknown
 */
class DecimalToBinary {

    /**
     * Main Method
     *
     * @param args Command Line Arguments
     */
    public static void main(String args[]) {
        conventionalConversion();
        bitwiseConversion();
        usingStrings();
    }

    /**
     * This method converts a decimal number
     * to a binary number using a conventional
     * algorithm.
     */
    public static void conventionalConversion() {
        int n, b = 0, c = 0, d;
        Scanner input = new Scanner(System.in);
        System.out.printf("Conventional conversion.\n\tEnter the decimal number: ");
        n = input.nextInt();
        while (n != 0) {
            d = n % 2;
            b = b + d * (int) Math.pow(10, c++);
            n /= 2;
        } //converting decimal to binary
        System.out.println("\tBinary number: " + b);
    }

    /**
     * This method converts a decimal number
     * to a binary number using a bitwise
     * algorithm
     */
    public static void bitwiseConversion() {
        int n, b = 0, c = 0, d;
        Scanner input = new Scanner(System.in);
        System.out.printf("Bitwise conversion.\n\tEnter the decimal number: ");
        n = input.nextInt();
        while (n != 0) {
            d = (n & 1);
            b += d * (int) Math.pow(10, c++);
            n >>= 1;
        }
        System.out.println("\tBinary number: " + b);
    }

    /**
     * This method converts a decimal number
     * to a binary number using a Strings 
     * to store the calculated value instead 
     * of integers as the output binary 
     * can be bigger than the limit of
     * Integers
     */

    public static void usingStrings(){
        int n, d;
        String s= new String();
        Scanner input = new Scanner(System.in);
        System.out.printf("Using Strings.\n\tEnter the decimal number: ");
        n = input.nextInt();
        while (n != 0) {
            d= n % 2;
            s= String.valueOf(d)+ s;
            n/=2;
        }
        System.out.println("\tBinary number: " + s);
    }

}
