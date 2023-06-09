package com.thealgorithms.others;

import java.util.Arrays;
import java.util.Objects;

/**
 * The Luhn algorithm or Luhn formula, also known as the "modulus 10" or "mod
 * 10" algorithm, named after its creator, IBM scientist Hans Peter Luhn, is a
 * simple checksum formula used to validate a variety of identification numbers.
 *
 * <p>
 * The algorithm is in the public domain and is in wide use today. It is
 * specified in ISO/IEC 7812-1. It is not intended to be a cryptographically
 * secure hash function; it was designed to protect against accidental errors,
 * not malicious attacks. Most credit cards and many government identification
 * numbers use the algorithm as a simple method of distinguishing valid numbers
 * from mistyped or otherwise incorrect numbers.</p>
 *
 * <p>
 * The Luhn algorithm will detect any single-digit error, as well as almost all
 * transpositions of adjacent digits. It will not, however, detect transposition
 * of the two-digit sequence 09 to 90 (or vice versa). It will detect most of
 * the possible twin errors (it will not detect 22 ↔ 55, 33 ↔ 66 or 44 ↔
 * 77).</p>
 *
 * <p>
 * The check digit is computed as follows:</p>
 * <ol>
 * <li>Take the original number and starting from the rightmost digit moving
 * left, double the value of every second digit (including the rightmost
 * digit).</li>
 * <li>Replace the resulting value at each position with the sum of the digits
 * of this position's value or just subtract 9 from all numbers more or equal
 * then 10.</li>
 * <li>Sum up the resulting values from all positions (s).</li>
 * <li>The calculated check digit is equal to {@code 10 - s % 10}.</li>
 * </ol>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Luhn_algorithm">Wiki</a>
 */
public class Luhn {

    /**
     * Check input digits array by Luhn algorithm. Initial array doesn't change
     * while processing.
     *
     * @param digits array of digits from 0 to 9
     * @return true if check was successful, false otherwise
     */
    public static boolean luhnCheck(int[] digits) {
        int[] numbers = Arrays.copyOf(digits, digits.length);
        int sum = 0;

        for (int i = numbers.length - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                int temp = numbers[i] * 2;
                if (temp > 9) {
                    temp = temp - 9;
                }
                numbers[i] = temp;
            }
            sum += numbers[i];
        }

        return sum % 10 == 0;
    }

    public static void main(String[] args) {
        System.out.println("Luhn algorithm usage examples:");
        int[] validInput = {4, 5, 6, 1, 2, 6, 1, 2, 1, 2, 3, 4, 5, 4, 6, 7};
        int[] invalidInput = {4, 5, 6, 1, 2, 6, 1, 2, 1, 2, 3, 4, 5, 4, 6, 4}; // typo in last
                                                                               // symbol
        checkAndPrint(validInput);
        checkAndPrint(invalidInput);

        System.out.println("\nBusiness examples:");
        String validCardNumber = "5265 9251 6151 1412";
        String invalidCardNumber = "4929 3231 3088 1896";
        String illegalCardNumber = "4F15 BC06 3A88 76D5";
        businessExample(validCardNumber);
        businessExample(invalidCardNumber);
        businessExample(illegalCardNumber);
    }

    private static void checkAndPrint(int[] input) {
        String validationResult = Luhn.luhnCheck(input) ? "valid" : "not valid";
        System.out.println("Input " + Arrays.toString(input) + " is " + validationResult);
    }

    /*
        ========================
         Business usage example
        ========================
     */
    /**
     * Object representation of credit card.
     */
    private record CreditCard(int[] digits) {
        private static final int DIGITS_COUNT = 16;

        /**
         * @param cardNumber string representation of credit card number - 16
         * digits. Can have spaces for digits separation
         * @return credit card object
         * @throws IllegalArgumentException if input string is not 16 digits or
         * if Luhn check was failed
         */
        public static CreditCard fromString(String cardNumber) {
            Objects.requireNonNull(cardNumber);
            String trimmedCardNumber = cardNumber.replaceAll(" ", "");
            if (trimmedCardNumber.length() != DIGITS_COUNT || !trimmedCardNumber.matches("\\d+")) {
                throw new IllegalArgumentException("{" + cardNumber + "} - is not a card number");
            }

            int[] cardNumbers = toIntArray(trimmedCardNumber);
            boolean isValid = luhnCheck(cardNumbers);
            if (!isValid) {
                throw new IllegalArgumentException("Credit card number {" + cardNumber + "} - have a typo");
            }

            return new CreditCard(cardNumbers);
        }

        /**
         * @return string representation separated by space every 4 digits.
         * Example: "5265 9251 6151 1412"
         */
        public String number() {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < DIGITS_COUNT; i++) {
                if (i % 4 == 0 && i != 0) {
                    result.append(" ");
                }
                result.append(digits[i]);
            }
            return result.toString();
        }

        @Override
        public String toString() {
            return String.format("%s {%s}", CreditCard.class.getSimpleName(), number());
        }

        private static int[] toIntArray(String string) {
            return string.chars().map(i -> Character.digit(i, 10)).toArray();
        }
    }

    private static void businessExample(String cardNumber) {
        try {
            System.out.println("Trying to create CreditCard object from valid card number: " + cardNumber);
            CreditCard creditCard = CreditCard.fromString(cardNumber);
            System.out.println("And business object is successfully created: " + creditCard + "\n");
        } catch (IllegalArgumentException e) {
            System.out.println("And fail with exception message: " + e.getMessage() + "\n");
        }
    }
}
