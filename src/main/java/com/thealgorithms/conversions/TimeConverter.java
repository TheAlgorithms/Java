package com.thealgorithms.conversions;

import java.util.Locale;
import java.util.Map;

/**
 * A utility class to convert between different units of time.
 *
 * <p>This class supports conversions between the following units:
 * <ul>
 *   <li>seconds</li>
 *   <li>minutes</li>
 *   <li>hours</li>
 *   <li>days</li>
 *   <li>weeks</li>
 *   <li>months (approximated as 30.44 days)</li>
 *   <li>years (approximated as 365.25 days)</li>
 * </ul>
 *
 * <p>The conversion is based on predefined constants in seconds.
 * Results are rounded to three decimal places for consistency.
 *
 * <p>This class is final and cannot be instantiated.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Unit_of_time">Wikipedia: Unit of time</a>
 */
public final class TimeConverter {

    private TimeConverter() {
        // Prevent instantiation
    }

    /**
     * Supported time units with their equivalent in seconds.
     */
    private enum TimeUnit {
        SECONDS(1.0),
        MINUTES(60.0),
        HOURS(3600.0),
        DAYS(86400.0),
        WEEKS(604800.0),
        MONTHS(2629800.0), // 30.44 days
        YEARS(31557600.0); // 365.25 days

        private final double seconds;

        TimeUnit(double seconds) {
            this.seconds = seconds;
        }

        public double toSeconds(double value) {
            return value * seconds;
        }

        public double fromSeconds(double secondsValue) {
            return secondsValue / seconds;
        }
    }

    private static final Map<String, TimeUnit> UNIT_LOOKUP
        = Map.ofEntries(Map.entry("seconds", TimeUnit.SECONDS), Map.entry("minutes", TimeUnit.MINUTES), Map.entry("hours", TimeUnit.HOURS), Map.entry("days", TimeUnit.DAYS), Map.entry("weeks", TimeUnit.WEEKS), Map.entry("months", TimeUnit.MONTHS), Map.entry("years", TimeUnit.YEARS));

    /**
     * Converts a time value from one unit to another.
     *
     * @param timeValue the numeric value of time to convert; must be non-negative
     * @param unitFrom the unit of the input value (e.g., "minutes", "hours")
     * @param unitTo the unit to convert into (e.g., "seconds", "days")
     * @return the converted value in the target unit, rounded to three decimals
     * @throws IllegalArgumentException if {@code timeValue} is negative
     * @throws IllegalArgumentException if either {@code unitFrom} or {@code unitTo} is not supported
     */
    public static double convertTime(double timeValue, String unitFrom, String unitTo) {
        if (timeValue < 0) {
            throw new IllegalArgumentException("timeValue must be a non-negative number.");
        }

        TimeUnit from = resolveUnit(unitFrom);
        TimeUnit to = resolveUnit(unitTo);

        double secondsValue = from.toSeconds(timeValue);
        double converted = to.fromSeconds(secondsValue);

        return Math.round(converted * 1000.0) / 1000.0;
    }

    private static TimeUnit resolveUnit(String unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }
        TimeUnit resolved = UNIT_LOOKUP.get(unit.toLowerCase(Locale.ROOT));
        if (resolved == null) {
            throw new IllegalArgumentException("Invalid unit '" + unit + "'. Supported units are: " + UNIT_LOOKUP.keySet());
        }
        return resolved;
    }
}
