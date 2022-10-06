package com.thealgorithms.others;

import java.util.Objects;

/**
 * Damm algorithm is a check digit algorithm that detects all single-digit
 * errors and all adjacent transposition errors. It was presented by H. Michael
 * Damm in 2004. Essential part of the algorithm is a quasigroup of order 10
 * (i.e. having a 10 × 10 Latin square as the body of its operation table) with
 * the special feature of being weakly totally anti-symmetric. Damm revealed
 * several methods to create totally anti-symmetric quasigroups of order 10 and
 * gave some examples in his doctoral dissertation.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Damm_algorithm">Wiki. Damm
 * algorithm</a>
 */
public class Damm {

    /**
     * Weakly totally anti-symmetric quasigroup of order 10. This table is not
     * the only possible realisation of weak totally anti-symmetric quasigroup
     * but the most common one (taken from Damm doctoral dissertation). All
     * zeros lay on the diagonal because it simplifies the check digit
     * calculation.
     */
    private static final byte[][] DAMM_TABLE = {
        { 0, 3, 1, 7, 5, 9, 8, 6, 4, 2 },
        { 7, 0, 9, 2, 1, 5, 4, 8, 6, 3 },
        { 4, 2, 0, 6, 8, 7, 1, 3, 5, 9 },
        { 1, 7, 5, 0, 9, 8, 3, 4, 2, 6 },
        { 6, 1, 2, 3, 0, 4, 5, 9, 7, 8 },
        { 3, 6, 7, 4, 2, 0, 9, 5, 8, 1 },
        { 5, 8, 6, 9, 7, 2, 0, 1, 3, 4 },
        { 8, 9, 4, 5, 3, 6, 2, 0, 1, 7 },
        { 9, 4, 3, 8, 6, 1, 7, 2, 0, 5 },
        { 2, 5, 8, 1, 4, 3, 6, 7, 9, 0 },
    };

    /**
     * Check input digits by Damm algorithm.
     *
     * @param digits input to check
     * @return true if check was successful, false otherwise
     * @throws IllegalArgumentException if input parameter contains not only
     * digits
     * @throws NullPointerException if input is null
     */
    public static boolean dammCheck(String digits) {
        checkInput(digits);
        int[] numbers = toIntArray(digits);

        int checksum = 0;
        for (int number : numbers) {
            checksum = DAMM_TABLE[checksum][number];
        }

        return checksum == 0;
    }

    /**
     * Calculate check digit for initial digits and add it tho the last
     * position.
     *
     * @param initialDigits initial value
     * @return digits with the checksum in the last position
     * @throws IllegalArgumentException if input parameter contains not only
     * digits
     * @throws NullPointerException if input is null
     */
    public static String addDammChecksum(String initialDigits) {
        checkInput(initialDigits);
        int[] numbers = toIntArray(initialDigits);

        int checksum = 0;
        for (int number : numbers) {
            checksum = DAMM_TABLE[checksum][number];
        }

        return initialDigits + checksum;
    }

    public static void main(String[] args) {
        System.out.println("Damm algorithm usage examples:");
        var validInput = "5724";
        var invalidInput = "5824";
        checkAndPrint(validInput);
        checkAndPrint(invalidInput);

        System.out.println("\nCheck digit generation example:");
        var input = "572";
        generateAndPrint(input);
    }

    private static void checkAndPrint(String input) {
        String validationResult = Damm.dammCheck(input) ? "valid" : "not valid";
        System.out.println("Input '" + input + "' is " + validationResult);
    }

    private static void generateAndPrint(String input) {
        String result = addDammChecksum(input);
        System.out.println(
            "Generate and add checksum to initial value '" +
            input +
            "'. Result: '" +
            result +
            "'"
        );
    }

    private static void checkInput(String input) {
        Objects.requireNonNull(input);
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException(
                "Input '" + input + "' contains not only digits"
            );
        }
    }

    private static int[] toIntArray(String string) {
        return string.chars().map(i -> Character.digit(i, 10)).toArray();
    }
}
