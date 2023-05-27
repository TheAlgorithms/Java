package com.thealgorithms.conversions;
import java.util.Scanner;
public class OctalToBinary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an octal number: ");
        String octalNumber = scanner.nextLine();

        // Convert octal to binary
        String binaryNumber = convertOctalToBinary(octalNumber);

        System.out.println("Binary equivalent: " + binaryNumber);
    }

    public static String convertOctalToBinary(String octalNumber) {
        StringBuilder binaryNumber = new StringBuilder();

        for (int i = 0; i < octalNumber.length(); i++) {
            char octalDigitChar = octalNumber.charAt(i);
            int octalDigit = Character.getNumericValue(octalDigitChar);

            String binaryDigit = convertOctalDigitToBinary(octalDigit);
            binaryNumber.append(binaryDigit);
        }

        return binaryNumber.toString();
    }

    public static String convertOctalDigitToBinary(int octalDigit) {
        StringBuilder binaryDigit = new StringBuilder();

        while (octalDigit != 0) {
            int octalDigitRemainder = octalDigit % 2;
            binaryDigit.insert(0, octalDigitRemainder);

            octalDigit /= 2;
        }

        // Add leading zeros if necessary
        while (binaryDigit.length() < 3) {
            binaryDigit.insert(0, "0");
        }

        return binaryDigit.toString();
    }
}
