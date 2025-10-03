package com.thealgorithms.maths;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A utility class for calculating the day of the week for a given date using Zeller's Congruence.
 *
 * <p>Zeller's Congruence is an algorithm devised by Christian Zeller in the 19th century to calculate
 * the day of the week for any Julian or Gregorian calendar date. The input date must be in the format
 * "MM-DD-YYYY" or "MM/DD/YYYY".
 *
 * <p>This class is final and cannot be instantiated.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Zeller%27s_congruence">Wikipedia: Zeller's Congruence</a>
 */
public final class ZellersCongruence {

    private static final String[] DAYS = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    // Private constructor to prevent instantiation
    private ZellersCongruence() {
    }

    /**
     * Calculates the day of the week for a given date using Zeller's Congruence.
     *
     * <p>The algorithm works for both Julian and Gregorian calendar dates. The input date must be
     * in the format "MM-DD-YYYY" or "MM/DD/YYYY".
     *
     * @param input the date in the format "MM-DD-YYYY" or "MM/DD/YYYY"
     * @return a string indicating the day of the week for the given date
     * @throws IllegalArgumentException if the input format is invalid, the date is invalid,
     *                                  or the year is out of range
     */
    public static String calculateDay(String input) {
        if (input == null || input.length() != 10) {
            throw new IllegalArgumentException("Input date must be 10 characters long in the format MM-DD-YYYY or MM/DD/YYYY.");
        }

        int month = parsePart(input.substring(0, 2), 1, 12, "Month must be between 1 and 12.");
        char sep1 = input.charAt(2);
        validateSeparator(sep1);

        int day = parsePart(input.substring(3, 5), 1, 31, "Day must be between 1 and 31.");
        char sep2 = input.charAt(5);
        validateSeparator(sep2);

        int year = parsePart(input.substring(6, 10), 46, 8499, "Year must be between 46 and 8499.");

        try {
            Objects.requireNonNull(LocalDate.of(year, month, day));
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Invalid date.", e);
        }
        if (month <= 2) {
            year -= 1;
            month += 12;
        }

        int century = year / 100;
        int yearOfCentury = year % 100;
        int t = (int) (2.6 * month - 5.39);
        int u = century / 4;
        int v = yearOfCentury / 4;
        int f = (int) Math.round((day + yearOfCentury + t + u + v - 2 * century) % 7.0);

        int correctedDay = (f + 7) % 7;

        return "The date " + input + " falls on a " + DAYS[correctedDay] + ".";
    }

    /**
     * Parses a part of the date string and validates its range.
     *
     * @param part  the substring to parse
     * @param min   the minimum valid value
     * @param max   the maximum valid value
     * @param error the error message to throw if validation fails
     * @return the parsed integer value
     * @throws IllegalArgumentException if the part is not a valid number or is out of range
     */
    private static int parsePart(String part, int min, int max, String error) {
        try {
            int value = Integer.parseInt(part);
            if (value < min || value > max) {
                throw new IllegalArgumentException(error);
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid numeric part: " + part, e);
        }
    }

    /**
     * Validates the separator character in the date string.
     *
     * @param sep the separator character
     * @throws IllegalArgumentException if the separator is not '-' or '/'
     */
    private static void validateSeparator(char sep) {
        if (sep != '-' && sep != '/') {
            throw new IllegalArgumentException("Date separator must be '-' or '/'.");
        }
    }
}
