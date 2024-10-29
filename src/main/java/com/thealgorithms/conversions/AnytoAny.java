package com.thealgorithms.conversions;

/**
 * A utility class for converting numbers from any base to any other base.
 *
 * This class provides a method to convert a source number from a given base
 * to a destination number in another base. Valid bases range from 2 to 10.
 */
public final class AnytoAny {
    private AnytoAny() {
    }

    /**
     * Converts a number from a source base to a destination base.
     *
     * @param sourceNumber The number in the source base (as an integer).
     * @param sourceBase The base of the source number (between 2 and 10).
     * @param destBase The base to which the number should be converted (between 2 and 10).
     * @throws IllegalArgumentException if the bases are not between 2 and 10.
     * @return The converted number in the destination base (as an integer).
     */
    public static int convertBase(int sourceNumber, int sourceBase, int destBase) {
        if (sourceBase < 2 || sourceBase > 10 || destBase < 2 || destBase > 10) {
            throw new IllegalArgumentException("Bases must be between 2 and 10.");
        }

        int decimalValue = toDecimal(sourceNumber, sourceBase);
        return fromDecimal(decimalValue, destBase);
    }

    /**
     * Converts a number from a given base to its decimal representation (base 10).
     *
     * @param number The number in the original base.
     * @param base The base of the given number.
     * @return The decimal representation of the number.
     */
    private static int toDecimal(int number, int base) {
        int decimalValue = 0;
        int multiplier = 1;

        while (number != 0) {
            decimalValue += (number % 10) * multiplier;
            multiplier *= base;
            number /= 10;
        }
        return decimalValue;
    }

    /**
     * Converts a decimal (base 10) number to a specified base.
     *
     * @param decimal The decimal number to convert.
     * @param base The destination base for conversion.
     * @return The number in the specified base.
     */
    private static int fromDecimal(int decimal, int base) {
        int result = 0;
        int multiplier = 1;

        while (decimal != 0) {
            result += (decimal % base) * multiplier;
            multiplier *= 10;
            decimal /= base;
        }
        return result;
    }
}
