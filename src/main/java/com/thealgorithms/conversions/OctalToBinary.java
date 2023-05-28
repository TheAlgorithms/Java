package com.thealgorithms.conversions;
import java.util.Scanner;

/**
 * Converts any Octal Number to a Binary Number
 *
 * @author Bama Charan Chhandogi
 */

public class OctalToBinary {
    public static long convertOctalToBinary(int octalNumber) {
        long binaryNumber = 0;
        int digitPosition = 1;

        while (octalNumber != 0) {
            int octalDigit = octalNumber % 10;
            long binaryDigit = convertOctalDigitToBinary(octalDigit);

            binaryNumber += binaryDigit * digitPosition;

            octalNumber /= 10;
            digitPosition *= 1000; // Move to the next group of 3 binary digits
        }

        return binaryNumber;
    }

    public static long convertOctalDigitToBinary(int octalDigit) {
        long binaryDigit = 0;
        int binaryMultiplier = 1;

        while (octalDigit != 0) {
            int octalDigitRemainder = octalDigit % 2;
            binaryDigit += octalDigitRemainder * binaryMultiplier;

            octalDigit /= 2;
            binaryMultiplier *= 10;
        }

        return binaryDigit;
    }
}
