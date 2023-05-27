package com.thealgorithms.conversions;

import java.util.Scanner;
public class OctalToBinary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an octal number: ");
        int octalNumber = scanner.nextInt();

        // Convert octal to binary
        long binaryNumber = convertOctalToBinary(octalNumber);

        System.out.println("Binary equivalent: " + binaryNumber);
    }

    public static long convertOctalToBinary(int octalNumber) {
        int decimalNumber = 0;
        int i = 0;

        // Convert octal to decimal
        while (octalNumber != 0) {
            decimalNumber += (octalNumber % 10) * Math.pow(8, i);
            ++i;
            octalNumber /= 10;
        }

        long binaryNumber = 0;
        i = 1;

        // Convert decimal to binary
        while (decimalNumber != 0) {
            binaryNumber += (decimalNumber % 2) * i;
            decimalNumber /= 2;
            i *= 10;
        }

        return binaryNumber;
    }
}
