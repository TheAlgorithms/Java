package Conversions;

import java.util.Scanner;

/**
 * This class converts a Binary number to a Decimal number
 *
 */
class BinaryToDecimal {

    /**
     * Main Method
     *
     * @param args Command line arguments
     */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int bin_num, bin_copy, d, s = 0, power = 0;
        System.out.print("Binary number: ");
        bin_num = sc.nextInt();
        bin_copy = bin_num;
        while (bin_copy != 0) {
            d = bin_copy % 10;
            s += d * (int) Math.pow(2, power++);
            bin_copy /= 10;
        }
        System.out.println("Decimal equivalent:" + s);
        sc.close();
    }
}
